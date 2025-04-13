import requests

def get_pokemon_info(poke_id):


    URL = f"https://pokeapi.co/api/v2/pokemon/{poke_id}"

    pokemon_data= requests.get(URL).json()


    pokemon_characteritics={
        "name": pokemon_data["name"],
        "height": pokemon_data["height"],
        "weight": pokemon_data["weight"],
        "base_experience": pokemon_data["base_experience"],
        "types": [type["type"]["name"] for type in pokemon_data["types"]], # Listar los tipos
        "abilities": [ability["ability"]["name"] for ability in pokemon_data["abilities"]], # Listar las habilidades
        "stats": {stat["stat"]["name"]: stat["base_stat"] for stat in pokemon_data["stats"]} # Listar las estadisticas

    }


    print(pokemon_characteritics)

    return pokemon_characteritics


get_pokemon_info(23)