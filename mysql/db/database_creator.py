#import sqlite3
import mysql.connector
from mysql.connector import Error


def create_db():
    """
    Initializa la database creando las tablas tables.
    """
    #db = sqlite3.connect('./mysql/db/poketienda_database.db')
    try:
        conexion = mysql.connector.connect(
            host = "localhost",
            port = 3306,
            user = "root",
            password = "123456",
            database = "poketienda_database"
        )

        cursor = conexion.cursor()
        cursor.execute("CREATE DATABASE IF NOT EXISTS poketienda_database")
        print ("Base de datos creada o ya existe.")
        conexion.commit()
        cursor.close()
        conexion.close()


        # Ahora la base de datos ya existe, por lo tanto a conectarse

        db = mysql.connector.connect(
            host = "localhost",
            port = 3306,
            user = "root",
            password = "123456",
            database = "poketienda_database"
        )


        cursor = db.cursor()

        # Crear tabla usuarios
        with open('./mysql/db/users_table.sql', 'r') as f:
            sql_script = f.read()
            for statement in sql_script.split(';'):
                if statement.strip():
                    cursor.execute(statement)

        # Crear tabla productos
        with open('./mysql/db/products_table.sql', 'r') as f:
            sql_script = f.read()
            for statement in sql_script.split(';'):
                if statement.strip():
                    cursor.execute(statement)

        db.commit()
        cursor.close()
        db.close()
        print("Tablas creadas correctamente.")

    except Error as e:
        print(f"Error al crear base de datos o tablas: {e}")


if __name__ == "__main__":
    create_db()
