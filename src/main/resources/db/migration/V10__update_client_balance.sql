CREATE OR REPLACE PROCEDURE update_client_balance(client_id bigint, new_balance numeric)
LANGUAGE plpgsql
AS $$
BEGIN
    IF new_balance < 0 THEN
        RAISE EXCEPTION 'Saldo insuficiente';
    END IF;

    UPDATE tb_clients SET balance = new_balance WHERE id = client_id;
END;
$$;
