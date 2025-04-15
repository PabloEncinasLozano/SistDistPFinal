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
('Pokeball', 'Una pokeball simple', 'pokeball', 10.21),
('Greatball', 'Una gran pokeball', 'pokeball', 20.21),
('Ultraball', 'Una ultraball', 'pokeball', 30.21),
('Masterball', 'Una masterball', 'pokeball', 40.21);
