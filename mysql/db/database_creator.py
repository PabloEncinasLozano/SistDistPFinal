import sqlite3

def create_db():
    """
    Initializa la database creando las tablas tables.
    """
    db = sqlite3.connect('./mysql/db/users_database.db')
    with open('./mysql/db/users_table.sql', 'r') as f:
        db.executescript(f.read())
    db.close()

create_db()
