CREATE OR REPLACE FUNCTION exists_supplier_by_username(p_username VARCHAR)
RETURNS BOOLEAN AS $$
DECLARE
    result BOOLEAN;
BEGIN
    SELECT EXISTS (
        SELECT 1 FROM tb_suppliers s
        JOIN tb_users u ON s.user_id = u.id
        WHERE u.user_name = p_username
    ) INTO result;
    RETURN result;
END;
$$ LANGUAGE plpgsql STABLE;
