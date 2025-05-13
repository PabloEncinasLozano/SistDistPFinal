from flask import Flask, jsonify, request


from src.database_requests import get_all_users
from src.database_requests import get_user_password
from src.database_requests import register_new_user
from src.database_requests import get_all_products
from src.pokeAPI_request import get_pokemon_info
from src.colections import create_new_collection
from src.colections import add_item_to_collection
from src.colections import get_collection_items
from src.colections import get_user_collections




import mysql.connector

''''
Metodos del API

GET -> Obtener informacion
POST -> Crear informacion
PUT -> Actualizar informacion
DELETE -> Eliminar informacion
'''

app = Flask(__name__) 
app.config['JSON_AS_ASCII'] = False

'''
@app.route('/')
def root ():
    return 'Home'

'''
@app.route('/', methods=['GET'])
def root ():
    try:

        catalogo_productos = get_all_products()
        return catalogo_productos

    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500
    



#--==ENDPOINT para login hecho manualmente==--
@app.route('/login', methods=['POST','GET']) 
def endpoint_login():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        datos = request.get_json()
        email:str = datos["email"]
        password:str = datos["password"]

        valid_users = get_all_users()

        

        if email not in valid_users:

            return jsonify({"mensaje":"Usuario Introducido no Existe"}), 404

        if password == valid_users[email]:
            return jsonify({"mensaje":"Usuario Valido"}), 200
        else: 
            return jsonify({"mensaje":"Contraseña Incorrecta"}), 403
            #NOTA: Para el usauario invalido uso 403 en vez de 401 porque creo
            # que por la configuracion del RestTemplate que utilizo para conectar
            # con el cliente el 401 se trata automaticamente como un error de
            # autenticacion y no consigo procesar correctamente el cuerpo del error
            

    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500
 


#--==ENDPOINT para login de Spring Security==--

@app.route('/login', methods=['POST']) 

def endpoint_get_password():
    try:
        datos = request.get_json()

        email:str = datos["email"]
        
        print(f"Datos recibidos: {datos}", flush=True)

        password = get_user_password(email)

        if password:
            return jsonify({"password": password}), 200
        else:
            return jsonify({"error": "Usuario no encontrado"}), 404
        
    except Exception as e:
        return jsonify({"error":str(e)}), 500



#--==ENDPOINT para registrarse==--
@app.route('/register', methods=['POST','GET']) 
def endpoint_register():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        datos = request.get_json()

        if datos is None:
            datos = request.form.to_dict()
        
        email:str = datos["email"]
        password:str = datos["password"]
        nombre:str = datos["name"]
        apellido:str = datos["surname"]
        

        valid_users = get_all_users()

        if email not in valid_users:

            register_new_user(email, password, nombre, apellido)
            return jsonify({"mensaje":"Usuario Registrado Correctamente"}), 200
            #return register_new_user(email, password, nombre, apellido)
              
        else:
            return jsonify({"mensaje":"Usuario ya existente"}), 403
            #NOTA: Este caso es lo mismo que en el login

    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500

 



