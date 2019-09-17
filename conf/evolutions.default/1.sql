# --- !Ups

CREATE TABLE user (
    ID integer NOT NULL AUTO_INCREMENT PRIMARY KEY,
    email varchar(64) NOT NULL,
    salt varchar(10) NOT NULL,
    hash varchar(32) NOT NULL
);


# --- !Downs

DROP TABLE user;