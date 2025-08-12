<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Perfil do Fornecedor</title>
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
            max-width: 40%;
        }

        .info-value {
            color: #111827;
            font-size: 15px;
            max-width: 60%;
            word-wrap: break-word;
            text-align: right;
        }

        .buttons {
            margin-top: 30px;
            display: flex;
            gap: 15px;
            justify-content: center;
            flex-wrap: wrap;
        }

        .button-link {
            background-color: #2563eb;
            color: white;
            padding: 12px 0;
            flex: 1;
            max-width: 220px;
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

        /* Responsividade */
        @media (max-width: 480px) {
            .buttons {
                flex-direction: column;
            }
            .button-link {
                max-width: 100%;
            }
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
    <div class="buttons">
        <a href="${pageContext.request.contextPath}/suppliers/history?page=0&size=10" class="button-link">Pedidos</a>
        <a href="${pageContext.request.contextPath}/products/supplier" class="button-link">Meus Produtos</a>
    </div>
	<div class="buttons">
		<a href="${pageContext.request.contextPath}/login" class="button-link logout-button">Logout</a>
	</div>
</div>
</body>
</html>
