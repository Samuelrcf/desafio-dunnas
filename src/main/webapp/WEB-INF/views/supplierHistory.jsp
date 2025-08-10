<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Histórico de Pedidos Recebidos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 25px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            margin-bottom: 20px;
            background: white;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
            border-radius: 6px;
            overflow: hidden;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 12px 15px;
            vertical-align: top;
            text-align: left;
        }
        th {
            background-color: #1976d2;
            color: white;
            font-weight: 600;
        }
        .product-list {
            margin: 0;
            padding-left: 20px;
            list-style-type: disc;
        }
        .pagination {
            text-align: center;
            margin-top: 20px;
        }
        .pagination a, .pagination span {
            padding: 6px 12px;
            margin: 0 3px;
            border: 1px solid #ccc;
            text-decoration: none;
            color: #1976d2;
            border-radius: 4px;
            font-weight: 500;
            display: inline-block;
        }
        .pagination .active {
            background-color: #1976d2;
            color: white;
            border-color: #1976d2;
        }
        .pagination a:hover {
            background-color: #145ca4;
            color: white;
            border-color: #145ca4;
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
    <h1>Histórico de Pedidos Recebidos</h1>

    <c:if test="${empty ordersPage.content}">
        <p>Não há pedidos recebidos.</p>
    </c:if>

    <c:if test="${not empty ordersPage.content}">
        <table>
            <thead>
                <tr>
                    <th>Código do Pedido</th>
                    <th>Cliente</th>
                    <th>Produtos</th>
                    <th>Total</th>
                    <th>Data do Pedido</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="order" items="${ordersPage.content}">
                    <tr>
                        <td>${order.orderCode}</td>
                        <td>${order.clientName}</td>
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
                        <td>R$ <fmt:formatNumber value="${order.total}" type="number" minFractionDigits="2" /></td>
						<td>
						  <c:set var="dt" value="${fn:substring(order.creationDate.toString(), 0, 19)}" />
						  <c:set var="datePart" value="${fn:substring(dt, 0, 10)}" />
						  <c:set var="timePart" value="${fn:substring(dt, 11, 19)}" />

						  <c:set var="day" value="${fn:substring(datePart, 8, 10)}" />
						  <c:set var="month" value="${fn:substring(datePart, 5, 7)}" />
						  <c:set var="year" value="${fn:substring(datePart, 0, 4)}" />

						  <c:out value="${day}/${month}/${year} ${timePart}" />
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

    <p><a href="${pageContext.request.contextPath}/suppliers/info">Voltar</a></p>
</body>
</html>
