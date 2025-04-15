CREATE TABLE "products"(
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name char(60) NOT NULL UNIQUE,
    description char(60) NOT NULL,
    type varchar(50) NOT NULL,
    price FLOAT(10,2) NOT NULL
);



-- Insertar datos de ejemplo
INSERT INTO "products" (name, description, type, price) VALUES
('Pokeball', 'Una pokeball simple', 'pokeball', 10.21),
('Greatball', 'Una gran pokeball', 'pokeball', 20.21),
('Ultraball', 'Una ultraball', 'pokeball', 30.21),
('Masterball', 'Una masterball', 'pokeball', 40.21);