#--==ENDPOINT para api externa==--
@app.route('/pokeAPI', methods=['GET','POST']) 
def endpoint_pokeAPI():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        idPokemon = request.args.get("id")

        if not idPokemon:
            return jsonify({"error":"Se debe dar el ID de un pokemon"}), 400


        return get_pokemon_info(idPokemon)

    except ValueError as e:
        return jsonify({"error": str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500






#--==ENDPOINT para colecciones==--
@app.route('/create_collection', methods=['POST','GET']) 
def endpoint_create_collections():
    """	
    Endpoint para crear una nueva coleccion
    """
    try:
        datos = request.get_json()

        if datos is None:
            datos = request.form.to_dict()
        
        name_collection:str = datos["name_collection"]
        user_id:int = 1
        #user_id:int = datos["user_id"]

        valid_collection = get_user_collections(user_id)
        
        if name_collection in valid_collection:
            return jsonify({"mensaje":"Coleccion ya existente"}), 403


        if (create_new_collection(name_collection, user_id)):
            return jsonify({"mensaje":"Coleccion Creada Correctamente"}), 200
        else:
            return jsonify({"mensaje":"Error al crear la coleccion"}), 403


    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500
    


@app.route('/list_items_collection', methods=['GET']) 
def endpoint_items_collection():
    """	
    Endpoint para listar los items de una coleccion
    """
    try:

        collection_name = request.args.get("name_collection")
        #user_id = request.args.get("user_id")
        user_id = 1

        if not collection_name:
            return jsonify({"error":"Se debe dar el nombre de la coleccion"}), 400


        collection_items = get_collection_items(collection_name, user_id)
        return collection_items

    except Exception as e:
        return jsonify({"error":str(e)}), 500


@app.route('/add_to_collection', methods=['POST','GET']) 
def endpoint_add_item_to_collections():
    """	
    Endpoint para añadir un item a una coleccion
    """
    try:
        datos = request.get_json()

        if datos is None:
            datos = request.form.to_dict()
        
        name_collection:str = datos["name_collection"]
        item_id:int = datos["item_id"]
        user_id:int = 1
        #user_id:int = datos["user_id"]

        

        if (add_item_to_collection(name_collection, user_id, item_id)):
            return jsonify({"mensaje":"Item añadido correctamente"}), 200
        else:
            return jsonify({"mensaje":"Error al añadir a la coleccion"}), 403


    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500
    



@app.route('/list_user_collections', methods=['GET']) 
def endpoint_list_user_collections():
    """	
    Endpoint para listar las colecciones de un usuario
    """
    try:
        user_id = request.args.get("user_id")

        if not user_id:
            return jsonify({"error":"Este usuario no existe"}), 400
        

        collection_list=get_user_collections(user_id)

        if (collection_list is not None):
            return jsonify(collection_list), 200
        else:
            return jsonify({"mensaje":"El usuario no tiene collecciones o error al buscarlas"}), 400


    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500




#==============Cosas de pruebas====================

@app.route('/test_register_user', methods=['POST'])
def test_register_and_list():
    try:
        datos = request.get_json()
        email = datos["email"]
        password = datos["password"]
        nombre = datos["name"]
        apellido = datos["surname"]

        # Conexión
        conn = mysql.connector.connect(
            host="mysql",
            port=3306,
            user="root",
            password="123456",
            database="pokewiki_database",
            charset='utf8mb4'
        )
        cursor = conn.cursor()

        # Insertar nuevo usuario
        try:
            cursor.execute(
                "INSERT INTO users (password, email, name, surname) VALUES (%s, %s, %s, %s)",
                (password, email, nombre, apellido)
            )
            conn.commit()
        except mysql.connector.IntegrityError as err:
            return jsonify({"Error": f"Violación de restricción (email duplicado): {str(err)}"}), 409

        # Mostrar todos los usuarios
        cursor.execute("SELECT id, email, name, surname FROM users")
        usuarios = cursor.fetchall()

        # Cerrar conexiones
        cursor.close()
        conn.close()

        # Formatear resultado
        usuarios_listos = [
            {"id": u[0], "email": u[1], "name": u[2], "surname": u[3]}
            for u in usuarios
        ]

        return jsonify({"mensaje": "Usuario insertado y listado correctamente", "usuarios": usuarios_listos}), 200

    except Exception as e:
        return jsonify({"Error": str(e)}), 500
    



@app.route('/lista_users', methods=['GET'])
def test_lista_users():

    try:

        catalogo_productos = get_all_users()
        return catalogo_productos

    except ValueError as e:
        return jsonify({"error":str(e)}), 400
    except Exception as e:
        return jsonify({"error":str(e)}), 500
    



@app.route('/delete_user', methods=['POST'])
def delete_user_postman():
    try:



        datos = request.get_json()
        email = datos["email"]


        # Conexión
        conn = mysql.connector.connect(
            host="mysql",
            port=3306,
            user="root",
            password="123456",
            database="pokewiki_database",
            charset='utf8mb4'
        )
        cursor = conn.cursor()

        # Mostrar todos los usuarios
        cursor.execute("SELECT id, email, name, surname FROM users")
        usuarios = cursor.fetchall()

        # Insertar nuevo usuario
        try:
            cursor.execute(
                "DELETE FROM users WHERE email = %s",
                (email,)
            )
            conn.commit()

            if cursor.rowcount == 0:
                return jsonify({"error": "No se encontró el usuario con ese email"}), 404
            

        except mysql.connector.IntegrityError as err:
            return jsonify({"error": f"Violación de restricción (email no registrado): {str(err)}"}), 409

        # Mostrar todos los usuarios
        cursor.execute("SELECT id, email, name, surname FROM users")
        usuarios = cursor.fetchall()

        # Cerrar conexiones
        cursor.close()
        conn.close()

        # Formatear resultado
        usuarios_listos = [
            {"id": u[0], "email": u[1], "name": u[2], "surname": u[3]}
            for u in usuarios
        ]

        return jsonify({"mensaje": "Usuario eliminado correctamente", "usuarios": usuarios_listos}), 200

    except Exception as e:
        return jsonify({"error": str(e)}), 500



if __name__ == '__main__': 
    app.run(debug=True, port=8000) 





