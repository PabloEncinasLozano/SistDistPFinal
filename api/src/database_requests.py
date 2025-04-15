import mysql.connector


def get_all_users():
    """	
    Obtener todos los usuarios de la base de datos
    """
    #db = sqlite3.connect('./mysql/db/poketienda_database.db')

    db = mysql.connector.connect(
        host = "localhost",
        port = 3407,
        user = "root",
        password = "123456",
        database = "poketienda_database"
    )


    try:
        cursor = db.cursor()
        cursor.execute("SELECT email, password FROM users")
        users = cursor.fetchall()

        db.close()

        dict_em_pass_users = {email: password for email, password in users}

        return dict_em_pass_users


    except Exception as e:
        return {"Error": str(e)}, 500
    



def register_new_user(email:str, password:str, nombre:str, apellido:str):
    """	
    Registrar nuevo usuario en la base de datos
    """
    #db = sqlite3.connect('./mysql/db/poketienda_database.db')


    db = mysql.connector.connect(
        host = "localhost",
        port = 3407,
        user = "root",
        password = "123456",
        database = "poketienda_database"
    )


    try:
        cursor = db.cursor()
        cursor.execute("INSERT INTO users (email, password, nombre, apellido) VALUES (?, ?, ?, ?)", (email, password, nombre, apellido))
        db.commit()
        db.close()

        return {"Message": "Usuario registrado correctamente"}, 200

    except Exception as e:
        return {"Error": str(e)}, 500