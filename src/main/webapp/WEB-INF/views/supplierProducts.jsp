<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meus Produtos</title>
    <style>
        /* Reset básico e fonte */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            margin: 30px auto;
            background-color: #f9fafb;
            max-width: 960px;
            padding: 0 20px;
            color: #1f2937;
        }
        h1 {
            font-weight: 700;
            font-size: 2.2rem;
            margin-bottom: 24px;
            text-align: center;
            letter-spacing: 0.03em;
        }
		.btn-container {
		    text-align: center;
		}
        a.btn {
            display: inline-block;
            padding: 12px 28px;
            background-color: #2563eb;
            color: white;
            font-weight: 700;
            font-size: 16px;
            border-radius: 12px;
            text-decoration: none;
            user-select: none;
            box-shadow: 0 4px 12px rgb(37 99 235 / 0.4);
            transition: background-color 0.3s ease;
            text-align: center;
        }
        a.btn:hover {
            background-color: #1e40af;
            box-shadow: 0 6px 20px rgb(30 64 175 / 0.5);
        }
        .btn-back {
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 12px;
            background: white;
            font-size: 15px;
            box-shadow: 0 6px 20px rgb(0 0 0 / 0.05);
            border-radius: 12px;
            overflow: hidden;
        }
        thead tr {
            background-color: #2563eb;
            color: white;
            font-weight: 600;
            user-select: none;
        }
        thead th {
            padding: 16px 20px;
            text-align: center;
        }
        tbody tr {
            background-color: #f3f4f6;
            border-radius: 8px;
            box-shadow: inset 0 0 8px #e0e7ff;
            transition: background-color 0.3s ease;
        }
        tbody tr:hover {
            background-color: #e0e7ff;
        }
        tbody td {
            padding: 14px 20px;
            vertical-align: middle;
            text-align: center;
            border: none;
        }
        /* Alinha as colunas de texto à esquerda (Nome e Descrição) */
        tbody td:nth-child(1),
        tbody td:nth-child(2),
        tbody td:nth-child(4),
        tbody td:nth-child(5) {
            text-align: center;
        }
        /* Estilo para listas dentro das células (descontos e cupons) */
        .discounts, .coupons {
            color: #4b5563;
            font-size: 0.95rem;
        }
        .discounts ul, .coupons ul {
            margin: 0;
            padding-left: 18px;
            list-style-type: disc;
        }
        .discounts li, .coupons li {
            margin-bottom: 4px;
        }
        /* Botão excluir */
        form button {
            background-color: #ef4444;
            border: none;
            color: white;
            padding: 8px 16px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 14px;
            cursor: pointer;
            transition: background-color 0.25s ease;
        }
        form button:hover {
            background-color: #b91c1c;
        }
        /* Margem do botão adicionar produto */
        a.btn-add {
            margin-top: 25px;
        }
		@media (max-width: 720px) {
		    body {
		        padding: 0 12px;
		        text-align: center;
		    }
		    a.btn, a.btn-add {
		        display: inline-block;
		        margin: 0 auto;
		        padding: 14px 28px;
		        width: auto;
		    }
		}

		p {
		    text-align: center;
		    margin-top: 30px;
		}
		p a {
		    color: #1976d2;
		    text-decoration: none;
		    font-weight: 600;
		}
		p a:hover {
		    text-decoration: underline;
		}
    </style>
</head>  
<body>

    <h1>Meus Produtos</h1>

    <c:choose>
        <c:when test="${not empty products}">
            <table>
                <thead>
                    <tr>
                        <th>Nome</th>
                        <th>Descrição</th>
                        <th>Preço</th>
                        <th>Descontos</th>
                        <th>Cupons</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.name}</td>
                            <td>${product.description}</td>
                            <td>R$ ${product.price}</td>
                            <td class="discounts">
                                <c:if test="${not empty product.discounts}">
                                    <ul>
                                        <c:forEach var="discount" items="${product.discounts}">
                                            <li>${discount.value}%</li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${empty product.discounts}">
                                    -
                                </c:if>
                            </td>
                            <td class="coupons">
                                <c:if test="${not empty product.coupons}">
                                    <ul>
                                        <c:forEach var="coupon" items="${product.coupons}">
                                            <li>${coupon.name} (${coupon.code}) - ${coupon.discount.value}%</li>
                                        </c:forEach>
                                    </ul>
                                </c:if>
                                <c:if test="${empty product.coupons}">
                                    -
                                </c:if>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/products/${product.id}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este produto?');">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button type="submit">Excluir</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <p>Você ainda não cadastrou nenhum produto.</p>
        </c:otherwise>
    </c:choose>
    
	<div class="btn-container">
	    <a href="${pageContext.request.contextPath}/products/new" class="btn btn-add">Adicionar Produto</a>
	</div>
	<p><a href="${pageContext.request.contextPath}/suppliers/info">Voltar</a></p>

</body>
</html>
