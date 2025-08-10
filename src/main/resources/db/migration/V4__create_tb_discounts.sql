CREATE TABLE tb_discounts
(
    id         BIGSERIAL PRIMARY KEY,
    value      NUMERIC(19, 2) NOT NULL,
    product_id BIGINT,
    CONSTRAINT fk_discount_product FOREIGN KEY (product_id) REFERENCES tb_products (id) ON DELETE CASCADE
);