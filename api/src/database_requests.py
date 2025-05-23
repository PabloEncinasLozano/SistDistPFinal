import mysql.connector
from flask import jsonify


#---===Cosas Usuarios===---

def get_all_users():
    """	
    Obtener todos los usuarios de la base de datos
    """

    conn = mysql.connector.connect(
        host = "mysql",
        port = 3306,
        user = "root",
        password = "123456",
        database = "pokewiki_database",
        charset='utf8mb4'
    )


    try:
        cursor = conn.cursor()
        cursor.execute("SELECT email, password FROM users")
        
        users = cursor.fetchall()

        cursor.close()
        conn.close()

        dict_em_pass_users = {email: password for email, password in users}

        return dict_em_pass_users


    except Exception as e:
        return {"Error": str(e)}, 500
    



def register_new_user(email:str, password:str, nombre:str, apellido:str):
    """
    Registrar nuevo usuario en la base de datos
    """ 

    conn = mysql.connector.connect(
        host = "mysql",
        port = 3306,
        user = "root",
        password = "123456",
        database = "pokewiki_database",
        charset='utf8mb4'
    )

    try:
        cursor = conn.cursor()

        cursor.execute("INSERT INTO users (email, password, name, surname) VALUES (%s, %s, %s, %s)", (email, password, nombre, apellido))
        conn.commit()

        cursor.close()
        conn.close()


    except Exception as e:
        return {"Error": str(e)}, 500


# ---===Cosas Login (Spring Security)===---
def get_user_password(email:str):
    """
    Obtener contraseña de un usuario usando su email
    """ 

    conn = mysql.connector.connect(
        host = "mysql",
        port = 3306,
        user = "root",
        password = "123456",
        database = "pokewiki_database",
        charset='utf8mb4'
    )


    try:
        cursor = conn.cursor()
        cursor.execute("SELECT password FROM users WHERE email = %s", (email,))
        
        password = cursor.fetchone()

        password = password[0] if password else None
        
        cursor.close()
        conn.close()

        return password

    except Exception as e:
        return {"Error": str(e)}, 500
    

#---===Cosas Productos===---

def get_all_products():
    """	
    Obtener todos los productos de la base de datos
    """

    conn = mysql.connector.connect(
        host = "mysql",
        port = 3306,
        user = "root",
        password = "123456",
        database = "pokewiki_database",
        charset='utf8mb4'
    )


    try:
        cursor = conn.cursor()
        cursor.execute("SELECT * FROM products")
        productos = cursor.fetchall() #devuelve lista de tuplas

        #meter todas las tuplas a una lista de diccionarios
        lista_dict_productos=[]

        for prod_actual in productos:
            dict = {
                "id":prod_actual[0],
                "name":prod_actual[1],
                "description":prod_actual[2],
                "type":prod_actual[3],
                "price":prod_actual[4]
            }

            lista_dict_productos.append(dict)

        cursor.close()
        conn.close()


        return jsonify(lista_dict_productos)


    except Exception as e:
        return jsonify({"error": str(e)}), 500