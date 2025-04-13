CREATE TABLE "users"(
    "id" INTEGER PRIMARY KEY AUTOINCREMENT,
    "password" char(60) NOT NULL,
    "email" TEXT NOT NULL UNIQUE,
    "nombre" varchar(50) NOT NULL,
    "apellido" varchar(50) NOT NULL
);



-- Insertar datos de ejemplo
INSERT INTO "users" (password, email, nombre, apellido) VALUES
('123', 'ana@example.com', 'Ana', 'Gómez'),
('456', 'luis@example.com', 'Luis', 'Pérez');