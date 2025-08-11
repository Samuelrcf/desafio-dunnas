CREATE TABLE tb_coupons
(
    id          BIGSERIAL PRIMARY KEY,
    name        VARCHAR(255) NOT NULL,
    code        VARCHAR(255) NOT NULL UNIQUE,
    discount_id BIGINT UNIQUE,
    deleted     BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_coupon_discount FOREIGN KEY (discount_id) REFERENCES tb_discounts (id) ON DELETE SET NULL
);
