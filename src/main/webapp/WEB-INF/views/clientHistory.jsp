<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Histórico de Pedidos</title>
    <style>
        table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }
        th, td { border: 1px solid #ddd; padding: 8px; vertical-align: top; }
        th { background-color: #f2f2f2; }
        .pagination { margin-top: 20px; }
        .pagination a, .pagination span {
            padding: 5px 10px; margin: 0 2px; border: 1px solid #ccc; text-decoration: none;
        }
        .pagination .active { background-color: #007BFF; color: white; }
        .product-list { margin: 0; padding-left: 20px; }
    </style>
</head>
<body>
    <h1>Histórico de Pedidos</h1>

    <c:if test="${empty ordersPage.content}">
        <p>Você não possui pedidos realizados.</p>
    </c:if>

    <c:if test="${not empty ordersPage.content}">
        <table>
            <thead>
                <tr>
                    <th>Código do Pedido</th>
                    <th>Fornecedor</th>
                    <th>Produtos</th>
                    <th>Total</th>
                    <th>Data de Criação</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${ordersPage.content}">
                    <tr>
                        <td>${order.orderCode}</td>
                        <td>${order.supplierName}</td>
                        <td>
                            <ul class="product-list">
                                <c:forEach var="product" items="${order.products}">
                                    <li>
                                        <strong>${product.name}</strong> - ${product.description}
                                        (R$ <fmt:formatNumber value="${product.price}" type="number" minFractionDigits="2" />)
                                        (<fmt:formatNumber value="${product.quantity}" type="number"/>)
                                    </li>
                                </c:forEach>
                            </ul>
                        </td>
                        <td>
                            R$ <fmt:formatNumber value="${order.total}" type="number" minFractionDigits="2" />
                        </td>
                        <td>
							<c:out value="${order.creationDate}" />
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Paginação -->
        <div class="pagination">
            <c:if test="${ordersPage.totalPages > 1}">
                <c:forEach var="i" begin="0" end="${ordersPage.totalPages - 1}">
                    <c:choose>
                        <c:when test="${i == ordersPage.number}">
                            <span class="active">${i + 1}</span>
                        </c:when>
                        <c:otherwise>
                            <a href="?page=${i}&size=${ordersPage.size}">${i + 1}</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:if>
        </div>
    </c:if>

    <p><a href="${pageContext.request.contextPath}/products">Voltar para produtos</a></p>
</body>
</html>
