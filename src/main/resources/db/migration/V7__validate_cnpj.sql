ALTER TABLE tb_suppliers
ADD CONSTRAINT cnpj_format CHECK (
    cnpj ~ '^\d{2}\.\d{3}\.\d{3}/\d{4}-\d{2}$' OR
    cnpj ~ '^\d{14}$'
);
