<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Perfil do Fornecedor</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f4f4f4; padding: 20px; }
        .profile-container {
            background: white;
            max-width: 500px;
            margin: auto;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
        }
        h2 { text-align: center; margin-bottom: 20px; }
        .info-row {
            margin-bottom: 12px;
        }
        .info-label {
            font-weight: bold;
            display: inline-block;
            width: 140px;
        }
        .buttons {
            margin-top: 20px;
            text-align: center;
        }
        .button-link {
            text-decoration: none;
            background: #007bff;
            color: white;
            padding: 10px 15px;
            border-radius: 5px;
            margin: 0 5px;
            display: inline-block;
        }
        .button-link:hover {
            background: #0056b3;
        }
    </style>
</head>
<body>
<div class="profile-container">
    <h2>Perfil do Fornecedor</h2>
    
    <div class="info-row">
        <span class="info-label">Nome:</span>
        <span class="info-value">${supplier.name}</span>
    </div>
    <div class="info-row">
        <span class="info-label">CNPJ:</span>
        <span class="info-value">${supplier.cnpj}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Usuário:</span>
        <span class="info-value">${supplier.readUserDto.userName}</span>
    </div>
    <div class="info-row">
        <span class="info-label">Perfil:</span>
        <span class="info-value">${supplier.readUserDto.role}</span>
    </div>

    <div class="buttons">
        <a href="${pageContext.request.contextPath}/orders" class="button-link">Visualizar Histórico de Pedidos</a>
        <a href="${pageContext.request.contextPath}/produtos" class="button-link">Visualizar Produtos</a>
        <!-- Adicione outras ações específicas do fornecedor aqui -->
    </div>
</div>
</body>
</html>
