<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Perfil do Cliente</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
        }
        .profile-container {
            background: white;
            max-width: 450px;
            margin: 0 auto;
            padding: 25px 30px;
            border-radius: 10px;
            box-shadow: 0 3px 10px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        .info-row {
            margin-bottom: 12px;
        }
        .info-label {
            font-weight: bold;
            color: #555;
        }
        .info-value {
            margin-left: 10px;
            color: #222;
        }
        .buttons {
            display: flex;
            justify-content: space-between;
            margin: 25px 0;
        }
        button, .button-link {
            padding: 10px 15px;
            background-color: #1976d2;
            color: white;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            flex: 1;
            margin: 0 5px;
        }
        button:hover, .button-link:hover {
            background-color: #145ca4;
        }
        #addCreditSection {
            margin-top: 20px;
            border-top: 1px solid #ddd;
            padding-top: 20px;
        }
        #addCreditForm input[type="number"] {
            width: calc(100% - 120px);
            padding: 8px;
            border-radius: 6px;
            border: 1px solid #ccc;
            margin-right: 10px;
            box-sizing: border-box;
        }
        #addCreditForm button {
            width: 100px;
            padding: 9px;
        }
        #message {
            margin-top: 15px;
            font-weight: bold;
        }
        #message.success {
            color: green;
        }
        #message.error {
            color: red;
        }
		#message {
		    margin-top: 15px;
		    font-weight: bold;
		    opacity: 1;
		    transition: opacity 1.5s ease;
		}

		#message.fade-out {
		    opacity: 0;
		}
    </style>
</head>
<body>
<div class="profile-container">
    <h2>Perfil do Cliente</h2>
    
    <div class="info-row">
        <span class="info-label">Nome:</span>
        <span class="info-value">${client.name}</span>
    </div>
    <div class="info-row">
        <span class="info-label">CPF:</span>
        <span class="info-value">${client.cpf}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Data de nascimento:</span>
        <span class="info-value">${client.birthDate}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Saldo:</span>
        <span class="info-value">R$ ${client.balance}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Usuário:</span>
        <span class="info-value">${client.readUserDto.userName}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Perfil:</span>
        <span class="info-value">${client.readUserDto.role}</span>
    </div>

    <div class="buttons">
        <a href="${pageContext.request.contextPath}/clients/orders" class="button-link">Visualizar Histórico de Pedidos</a>
        <a href="${pageContext.request.contextPath}/clients/produtos" class="button-link">Visualizar Produtos Disponíveis</a>
    </div>

    <div id="addCreditSection">
        <h3>Adicionar Crédito</h3>
        <form id="addCreditForm">
            <input type="number" step="0.01" min="0.01" name="amount" placeholder="Valor (R$)" required />
            <button type="submit">Adicionar</button>
        </form>
        <div id="message"></div>
    </div>
</div>

<script>
    const form = document.getElementById('addCreditForm');
    const messageDiv = document.getElementById('message');

    form.addEventListener('submit', function(event) {
        event.preventDefault();
        messageDiv.textContent = '';
        messageDiv.className = '';

        const amount = form.amount.value;
        if (amount <= 0) {
            messageDiv.textContent = 'Informe um valor válido.';
            messageDiv.className = 'error';
            return;
        }

        fetch('${pageContext.request.contextPath}/clients/credit', {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ amount: parseFloat(amount) })
        })
        .then(response => {
            if (response.ok) {
                messageDiv.textContent = 'Crédito adicionado com sucesso!';
                messageDiv.className = 'success';
                form.reset();
                // Opcional: atualizar o saldo na tela ou recarregar
                location.reload();
            } else {
                return response.json().then(data => {
                    throw new Error(data.message || 'Erro ao adicionar crédito');
                });
            }
        })
        .catch(error => {
            messageDiv.textContent = error.message;
            messageDiv.className = 'error';
        });
    });
</script>
</body>
</html>
