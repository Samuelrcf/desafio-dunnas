INSERT INTO tb_users (id, user_name, password, role) VALUES
(2147483647, 'joao123', '$2a$12$TSpv.CP9CK4f4zHR9P1Xoun/MCqKOP.cJsqLVophzyIBok.Eytt/u', 'CLIENT'),
(2147483646, 'maria123', '$2a$12$TSpv.CP9CK4f4zHR9P1Xoun/MCqKOP.cJsqLVophzyIBok.Eytt/u', 'CLIENT');

INSERT INTO tb_clients (id, name, cpf, birth_date, balance, user_id) VALUES
(2147483647, 'João Silva', '12345678901', '1985-03-15', 0.00, 2147483647),
(2147483646, 'Maria Madalena', '98765432100', '1990-07-22', 0.00, 2147483646);
