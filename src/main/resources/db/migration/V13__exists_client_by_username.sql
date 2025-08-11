CREATE OR REPLACE FUNCTION exists_client_by_username(in_username varchar)
RETURNS boolean AS $$
DECLARE
    exists_flag boolean;
BEGIN
    SELECT EXISTS (
        SELECT 1 
        FROM tb_clients c
        JOIN tb_users u ON c.user_id = u.id
        WHERE u.user_name = in_username
    ) INTO exists_flag;

    RETURN exists_flag;
END;
$$ LANGUAGE plpgsql;
