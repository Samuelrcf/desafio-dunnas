CREATE OR REPLACE FUNCTION find_client_by_user_id(in_user_id bigint)
RETURNS SETOF tb_clients AS $$
BEGIN
    RETURN QUERY SELECT * FROM tb_clients WHERE user_id = in_user_id;
END;
$$ LANGUAGE plpgsql;
