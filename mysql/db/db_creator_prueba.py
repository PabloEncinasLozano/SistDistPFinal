import mysql.connector
from mysql.connector import errorcode

# Configuración de conexión a MySQL
config = {
    'host': 'localhost',
    'port': 3306,
    'user': 'root',
    'password': '123456'  # <-- cambia esto si tu contraseña es diferente
}

# Datos de la base de datos y tabla
DB_NAME = 'poketienda_database'

TABLES = {}
TABLES['users'] = (
    """
    CREATE TABLE IF NOT EXISTS users (
        id INT AUTO_INCREMENT PRIMARY KEY,
        email VARCHAR(255) NOT NULL UNIQUE,
        password VARCHAR(255) NOT NULL,
        nombre VARCHAR(100),
        apellido VARCHAR(100)
    )
    """
)

def create_database(cursor):
    try:
        cursor.execute(f"CREATE DATABASE IF NOT EXISTS {DB_NAME} DEFAULT CHARACTER SET 'utf8mb4'")
        print(f"Base de datos '{DB_NAME}' creada o ya existe.")
    except mysql.connector.Error as err:
        print(f"Error creando la base de datos: {err}")
        exit(1)

def connect_and_create():
    try:
        # Conectar sin seleccionar base de datos
        db = mysql.connector.connect(**config)
        cursor = db.cursor()
        create_database(cursor)

        # Seleccionar base de datos creada
        db.database = DB_NAME

        # Crear las tablas necesarias
        for table_name, ddl in TABLES.items():
            try:
                print(f"Creando tabla `{table_name}`...")
                cursor.execute(ddl)
                print("OK")
            except mysql.connector.Error as err:
                print(f"Error creando la tabla `{table_name}`: {err}")

        cursor.close()
        db.close()

    except mysql.connector.Error as err:
        if err.errno == errorcode.ER_ACCESS_DENIED_ERROR:
            print("Usuario o contraseña incorrectos")
        else:
            print(err)

if __name__ == '__main__':
    connect_and_create()
