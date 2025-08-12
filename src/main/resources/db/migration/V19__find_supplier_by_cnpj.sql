CREATE OR REPLACE FUNCTION find_supplier_by_cnpj(p_cnpj VARCHAR)
RETURNS SETOF tb_suppliers AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM tb_suppliers
    WHERE cnpj = p_cnpj;
END;
$$ LANGUAGE plpgsql STABLE;
