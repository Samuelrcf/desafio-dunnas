<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Meus Produtos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background: #f8f8f8;
        }
        h1 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
            background: white;
        }
        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }
        th {
            background: #eee;
        }
        .discounts, .coupons {
            font-size: 0.9em;
            color: #555;
        }
        .discounts ul, .coupons ul {
            margin: 0;
            padding-left: 15px;
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
				                    <button type="submit" style="background-color: red; color: white; border: none; padding: 5px 10px; cursor: pointer;">
				                        Excluir
				                    </button>
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
	<a href="${pageContext.request.contextPath}/products/new" class="btn btn-primary">
	    Adicionar Produto
	</a>
</body>
</html>
