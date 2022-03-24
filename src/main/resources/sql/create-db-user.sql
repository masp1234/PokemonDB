DROP USER IF EXISTS pokemon_user@localhost;
CREATE USER webshop_user@localhost IDENTIFIED BY '123123';

GRANT SELECT, INSERT, UPDATE, DELETE
    ON pokemon.*
    TO pokemon_user@localhost;

SELECT User, Host FROM mysql.user;
SHOW GRANTS FOR pokemon_user@localhost;