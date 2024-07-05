CREATE TABLE "users"
(
    id    INTEGER,
    email VARCHAR(25) NOT NULL UNIQUE,
    login VARCHAR(20) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);


