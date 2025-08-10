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
        .error-message {
            color: red;
            font-size: 0.85em;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>

    <script>
        const clientRegisterUrl = '${pageContext.request.contextPath}/clients/register';
        const supplierRegisterUrl = '${pageContext.request.contextPath}/suppliers/register';

        function toggleFields() {
            const clientFields = document.getElementById('client-fields');
            const supplierFields = document.getElementById('supplier-fields');
            const form = document.getElementById('formRegister');

            if (document.getElementById('radioClient').checked) {
                clientFields.style.display = 'block';
                supplierFields.style.display = 'none';
                form.action = clientRegisterUrl;

                clientFields.querySelectorAll('input').forEach(i => i.disabled = false);
                supplierFields.querySelectorAll('input').forEach(i => i.disabled = true);
            } else {
                clientFields.style.display = 'none';
                supplierFields.style.display = 'block';
                form.action = supplierRegisterUrl;

                clientFields.querySelectorAll('input').forEach(i => i.disabled = true);
                supplierFields.querySelectorAll('input').forEach(i => i.disabled = false);
            }
        }

        // Regex CPF: 000.000.000-00 or 00000000000
        const cpfPattern = /^(\d{3}\.\d{3}\.\d{3}\-\d{2}|\d{11})$/;
        // Regex CNPJ: 00.000.000/0000-00 or 00000000000000
        const cnpjPattern = /^(\d{2}\.\d{3}\.\d{3}\/\d{4}\-\d{2}|\d{14})$/;
        // Password min 8 chars, at least 1 uppercase, 1 lowercase, 1 number
        const passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}$/;

		function validateForm(event) {
		    const userType = document.querySelector('input[name="userType"]:checked').value;
		    let valid = true;
		    clearErrors();

		    if(userType === 'client') {
		        const cpfInput = document.querySelector('input[name="cpf"]');
		        if (!cpfPattern.test(cpfInput.value)) {
		            showError(cpfInput, 'CPF inválido. Use formato 000.000.000-00 ou 11 dígitos.');
		            valid = false;
		        }
		        const birthDateInput = document.querySelector('input[name="birthDate"]');
		        if (!birthDateInput.value) {
		            showError(birthDateInput, 'Informe a data de nascimento.');
		            valid = false;
		        } else {
		            const today = new Date();
		            const birthDate = new Date(birthDateInput.value);
		            if (birthDate > today) {
		                showError(birthDateInput, 'Data de nascimento não pode ser futura.');
		                valid = false;
		            }
		        }
		        // Aqui pega o campo senha dentro de client-fields, que está ativo
		        const passwordInput = document.querySelector('#client-fields input[name="createUserDto.password"]:not([disabled])');
		        if (!passwordPattern.test(passwordInput.value)) {
		            showError(passwordInput, 'Senha deve ter pelo menos 8 caracteres, incluindo maiúscula, minúscula e número.');
		            valid = false;
		        }

		    } else if(userType === 'supplier') {
		        const cnpjInput = document.querySelector('input[name="cnpj"]');
		        if (!cnpjPattern.test(cnpjInput.value)) {
		            showError(cnpjInput, 'CNPJ inválido. Use formato 00.000.000/0000-00 ou 14 dígitos.');
		            valid = false;
		        }
		        // Aqui pega o campo senha dentro de supplier-fields, que está ativo
		        const passwordInput = document.querySelector('#supplier-fields input[name="createUserDto.password"]:not([disabled])');
		        if (!passwordPattern.test(passwordInput.value)) {
		            showError(passwordInput, 'Senha deve ter pelo menos 8 caracteres, incluindo maiúscula, minúscula e número.');
		            valid = false;
		        }
		    }

		    if(!valid) {
		        event.preventDefault();
		    }
		}

        function showError(input, message) {
            let error = document.createElement('div');
            error.className = 'error-message';
            error.textContent = message;
            input.parentNode.insertBefore(error, input.nextSibling);
        }

        function clearErrors() {
            document.querySelectorAll('.error-message').forEach(e => e.remove());
        }

        window.onload = () => {
            toggleFields();
            document.getElementById('formRegister').addEventListener('submit', validateForm);
        };
    </script>
</head>
<body>
    <div class="form-container">
        <h2>Cadastro de Usuário</h2>
        <form method="post" id="formRegister" novalidate>
            <label>
                <input type="radio" name="userType" value="client" id="radioClient" checked onchange="toggleFields()"> Cliente
            </label>
            <label>
                <input type="radio" name="userType" value="supplier" id="radioSupplier" onchange="toggleFields()"> Fornecedor
            </label>

            <div id="client-fields">
                <input type="text" name="name" placeholder="Nome completo" required/>
                <input type="text" name="cpf" placeholder="CPF (ex: 000.000.000-00)" required pattern="(\d{3}\.\d{3}\.\d{3}-\d{2}|\d{11})" title="Formato CPF válido"/>
                <input type="date" name="birthDate" placeholder="Data de nascimento" required max="<%= java.time.LocalDate.now() %>" title="Data não pode ser futura"/>
                <input type="text" name="createUserDto.userName" placeholder="Nome de usuário" 
                       pattern="[A-Za-z0-9_]{3,20}" title="3 a 20 caracteres, letras, números ou _" required/>
                <input type="password" name="createUserDto.password" placeholder="Senha" required 
                       pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}" 
                       title="Senha deve ter pelo menos 8 caracteres, incluindo maiúscula, minúscula e número"/>
                <input type="hidden" name="createUserDto.role" value="CLIENT"/>
            </div>

            <div id="supplier-fields" style="display:none;">
                <input type="text" name="name" placeholder="Nome" required/>
                <input type="text" name="cnpj" placeholder="CNPJ (ex: 00.000.000/0000-00)" required pattern="(\d{2}\.\d{3}\.\d{3}\/\d{4}-\d{2}|\d{14})" title="Formato CNPJ válido"/>
                <input type="text" name="createUserDto.userName" placeholder="Nome de usuário" 
                       pattern="[A-Za-z0-9_]{3,20}" title="3 a 20 caracteres, letras, números ou _" required/>
                <input type="password" name="createUserDto.password" placeholder="Senha" required
                       pattern="(?=.*[a-z])(?=.*[A-Z])(?=.*\d).{8,}"
                       title="Senha deve ter pelo menos 8 caracteres, incluindo maiúscula, minúscula e número"/>
                <input type="hidden" name="createUserDto.role" value="SUPPLIER"/>
            </div>

            <button type="submit">Cadastrar</button>
            <p>Já tem uma conta? <a href="${pageContext.request.contextPath}/login">Faça login</a></p>
			<c:if test="${not empty errorMessage}">
			    <div style="color: red; font-weight: bold; margin-bottom: 15px;">
			        ${errorMessage}
			    </div>
			</c:if>

			<c:if test="${not empty successMessage}">
			    <div style="color: green; font-weight: bold; margin-bottom: 15px;">
			        ${successMessage}
			    </div>
			</c:if>
        </form>
    </div>
</body>
</html>
