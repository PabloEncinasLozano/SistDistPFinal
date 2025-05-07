import mysql.connector
from flask import jsonify


def create_new_collection(nombre_coleccion:str,user_id:int):


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

        cursor.execute("INSERT INTO collections (name_collection, user_id) VALUES (%s, %s)", (nombre_coleccion,user_id))
        conn.commit()

        cursor.close()
        conn.close()

        return True


    except Exception as e:
        return {"Error": str(e)}, 500