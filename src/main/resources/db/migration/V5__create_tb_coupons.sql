CREATE TABLE tb_coupons
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    code        VARCHAR(255) NOT NULL UNIQUE,
    discount_id BIGINT UNIQUE,
    product_id  BIGINT,
    CONSTRAINT fk_coupon_discount FOREIGN KEY (discount_id) REFERENCES tb_discounts (id) ON DELETE SET NULL,
    CONSTRAINT fk_coupon_product FOREIGN KEY (product_id) REFERENCES tb_products (id) ON DELETE CASCADE
);