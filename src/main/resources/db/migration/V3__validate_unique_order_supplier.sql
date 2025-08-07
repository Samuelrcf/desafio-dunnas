CREATE OR REPLACE FUNCTION validate_unique_supplier()
RETURNS TRIGGER AS $$
DECLARE
    existing_supplier_id bigint;  -- aqui, trocar para bigint
BEGIN
    SELECT p.supplier_id INTO existing_supplier_id
    FROM tb_orders_items oi
    JOIN tb_products p ON oi.product_id = p.id
    WHERE oi.order_id = NEW.order_id
    LIMIT 1;

    IF EXISTS (
        SELECT 1
        FROM tb_products p
        WHERE p.id = NEW.product_id
        AND p.supplier_id <> existing_supplier_id
    ) THEN
        RAISE EXCEPTION 'All products in an order must belong to the same supplier.';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Criação da trigger
CREATE TRIGGER trigger_validate_unique_supplier
BEFORE INSERT ON tb_orders_items
FOR EACH ROW
EXECUTE FUNCTION validate_unique_supplier();
