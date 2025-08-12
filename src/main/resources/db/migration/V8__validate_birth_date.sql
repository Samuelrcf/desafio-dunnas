ALTER TABLE tb_clients
ADD CONSTRAINT birthdate_not_future CHECK (
    birth_date <= CURRENT_DATE
);
