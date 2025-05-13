CREATE TABLE IF NOT EXISTS collections (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_collection VARCHAR(60) NOT NULL,
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);


CREATE TABLE if NOT EXISTS collections_items(
    collection_id INT NOT NULL,
    product_id INT NOT NULL,
    PRIMARY KEY (collection_id, product_id),
    FOREIGN KEY (collection_id) REFERENCES collections(id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE
);


INSERT INTO collections (name_collection, user_id) VALUES ('MisFavoritos', 1);
INSERT INTO collections_items (collection_id, product_id) VALUES (1, 2), (1, 5);

