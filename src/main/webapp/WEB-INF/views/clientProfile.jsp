<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Perfil do Cliente</title>
    <style>
        /* Reset básico */
        * {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f0f4f8;
            padding: 40px 20px;
            display: flex;
            justify-content: center;
            align-items: flex-start;
            min-height: 100vh;
            margin: 0;
        }

        .profile-container {
            background: #fff;
            width: 100%;
            max-width: 480px;
            border-radius: 12px;
            box-shadow: 0 6px 18px rgba(0,0,0,0.12);
            padding: 30px 35px 40px 35px;
            display: flex;
            flex-direction: column;
        }

        h2 {
            text-align: center;
            margin-bottom: 28px;
            color: #1f2937;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 0.02em;
        }

        .info-row {
            display: flex;
            justify-content: space-between;
            padding: 12px 0;
            border-bottom: 1px solid #e2e8f0;
        }

        .info-row:last-child {
            border-bottom: none;
        }

        .info-label {
            font-weight: 600;
            color: #4b5563;
            font-size: 15px;
        }

        .info-value {
            color: #111827;
            font-size: 15px;
            max-width: 60%;
            word-wrap: break-word;
            text-align: right;
        }

        .buttons {
            margin: 30px 0 15px;
            display: flex;
            gap: 15px;
            justify-content: center;
        }

        .button-link {
            background-color: #2563eb;
            color: white;
            padding: 12px 0;
            flex: 1;
            text-align: center;
            border-radius: 8px;
            font-weight: 600;
            font-size: 16px;
            text-decoration: none;
            transition: background-color 0.25s ease;
            user-select: none;
            box-shadow: 0 2px 8px rgb(37 99 235 / 0.4);
        }
		.logout-button {
		    flex: 0 0 100px;  
		    padding: 8px 10px; 
		    font-size: 14px;   
		    background-color: #ef4444; 
		    box-shadow: 0 2px 8px rgba(239, 68, 68, 0.4);
		}

        #addCreditSection {
            margin-top: 30px;
            padding-top: 25px;
            border-top: 1.5px solid #cbd5e1;
        }

        #addCreditSection h3 {
            margin-bottom: 18px;
            font-weight: 700;
            font-size: 20px;
            color: #334155;
            text-align: center;
        }

        #addCreditForm {
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        #addCreditForm input[type="number"] {
            flex: 1 1 150px;
            max-width: 250px;
            padding: 12px 14px;
            border-radius: 8px;
            border: 1.5px solid #cbd5e1;
            font-size: 16px;
            color: #374151;
            transition: border-color 0.2s ease;
        }
        #addCreditForm input[type="number"]:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 8px rgba(37, 99, 235, 0.4);
        }

        #addCreditForm button {
            flex: 0 0 120px;
            background-color: #10b981;
            color: white;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-weight: 700;
            font-size: 16px;
            box-shadow: 0 2px 8px rgba(16, 185, 129, 0.4);
            transition: background-color 0.25s ease;
            user-select: none;
        }
        #addCreditForm button:hover {
            background-color: #059669;
            box-shadow: 0 4px 14px rgba(5, 150, 105, 0.6);
        }

        #message {
            margin-top: 20px;
            font-weight: 600;
            font-size: 16px;
            text-align: center;
            opacity: 1;
            transition: opacity 1.5s ease;
            user-select: none;
            padding: 10px 15px;
            border-radius: 6px;
        }
        #message.success {
            color: #065f46;
            background-color: #d1fae5;
            border: 1.5px solid #10b981;
        }
        #message.error {
            color: #b91c1c;
            background-color: #fee2e2;
            border: 1.5px solid #ef4444;
        }

        /* Responsive */
        @media (max-width: 480px) {
            .buttons {
                flex-direction: column;
            }
            #addCreditForm {
                flex-direction: column;
            }
            #addCreditForm button {
                max-width: 100%;
            }
        }
    </style>
</head>
<body>
<div class="profile-container">
    <h2>Perfil do Cliente</h2>

    <!-- Mensagem de sucesso/erro -->
    <c:if test="${not empty successMessage}">
        <div id="message" class="success">${successMessage}</div>
    </c:if>
    <c:if test="${not empty errorMessage}">
        <div id="message" class="error">${errorMessage}</div>
    </c:if>

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
        <a href="${pageContext.request.contextPath}/clients/history?page=0&size=10" class="button-link">Pedidos</a>
        <a href="${pageContext.request.contextPath}/products" class="button-link">Produtos</a>
    </div>

    <div id="addCreditSection">
        <h3>Adicionar Crédito</h3>
        <form id="addCreditForm" action="${pageContext.request.contextPath}/clients/credit" method="post">
            <input type="number" step="0.01" min="0.01" name="amount" placeholder="Valor (R$)" required />
            <button type="submit">Adicionar</button>
        </form>
    </div>
	<div class="buttons">
		<a href="${pageContext.request.contextPath}/login" class="button-link logout-button">Logout</a>
	</div>

</div>
</body>
</html>
