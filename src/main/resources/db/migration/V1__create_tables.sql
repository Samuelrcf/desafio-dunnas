CREATE TABLE tb_users (
    id BIGSERIAL PRIMARY KEY,
    user_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

CREATE TABLE tb_clients (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL UNIQUE,
    birth_date DATE NOT NULL,
    balance NUMERIC(19, 2) NOT NULL,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_client_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

CREATE TABLE tb_suppliers (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cnpj VARCHAR(18) NOT NULL UNIQUE,
    user_id BIGINT UNIQUE,
    CONSTRAINT fk_supplier_user FOREIGN KEY (user_id) REFERENCES tb_users(id)
);

CREATE TABLE tb_products (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(19, 2) NOT NULL,
    supplier_id BIGINT,
    CONSTRAINT fk_product_supplier FOREIGN KEY (supplier_id) REFERENCES tb_suppliers(id)
);

CREATE TABLE tb_orders (
    id BIGSERIAL PRIMARY KEY,
    order_code VARCHAR(255) NOT NULL,
    supplier_id BIGINT,
    client_id BIGINT,
    total NUMERIC(19, 2) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    CONSTRAINT fk_order_supplier FOREIGN KEY (supplier_id) REFERENCES tb_suppliers(id),
    CONSTRAINT fk_order_client FOREIGN KEY (client_id) REFERENCES tb_clients(id)
);

CREATE TABLE tb_orders_items (
    id BIGSERIAL PRIMARY KEY,
    order_id BIGINT,
    product_id BIGINT,
    quantity INT NOT NULL,
    subtotal NUMERIC(19, 2) NOT NULL,
    CONSTRAINT fk_order_item_order FOREIGN KEY (order_id) REFERENCES tb_orders(id) ON DELETE CASCADE,
    CONSTRAINT fk_order_item_product FOREIGN KEY (product_id) REFERENCES tb_products(id)
);
