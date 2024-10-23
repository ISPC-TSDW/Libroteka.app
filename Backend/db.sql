/*En settings pueden insertar esta confi de db y luego en mysql crear la db:
DATABASES = {
    'default': {
       'ENGINE': 'django.db.backends.mysql',
       'NAME': 'libroteka', #cambia por la el nombre de db que quieras
       'USER': 'root',
       'PASSWORD': 'root', #cambia por la contraseña de root
       'HOST': 'localhost',
       'PORT': '3306',
       'OPTIONS': {
            'init_command': "SET sql_mode='STRICT_TRANS_TABLES'"
        }

    }
}*/

----Script para crear las tablas

CREATE TABLE role (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE author (
    id_Author SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    country VARCHAR(50) NOT NULL
);

CREATE TABLE editorial (
    id_Editorial SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE genre (
    id_Genre SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE book (
    id_Book SERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    id_Author_id INT REFERENCES author(id_Author) ON DELETE CASCADE,
    id_Genre_id INT REFERENCES genre(id_Genre) ON DELETE CASCADE,
    description TEXT NOT NULL CHECK (LENGTH(description) <= 1500),
    price DECIMAL(10, 2) NOT NULL,
    stock INT DEFAULT 1000 NOT NULL,
    id_Editorial_id INT REFERENCES editorial(id_Editorial) ON DELETE CASCADE,
    avg_rating FLOAT DEFAULT 0.0
);

CREATE TABLE OrderStatus (
    id_Order_Status SERIAL PRIMARY KEY,
    status VARCHAR(15) NOT NULL
);

CREATE TABLE UsersLibroteka (
    email VARCHAR(254) PRIMARY KEY UNIQUE NOT NULL,
    username VARCHAR(30) UNIQUE NOT NULL,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(35) NOT NULL,
    dni VARCHAR(10) NOT NULL UNIQUE,
    password VARCHAR(128) NOT NULL,
    is_active BOOLEAN DEFAULT TRUE
);

CREATE TABLE Orders (
    id_Order SERIAL PRIMARY KEY,
    id_Order_Status INT REFERENCES OrderStatus(id_Order_Status) ON DELETE CASCADE,
    id_User VARCHAR(254) REFERENCES UsersLibroteka(email) ON DELETE CASCADE,
    date TIMESTAMP NOT NULL,
    books JSON NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    books_amount INT NOT NULL
);

CREATE TABLE favorite (
    id_user VARCHAR(254) REFERENCES UsersLibroteka(email) ON DELETE CASCADE,
    id_book INT REFERENCES book(id_Book) ON DELETE CASCADE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_user, id_book)
);

CREATE TABLE rating (
    id_user VARCHAR(254) REFERENCES UsersLibroteka(email) ON DELETE CASCADE,
    id_book INT REFERENCES book(id_Book) ON DELETE CASCADE,
    rating SMALLINT CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (id_user, id_book)
);

