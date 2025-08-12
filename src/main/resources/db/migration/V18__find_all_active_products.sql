CREATE OR REPLACE FUNCTION find_all_active_products()
RETURNS SETOF tb_products AS $$
BEGIN
    RETURN QUERY
    SELECT * FROM tb_products
    WHERE deleted = false;
END;
$$ LANGUAGE plpgsql STABLE;
