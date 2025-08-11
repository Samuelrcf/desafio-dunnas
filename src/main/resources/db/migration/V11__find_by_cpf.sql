CREATE OR REPLACE FUNCTION find_client_by_cpf(in_cpf varchar)
RETURNS SETOF tb_clients AS $$
BEGIN
    RETURN QUERY SELECT * FROM tb_clients WHERE cpf = in_cpf;
END;
$$ LANGUAGE plpgsql;
