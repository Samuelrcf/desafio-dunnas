ALTER TABLE tb_orders 
ALTER COLUMN order_code 
SET DATA TYPE UUID USING order_code::uuid;