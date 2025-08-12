ALTER TABLE tb_clients
ADD CONSTRAINT cpf_format CHECK (
    cpf ~ '^\d{3}\.\d{3}\.\d{3}-\d{2}$' OR
    cpf ~ '^\d{11}$'
);
