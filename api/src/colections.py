import mysql.connector
from flask import jsonify


def create_new_collection(name_collection:str,user_id:int):


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

        cursor.execute("SELECT 1 FROM collections WHERE name_collection = %s AND user_id = %s", (name_collection,user_id))
        

        if cursor.fetchone():
            return {"Error": "Una coleccion con este nombre ya existe"}, 400


        cursor.execute("INSERT INTO collections (name_collection, user_id) VALUES (%s, %s)", (name_collection,user_id))

        conn.commit()
        cursor.close()
        conn.close()

        return True


    except Exception as e:
        return {"Error": str(e)}, 500
    


def add_item_to_collection(collection_name:int, user_id:int,item_id:int):

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

        cursor.execute("SELECT id FROM collections WHERE name_collection= %s AND user_id= %s", (collection_name,user_id))
        
        result = cursor.fetchone()

        if not result:
            return {"Error": "No existe la coleccion"}, 400
        
        collection_id = result[0]

        # Revision de duplicados
        cursor.execute("SELECT 1 FROM collections_items WHERE collection_id = %s AND product_id = %s", (collection_id,item_id))
        if cursor.fetchone():
            return {"Error": "El item ya existe en la coleccion"}, 400


        cursor.execute("INSERT INTO collections_items (collection_id, product_id) VALUES (%s, %s)", (collection_id,item_id))

        conn.commit()
        cursor.close()
        conn.close()

        return True


    except Exception as e:
        return {"Error": str(e)}, 500
    


def get_collection_items(collection_name:str, user_id:int):
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

        cursor.execute("SELECT id FROM collections WHERE name_collection = %s AND user_id = %s", (collection_name,user_id))

        result = cursor.fetchone()

        if not result:
            return jsonify({"Error": "No existe la coleccion"}), 400


        collection_id = result[0]

        cursor.execute("SELECT p.id, p.name, p.description, p.type, p.price FROM products p " \
                        "JOIN collections_items ci ON p.id = ci.product_id " \
                        "WHERE ci.collection_id = %s", (collection_id,))
        

        items = cursor.fetchall() #devuelve lista de tuplas

        #meter todas las tuplas a una lista de diccionarios
        lista_dict_items=[]

        for prod_actual in items:
            dict = {
                "id":prod_actual[0],
                "name":prod_actual[1],
                "description":prod_actual[2],
                "type":prod_actual[3],
                "price":prod_actual[4]
            }

            lista_dict_items.append(dict)

        cursor.close()
        conn.close()


        return jsonify(lista_dict_items)
    
    except Exception as e:
        return jsonify({"error": str(e)}), 500
    

def get_user_collections(user_id:int):
    """	
    Obtener todas las colecciones de un usuario
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

        cursor.execute("SELECT id, name_collection FROM collections WHERE user_id = %s", (user_id,))
        collections = cursor.fetchall() #devuelve lista de tuplas

        #meter todas las tuplas a una lista de diccionarios
        lista_dict_collections=[]
        for collection in collections:
            dict = {
                "id":collection[0],
                "name_collection":collection[1]
            }

            lista_dict_collections.append(dict)

        cursor.close()
        conn.close()

        return lista_dict_collections
    

    except Exception as e:
        return jsonify({"error": str(e)}), 500
