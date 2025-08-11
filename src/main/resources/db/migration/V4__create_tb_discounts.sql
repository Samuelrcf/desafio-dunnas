CREATE TABLE tb_discounts
(
    id         BIGSERIAL PRIMARY KEY,
    value      NUMERIC(19, 2) NOT NULL,
    deleted     BOOLEAN NOT NULL DEFAULT FALSE
);