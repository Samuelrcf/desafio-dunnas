CREATE OR REPLACE FUNCTION find_products_by_supplier_id(p_supplier_id BIGINT)
RETURNS SETOF tb_products AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM tb_products
    WHERE supplier_id = p_supplier_id AND deleted = false;
END;
$$ LANGUAGE plpgsql STABLE;
