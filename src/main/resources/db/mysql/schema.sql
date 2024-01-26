-- Create tables

-- Authority
CREATE TABLE IF NOT EXISTS authority (
                                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                         name VARCHAR(255) NOT NULL UNIQUE
);

-- Category
CREATE TABLE IF NOT EXISTS category (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL UNIQUE,
                                        description VARCHAR(255) NOT NULL
);

-- Course
CREATE TABLE IF NOT EXISTS course (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      title VARCHAR(255) NOT NULL,
                                      description TEXT,
                                      instructor_id BIGINT,
                                      start_date TIMESTAMP,
                                      end_date TIMESTAMP,
                                      location VARCHAR(255) NOT NULL,
                                      online BOOLEAN NOT NULL,
                                      price DOUBLE NOT NULL,
                                      category_id BIGINT,
                                      FOREIGN KEY (instructor_id) REFERENCES instructor(id),
                                      FOREIGN KEY (category_id) REFERENCES category(id)
);

-- Customer
CREATE TABLE IF NOT EXISTS customer (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        first_name VARCHAR(255),
                                        last_name VARCHAR(255)
);

-- Instructor
CREATE TABLE IF NOT EXISTS instructor (
                                          id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                          first_name VARCHAR(255) NOT NULL,
                                          last_name VARCHAR(255) NOT NULL,
                                          email VARCHAR(255) NOT NULL UNIQUE,
                                          bio VARCHAR(255) NOT NULL
);

-- Role
CREATE TABLE IF NOT EXISTS role (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name VARCHAR(255) NOT NULL UNIQUE
);

-- Session
CREATE TABLE IF NOT EXISTS sessionTraing (
                                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                       training_id BIGINT,
                                       FOREIGN KEY (training_id) REFERENCES training(id)
);

-- Training
CREATE TABLE IF NOT EXISTS training (
                                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        name VARCHAR(255) NOT NULL,
                                        description TEXT,
                                        image VARCHAR(255),
                                        training_category_id INT, -- nom de la colonne
                                        FOREIGN KEY (training_category_id) REFERENCES category (id)
);

-- User
CREATE TABLE IF NOT EXISTS user (
                                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    account_non_expired BOOLEAN NOT NULL,
                                    account_non_locked BOOLEAN NOT NULL,
                                    credentials_non_expired BOOLEAN NOT NULL,
                                    enabled BOOLEAN NOT NULL,
                                    username VARCHAR(255) NOT NULL UNIQUE,
                                    password VARCHAR(255) NOT NULL,
                                    email VARCHAR(255) NOT NULL,
                                    UNIQUE KEY (email)
);

-- Join Tables

-- Role Authorities
CREATE TABLE IF NOT EXISTS role_authorities (
                                                role_id BIGINT,
                                                authority_id BIGINT,
                                                FOREIGN KEY (role_id) REFERENCES role(id),
                                                FOREIGN KEY (authority_id) REFERENCES authority(id)
);

-- User Roles
CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT,
                                          role_id BIGINT,
                                          FOREIGN KEY (user_id) REFERENCES user(id),
                                          FOREIGN KEY (role_id) REFERENCES role(id)
);

-- Session Course
CREATE TABLE IF NOT EXISTS session_course (
                                              session_id BIGINT,
                                              course_id BIGINT,
                                              FOREIGN KEY (session_id) REFERENCES sessionTraing(id),
                                              FOREIGN KEY (course_id) REFERENCES course(id)
);
