CREATE OR REPLACE FUNCTION find_product_by_name(p_name VARCHAR)
RETURNS SETOF tb_products AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM tb_products
    WHERE name = p_name AND deleted = false;
END;
$$ LANGUAGE plpgsql STABLE;
