CREATE OR REPLACE FUNCTION find_supplier_by_user_id(p_user_id BIGINT)
RETURNS SETOF tb_suppliers AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM tb_suppliers
    WHERE user_id = p_user_id;
END;
$$ LANGUAGE plpgsql STABLE;
