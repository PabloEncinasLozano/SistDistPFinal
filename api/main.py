from flask import Flask, jsonify, request

from src.database_requests import get_all_users
from src.database_requests import register_new_user
from src.database_requests import get_all_products
from src.pokeAPI_request import get_pokemon_info

''''
Metodos del API

GET -> Obtener informacion
POST -> Crear informacion
PUT -> Actualizar informacion
DELETE -> Eliminar informacion
'''

app = Flask(__name__) 

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
        return jsonify({"Error":str(e)}), 400
    except Exception as e:
        return jsonify({"Error":str(e)}), 500



#--==ENDPOINT para login==--
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

            return jsonify({"mensaje":"Usuario Introducido no Existe"}), 200

        if email in valid_users and password == valid_users[email]:
            return jsonify({"mensaje":"Usuario Valido"}), 200
        else:
            return jsonify({"mensaje":"Usuario Invalido"}), 401

    except ValueError as e:
        return jsonify({"Error":str(e)}), 400
    except Exception as e:
        return jsonify({"Error":str(e)}), 500
    


#--==ENDPOINT para registrarse==--
@app.route('/register', methods=['POST','GET']) 
def endpoint_register():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        datos = request.get_json()
        email:str = datos["email"]
        password:str = datos["password"]
        nombre:str = datos["name"]
        apellido:str = datos["surname"]
        

        valid_users = get_all_users()

        if email not in valid_users:

            register_new_user(email, password, nombre, apellido)
            return jsonify({"mensaje":"Usuario Registrado Correctamente"}), 200
        
        else:
            return jsonify({"mensaje":"Usuario ya existente"}), 401

    except ValueError as e:
        return jsonify({"Error":str(e)}), 400
    except Exception as e:
        return jsonify({"Error":str(e)}), 500






#--==ENDPOINT para login==--
@app.route('/pokeAPI', methods=['GET']) 
def endpoint_pokeAPI():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        idPokemon = request.args.get("id")

        if not idPokemon:
            return jsonify({"Error":"Se debe dar el ID de un pokemon"}), 400


        return get_pokemon_info(idPokemon)

    except ValueError as e:
        return jsonify({"Error": str(e)}), 400
    except Exception as e:
        return jsonify({"Error":str(e)}), 500


if __name__ == '__main__': 
    app.run(debug=True, port=8000) 