CREATE TABLE IF NOT EXISTS teams_list (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name_team VARCHAR(60) NOT NULL,
    user_email VARCHAR(60) NOT NULL,
    FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE
);


CREATE TABLE if NOT EXISTS pokemon_team(
    team_id INT NOT NULL,
    pokemon_name VARCHAR(60) NOT NULL,
    PRIMARY KEY (team_id, pokemon_name),
    FOREIGN KEY (team_id) REFERENCES teams_list(id) ON DELETE CASCADE,
);


