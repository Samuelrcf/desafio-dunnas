<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Lista de Produtos</title>
    <style>
        table { border-collapse: collapse; width: 100%; }
        th, td { border: 1px solid #ddd; padding: 8px; }
        th { background-color: #f2f2f2; }
        .discounts, .coupons { margin-left: 20px; font-size: 0.9em; }
        .error { color: red; }
    </style>
</head>
<body>
    <h1>Produtos</h1>
    
    <c:if test="${not empty error}">
        <p class="error">${error}</p>
    </c:if>

    <form method="post" action="${pageContext.request.contextPath}/orders/create">
        <table>
            <thead>
                <tr>
                    <th>Nome</th>
                    <th>Descrição</th>
                    <th>Preço</th>
                    <th>Quantidade</th>
                    <th>Descontos</th>
                    <th>Cupons</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.name}</td>
                        <td>${product.description}</td>
                        <td>R$ ${product.price}</td>
                        <td>
                            <input type="number" min="0" name="quantity_${product.id}" value="0" />
                        </td>
                        <td>
                            <c:if test="${not empty product.discounts}">
                            </c:if>
                            <c:if test="${empty product.discounts}">
                                -
                            </c:if>
                        </td>
                        <td>
                            <c:if test="${not empty product.coupons}">
                                <ul class="coupons">
                                    <c:forEach var="coupon" items="${product.coupons}">
                                        <li>
                                            Nome: ${coupon.name} <br/>
                                            Código: ${coupon.code} <br/>
                                            Desconto: 
                                            <c:choose>
                                                <c:when test="${not empty coupon.discount}">
                                                    R$ ${coupon.discount.value}
                                                </c:when>
                                                <c:otherwise>-</c:otherwise>
                                            </c:choose>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty product.coupons}">
                                -
                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <br/>
        <button type="submit">Fazer Pedido</button>
    </form>
</body>
</html>
