CREATE TABLE IF NOT EXISTS products(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL UNIQUE,
    description VARCHAR(60) NOT NULL,
    type VARCHAR(50) NOT NULL,
    price FLOAT(10,2) NOT NULL
);


CREATE TABLE IF NOT EXISTS users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    password VARCHAR(60) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(50) NOT NULL,
    surname VARCHAR(50) NOT NULL
);


-- Insertar datos de ejemplo
INSERT INTO users (password, email, name, surname) VALUES
('123', 'ana@example.com', 'Ana', 'Gómez'),
('456', 'luis@example.com', 'Luis', 'Pérez');



-- Insertar datos de ejemplo
INSERT INTO products (name, description, type, price) VALUES
('Pokeball', 'Una pokeball simple', 'pokeball', 200),
('Greatball', 'Una gran pokeball', 'pokeball', 600),
('Ultraball', 'Una ultraball', 'pokeball', 1200),
('Masterball', 'Una masterball', 'pokeball', 0), -- No se puede comprar, precio 0
('Potion', 'Restaura 20 PS', 'potion', 300),
('Super Potion', 'Restaura 50 PS', 'potion', 700),
('Full Restore', 'Restaura todos los PS y cura estados', 'potion', 3000),
('Hyper Potion', 'Restaura 120 PS', 'potion', 1500),
('Max Potion', 'Restaura todos los PS', 'potion', 2500),
('Oran Berry', 'Restaura 10 PS', 'berry', 200),
('Sitrus Berry', 'Restaura 25% de los PS', 'berry', 800),
('Lum Berry', 'Cura cualquier estado', 'berry', 1000),
('Leppa Berry', 'Restaura 10 PP', 'berry', 300),
('Chesto Berry', 'Cura el estado de sueño', 'berry', 300),
('Escape Rope', 'Permite salir de una cueva o mazmorra', 'tool', 1000),
('Repel', 'Evita encuentros con Pokémon débiles', 'tool', 350),
('Super Repel', 'Evita encuentros con Pokémon débiles por más tiempo', 'tool', 500),
('Max Repel', 'Evita encuentros con Pokémon débiles por el máximo tiempo', 'tool', 700),
('Rare Candy', 'Sube un nivel al Pokémon', 'candy', 0), -- No se puede comprar, precio NULL
('Exp. Share', 'Comparte experiencia con otro Pokémon', 'tool', 0), -- No se puede comprar, precio NULL
('Quick Claw', 'Permite atacar primero en ocasiones', 'held_item', 4000),
('Focus Sash', 'Evita que el Pokémon sea derrotado de un golpe', 'held_item', 2000),
('Leftovers', 'Restaura PS gradualmente durante el combate', 'held_item', 4000),
('Choice Band', 'Aumenta el ataque pero limita los movimientos', 'held_item', 3000),
('Life Orb', 'Aumenta el daño a costa de PS', 'held_item', 3000),
('Soothe Bell', 'Aumenta la amistad del Pokémon', 'held_item', 1000);
