INSERT INTO tb_users (id, user_name, password, role) VALUES
(2147483645, 'fornecedor1', '$2a$12$TSpv.CP9CK4f4zHR9P1Xoun/MCqKOP.cJsqLVophzyIBok.Eytt/u', 'SUPPLIER'),
(2147483644, 'fornecedor2', '$2a$12$TSpv.CP9CK4f4zHR9P1Xoun/MCqKOP.cJsqLVophzyIBok.Eytt/u', 'SUPPLIER');

INSERT INTO tb_suppliers (id, name, cnpj, user_id) VALUES
(2147483645, 'Fornecedor ABC Ltda', '12345678000199', 2147483645),
(2147483644, 'Fornecedor XYZ S/A', '98765432000155', 2147483644);
