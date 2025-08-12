CREATE OR REPLACE FUNCTION exists_supplier_by_product_id(p_product_id BIGINT)
RETURNS BOOLEAN AS $$
DECLARE
    result BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM tb_suppliers s
        JOIN tb_products p ON p.supplier_id = s.id
        WHERE p.id = p_product_id
    ) INTO result;
    RETURN result;
END;
$$ LANGUAGE plpgsql STABLE;
