from flask import Flask, jsonify, request

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


@app.route('/login', methods=['POST']) 
def endpoint_login():
    """	
    Endpoint para validar un usuario y contraseña
    """
    try:
        request = request.get_json()
        username:str = request["username"]
        password:str = request["password"]
        valid_users ={
            "pablo":"1234",
            "pepe":"4321",
            "maria":"1234",
            "marta":"1234",
        }
        if username in valid_users and password == valid_users[username]:
            return jsonify("Usuario Valido"), 200
        else:
            return jsonify("Usuario Invalido"), 401

    except ValueError as e:
        return jsonify({"Error":e}), 400
    except Exception as e:
        return jsonify({"Error":e}), 500


if __name__ == '__main__': 
    app.run(debug=True) 