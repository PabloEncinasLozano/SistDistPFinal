from flask import Flask, jsonify, request

from src.database_requests import get_all_users
from src.database_requests import register_new_user


''''
Metodos del API

GET -> Obtener informacion
POST -> Crear informacion
PUT -> Actualizar informacion
DELETE -> Eliminar informacion
'''

app = Flask(__name__) 


@app.route('/') 
def root ():
    return "Home"



#--==ENDPOINT para login==--
@app.route('/login', methods=['POST']) 
def endpoint_login():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        datos = request.get_json()
        email:str = datos["email"]
        password:str = datos["password"]

        valid_users = get_all_users()

        if email in valid_users and password == valid_users[email]:
            return jsonify("Usuario Valido"), 200
        else:
            return jsonify("Usuario Invalido"), 401

    except ValueError as e:
        return jsonify({"Error":e}), 400
    except Exception as e:
        return jsonify({"Error":e}), 500



#--==ENDPOINT para registrarse==--
@app.route('/register', methods=['POST']) 
def endpoint_register():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        datos = request.get_json()
        email:str = datos["email"]
        password:str = datos["password"]
        nombre:str = datos["nombre"]
        apellido:str = datos["apellido"]
        

        valid_users = get_all_users()

        if email not in valid_users:

            register_new_user(email, password, nombre, apellido)
            return jsonify("Usuario Registrado Correctamente"), 200
        
        else:
            return jsonify("Usuario ya existente"), 401

    except ValueError as e:
        return jsonify({"Error":e}), 400
    except Exception as e:
        return jsonify({"Error":e}), 500




if __name__ == '__main__': 
    app.run(debug=True, port=8000) 