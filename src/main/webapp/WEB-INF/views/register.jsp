<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Cadastro de Usuário</title>
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

	        // Garantir que o role do client esteja habilitado
	        document.getElementById('roleClient').disabled = false;
	        document.getElementById('roleSupplier').disabled = true;

	        // Ajustar action para cliente
	        document.getElementById('formClient').action = '/register/client';

	    } else {
	        clientFields.style.display = 'none';
	        supplierFields.style.display = 'block';

	        clientFields.querySelectorAll('input').forEach(input => input.disabled = true);
	        supplierFields.querySelectorAll('input').forEach(input => input.disabled = false);

	        // Garantir que o role do supplier esteja habilitado
	        document.getElementById('roleClient').disabled = true;
	        document.getElementById('roleSupplier').disabled = false;

	        // Ajustar action para fornecedor
	        document.getElementById('formClient').action = '/register/supplier';
	    }
	}
	window.onload = toggleFields;
	</script>
</head>
<body>
    <h2>Cadastro de Usuário</h2>
	<form action="/register/client" method="post" id="formClient">
	    <input type="radio" name="userType" value="client" id="radioClient" checked onchange="toggleFields()"> Cliente
	    <input type="radio" name="userType" value="supplier" id="radioSupplier" onchange="toggleFields()"> Fornecedor

	    <div id="client-fields">
	        <input type="text" name="name" required placeholder="Nome completo"/>
	        <input type="text" name="cpf" required placeholder="CPF"/>
	        <input type="date" name="birthDate" required placeholder="Data de nascimento"/>
	        <input type="email" name="createUserDto.userName" required placeholder="Email"/>
	        <input type="password" name="createUserDto.password" required placeholder="Senha"/>
	        <!-- input hidden para role, que será preenchido via JS -->
	        <input type="hidden" id="roleClient" name="createUserDto.role" value="CLIENT"/>
	    </div>

	    <div id="supplier-fields" style="display:none;">
	        <input type="text" name="name" disabled placeholder="Nome completo"/>
	        <input type="text" name="cnpj" disabled placeholder="CNPJ"/>
	        <input type="email" name="createUserDto.userName" disabled placeholder="Email"/>
	        <input type="password" name="createUserDto.password" disabled placeholder="Senha"/>
	        <input type="hidden" id="roleSupplier" name="createUserDto.role" value="SUPPLIER" disabled/>
	    </div>

	    <button type="submit">Cadastrar</button>
	</form>
</body>
</html>
