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
INSERT INTO products (name, description, type, price) VALUES
('Poke Ball', 'Una Poke Ball simple', 'pokeball', 200),
('Super Ball', 'Una gran Poke Ball', 'pokeball', 600),
('Ultra Ball', 'Una Ultra Ball', 'pokeball', 1200),
('Master Ball', 'Una Master Ball', 'pokeball', 0), -- No se puede comprar, precio 0

('Pocien', 'Restaura 20 PS', 'consumible', 300),
('Superpocien', 'Restaura 50 PS', 'consumible', 700),
('Restaurar Todo', 'Restaura todos los PS y cura estados', 'consumible', 3000),
('Hiperpocipon', 'Restaura 120 PS', 'consumible', 1500),
('Maxima Pocion', 'Restaura todos los PS', 'consumible', 2500),
('Cuerda Huida', 'Permite salir de una cueva o mazmorra', 'consumible', 1000),
('Repelente', 'Evita encuentros con Pokemon debiles', 'consumible', 350),
('Superrepelente', 'Evita encuentros con Pokemon debiles por mas tiempo', 'consumible', 500),
('Maximo Repelente', 'Evita encuentros con Pokemon debiles por el maximo tiempo', 'consumible', 700),
('Caramelo Raro', 'Sube un nivel al Pokemon', 'consumible', 0), -- No se puede comprar, precio NULL

('Baya Aranja', 'Restaura 10 PS', 'baya', 200),
('Baya Zidra', 'Restaura 25% de los PS', 'baya', 800),
('Baya Meloc', 'Cura cualquier estado', 'baya', 1000),
('Baya Zanama', 'Restaura 10 PP', 'baya', 300),
('Baya Atania', 'Cura el estado de sueño', 'baya', 300),
('Baya Caoca', 'Reduce el ataque especial del Pokemon', 'baya', 500),
('Baya Algama', 'Reduce la velocidad del Pokemon', 'baya', 500),
('Baya Grana', 'Reduce el ataque del Pokemon', 'baya', 500),
('Baya Ispero', 'Reduce la defensa del Pokemon', 'baya', 500),
('Baya Meluce', 'Reduce la defensa especial del Pokemon', 'baya', 500),

('Garra Rapida', 'Permite atacar primero en ocasiones', 'equipable', 4000),
('Banda Focus', 'Evita que el Pokemon sea derrotado de un golpe', 'equipable', 2000),
('Restos', 'Restaura PS gradualmente durante el combate', 'equipable', 4000),
('Cinta Elegida', 'Aumenta el ataque pero limita los movimientos', 'equipable', 3000),
('Vidasfera', 'Aumenta el dano a costa de PS', 'equipable', 3000),
('Campana Alivio', 'Aumenta la amistad del Pokemon', 'equipable', 1000),
('Repartir Experiencia', 'Comparte experiencia con otro Pokemon', 'equipable', 0); -- No se puede comprar, precio NULL



INSERT INTO users (password, email, name, surname) 
VALUES ('$2a$10$N5DN/N9wqcYaY7pW0LldsOsM9svTY85mqqh2IVhiX04Fz86ChL2ZK', 'test@example.com', 'Test', 'login');
