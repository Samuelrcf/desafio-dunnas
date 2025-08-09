<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .form-container {
            background-color: white;
            padding: 25px 35px;
            border-radius: 10px;
            box-shadow: 0 4px 12px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 400px;
        }

        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }

        input[type="text"],
        input[type="password"],
        input[type="date"] {
            width: 100%;
            padding: 10px;
            margin: 6px 0 14px 0;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        input[type="radio"] {
            margin-right: 5px;
        }

        label {
            margin-right: 15px;
            font-size: 14px;
            color: #555;
        }

        button {
            width: 100%;
            padding: 12px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        button:hover {
            background-color: #145ca4;
        }
    </style>

    <script>
        function toggleFields() {
            const clientFields = document.getElementById('client-fields');
            const supplierFields = document.getElementById('supplier-fields');

            const radioClient = document.getElementById('radioClient');
            const radioSupplier = document.getElementById('radioSupplier');

            if (radioClient.checked) {
                clientFields.style.display = 'block';
                supplierFields.style.display = 'none';

                clientFields.querySelectorAll('input').forEach(input => input.disabled = false);
                supplierFields.querySelectorAll('input').forEach(input => input.disabled = true);

                document.getElementById('roleClient').disabled = false;
                document.getElementById('roleSupplier').disabled = true;

                document.getElementById('formClient').action = '/register/client';
            } else {
                clientFields.style.display = 'none';
                supplierFields.style.display = 'block';

                clientFields.querySelectorAll('input').forEach(input => input.disabled = true);
                supplierFields.querySelectorAll('input').forEach(input => input.disabled = false);

                document.getElementById('roleClient').disabled = true;
                document.getElementById('roleSupplier').disabled = false;

                document.getElementById('formClient').action = '/register/supplier';
            }
        }
        window.onload = toggleFields;
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Cadastro de Usuário</h2>
        <form action="/register/client" method="post" id="formClient">
            <label>
                <input type="radio" name="userType" value="client" id="radioClient" checked onchange="toggleFields()"> Cliente
            </label>
            <label>
                <input type="radio" name="userType" value="supplier" id="radioSupplier" onchange="toggleFields()"> Fornecedor
            </label>

            <div id="client-fields">
                <input type="text" name="name" required placeholder="Nome completo"/>
                <input type="text" name="cpf" required placeholder="CPF"/>
                <input type="date" name="birthDate" required placeholder="Data de nascimento"/>
                <input type="text" 
                       name="createUserDto.userName" 
                       required 
                       placeholder="Nome de usuário"
                       pattern="[A-Za-z0-9_]{3,20}"
                       title="O nome de usuário deve ter entre 3 e 20 caracteres e conter apenas letras, números e underline"/>
                <input type="password" name="createUserDto.password" required placeholder="Senha"/>
                <input type="hidden" id="roleClient" name="createUserDto.role" value="CLIENT"/>
            </div>

            <div id="supplier-fields" style="display:none;">
                <input type="text" name="name" disabled placeholder="Nome"/>
                <input type="text" name="cnpj" disabled placeholder="CNPJ"/>
                <input type="text" 
                       name="createUserDto.userName" 
                       required 
                       placeholder="Nome de usuário"
                       pattern="[A-Za-z0-9_]{3,20}"
                       title="O nome de usuário deve ter entre 3 e 20 caracteres e conter apenas letras, números e underline"/>
                <input type="password" name="createUserDto.password" disabled placeholder="Senha"/>
                <input type="hidden" id="roleSupplier" name="createUserDto.role" value="SUPPLIER" disabled/>
            </div>

            <button type="submit">Cadastrar</button>
			<p>Já tem uma conta? <a href="${pageContext.request.contextPath}/login">Faça login</a></p>
        </form>
    </div>
</body>
</html>
