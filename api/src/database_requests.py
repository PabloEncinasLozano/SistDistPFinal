import mysql.connector
from flask import jsonify


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