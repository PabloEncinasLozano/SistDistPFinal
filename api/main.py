from flask import Flask, jsonify, request



from src.database_requests import get_all_products
from src.pokeAPI_request import get_pokemon_info



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


@app.route('/', methods=['GET'])
def root ():
    try:

        catalogo_productos = get_all_products()
        return catalogo_productos

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




if __name__ == '__main__': 
    app.run(debug=True, port=8000) 





