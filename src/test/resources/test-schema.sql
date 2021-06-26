DROP SCHEMA TESTQUIZDB;

CREATE SCHEMA IF NOT EXISTS TESTQUIZDB;


CREATE TABLE  APP_USER(
                                         id INT AUTO_INCREMENT   PRIMARY KEY,
                                         first_name VARCHAR(256) NOT NULL,
                                         last_name VARCHAR(250) NOT NULL,
                                         email VARCHAR (256) NOT NULL,
                                         username VARCHAR (256) NOT NULL,
                                         is_active INt,
                                         password VARCHAR (256) NOT NULL,
                                         created_at timestamp NOT NULL,
                                         updated_at timestamp,
                                         deleted_at timestamp
);

CREATE TABLE TRIVIA(
                                     id INT AUTO_INCREMENT   PRIMARY KEY,
                                     question VARCHAR (256) NOT NULL,
                                     answer VARCHAR (256) NOT NULL,
                                     difficulty_level VARCHAR (256)
);

CREATE TABLE TRIVIA_HISTORY (
                                              id INT AUTO_INCREMENT PRIMARY KEY,
                                              date_of_trivia timestamp,
                                              num_passed_trivia INT,
                                              num_failed_trivia INT,
                                              user_id INT,
                                              FOREIGN KEY (user_id) REFERENCES APP_USER (id)
);