<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Novo Produto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
            margin: 0;
            display: flex;
            justify-content: center;
			align-items: flex-start;
        }

        .container {
            background: white;
            max-width: 480px;
            width: 100%;
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

        form div {
            margin-bottom: 18px;
            display: flex;
            flex-direction: column;
        }

        label {
            font-weight: 600;
            color: #4b5563;
            margin-bottom: 6px;
            font-size: 15px;
            user-select: none;
        }

        input[type="text"], input[type="number"] {
            padding: 12px 14px;
            border-radius: 8px;
            border: 1.5px solid #cbd5e1;
            font-size: 16px;
            color: #374151;
            transition: border-color 0.2s ease;
        }

        input[type="text"]:focus, input[type="number"]:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 8px rgba(37, 99, 235, 0.4);
        }

        .error {
            color: #b91c1c;
            font-size: 13px;
            margin-top: 4px;
            user-select: none;
        }

		button[type="submit"] {
		    display: block;
		    margin: 0 auto;
		    background-color: #2563eb;
		    color: white;
		    border: none;
		    border-radius: 8px;
		    padding: 10px 20px;
		    font-weight: 700;
		    font-size: 16px;
		    cursor: pointer;
		    box-shadow: 0 2px 8px rgba(37, 99, 235, 0.4);
		    transition: background-color 0.25s ease;
		    user-select: none;
		    margin-top: 40px;
		}

        button[type="submit"]:hover {
            background-color: #1d4ed8;
            box-shadow: 0 4px 12px rgba(29, 78, 216, 0.5);
        }

        .back-link {
            margin-top: 20px;
            text-align: center;
        }

        .back-link a {
            display: inline-block;
            color: #2563eb;
            font-weight: 600;
            font-size: 15px;
            text-decoration: none;
            border-bottom: 2px solid transparent;
            padding-bottom: 3px;
            transition: border-color 0.25s ease;
            user-select: none;
        }

        .back-link a:hover {
            border-color: #2563eb;
        }

        @media (max-width: 480px) {
            .container {
                padding: 25px 20px 30px 20px;
            }
            input[type="text"], input[type="number"] {
                font-size: 14px;
                padding: 10px 12px;
            }
            button[type="submit"] {
                font-size: 14px;
                padding: 12px 0;
            }
            .back-link a {
                font-size: 14px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Novo Produto</h2>

    <form:form action="/products" method="post" modelAttribute="product">
        <div>
            <label>Nome</label>
            <form:input path="name" type="text" />
            <form:errors path="name" cssClass="error" />
        </div>

        <div>
            <label>Descrição</label>
            <form:input path="description" type="text" />
            <form:errors path="description" cssClass="error" />
        </div>

        <div>
            <label>Preço</label>
            <form:input path="price" type="number" step="0.01" />
            <form:errors path="price" cssClass="error" />
        </div>

        <button type="submit">Salvar</button>
    </form:form>

    <div class="back-link">
        <a href="${pageContext.request.contextPath}/products/supplier">&larr; Voltar</a>
    </div>
</div>
</body>
</html>
