CREATE SCHEMA IF NOT EXISTS covatriviatestdb;

USE covatriviatestdb;

CREATE TABLE  IF NOT EXISTS  USERS(
    id INT AUTO_INCREMENT   PRIMARY KEY,
    first_name VARCHAR(256) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    email VARCHAR (256) NOT NULL,
    salt VARCHAR (256) NOT NULL,
    password VARCHAR (256) NOT NULL,
    created_at timestamp,
    updated_at timestamp,
    deleted_at timestamp,
)

CREATE TABLE IF NOT EXISTS TRIVIA(
    id INT AUTO_INCREMENT   PRIMARY KEY,
    question VARCHAR (256) NOT NULL,
    answer VARCHAR (256) NOT NULL,
    difficulty_level VARCHAR (256)
);

CREATE TABLE IF NOT EXISTS TRIVIA_RESULT (
    id INT AUTO_INCREMENT PRIMARY KEY,
    date_of_trivia timestamp
    num_passed_trivia INT,
    num_failed_trivia INT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES USERS (user_id)
);