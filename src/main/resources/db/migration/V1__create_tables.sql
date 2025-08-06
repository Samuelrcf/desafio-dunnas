CREATE TABLE tb_users (
    id SERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE tb_clients (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    balance NUMERIC(19,2) DEFAULT 0 NOT NULL,
    user_id INTEGER UNIQUE,
    CONSTRAINT fk_client_user FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);

CREATE TABLE tb_suppliers (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    user_id INTEGER UNIQUE,
    CONSTRAINT fk_supplier_user FOREIGN KEY (user_id) REFERENCES tb_users(id) ON DELETE CASCADE
);

CREATE TABLE tb_products (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(19,2) NOT NULL,
    supplier_id INTEGER NOT NULL,
    CONSTRAINT fk_product_supplier FOREIGN KEY (supplier_id) REFERENCES tb_suppliers(id) ON DELETE CASCADE
);
