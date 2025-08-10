ALTER TABLE tb_users
ADD CONSTRAINT password_min_length CHECK (char_length(password) >= 8);
