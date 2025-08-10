<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Histórico de Pedidos</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9fafb;
            margin: 0;
            padding: 30px 15px;
            display: flex;
            justify-content: center;
        }
        .container {
            width: 100%;
            max-width: 900px;
            background: white;
            padding: 25px 30px 35px 30px;
            border-radius: 12px;
            box-shadow: 0 6px 20px rgba(0,0,0,0.1);
        }
        h1 {
            text-align: center;
            margin-bottom: 28px;
            color: #1f2937;
            font-weight: 700;
            font-size: 28px;
            letter-spacing: 0.02em;
        }
        table {
            border-collapse: separate;
            border-spacing: 0 12px;
            width: 100%;
            font-size: 15px;
        }
        thead tr {
            background-color: #2563eb;
            color: white;
            border-radius: 8px;
        }
        thead th {
            padding: 14px 18px;
            font-weight: 600;
            user-select: none;
            text-align: center; /* centraliza cabeçalho */
        }
        tbody tr {
            background-color: #f3f4f6;
            border-radius: 10px;
            box-shadow: inset 0 0 8px #e0e7ff;
            transition: background-color 0.3s ease;
        }
        tbody tr:hover {
            background-color: #e0e7ff;
        }
        tbody td {
            padding: 14px 18px;
            vertical-align: top;
            border: none;
        }
        .product-list {
            margin: 0;
            padding-left: 18px;
            list-style-type: disc;
            color: #374151;
            max-width: 320px;
        }
        .product-list li {
            margin-bottom: 6px;
            line-height: 1.3;
        }
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 28px;
            gap: 6px;
            flex-wrap: wrap;
        }
        .pagination a, .pagination span {
            padding: 8px 14px;
            border-radius: 8px;
            border: 1px solid #d1d5db;
            color: #2563eb;
            font-weight: 600;
            text-decoration: none;
            user-select: none;
            transition: background-color 0.3s ease, color 0.3s ease;
        }
        .pagination a:hover {
            background-color: #2563eb;
            color: white;
            border-color: #2563eb;
        }
        .pagination .active {
            background-color: #2563eb;
            color: white;
            border-color: #2563eb;
            cursor: default;
        }
        p.back-link {
            text-align: center;
            margin-top: 30px;
        }
        p.back-link a {
            text-decoration: none;
            color: #2563eb;
            font-weight: 600;
            font-size: 16px;
            border-bottom: 2px solid transparent;
            transition: border-color 0.25s ease;
        }
        p.back-link a:hover {
            border-color: #2563eb;
        }
        /* Responsividade */
        @media (max-width: 720px) {
            .product-list {
                max-width: 100%;
            }
            table thead tr {
                font-size: 13px;
            }
            table tbody td {
                padding: 12px 10px;
                font-size: 13px;
            }
        }
    </style>
</head>
<body>
<div class="container">
    <h1>Histórico de Pedidos</h1>

    <c:if test="${empty ordersPage.content}">
        <p style="text-align:center; color:#6b7280; font-size:16px;">Você não possui pedidos realizados.</p>
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
                        <td style="font-family: monospace; color:#2563eb;">${order.orderCode}</td>
                        <td>${order.supplierName}</td>
                        <td>
                            <ul class="product-list">
                                <c:forEach var="product" items="${order.products}">
                                    <div style="margin-bottom: 12px; padding-bottom: 8px; border-bottom: 1px solid #ddd;">
                                        <div style="font-weight: bold; font-size: 1.05em; color: #333;">${product.name}</div>
                                        <div style="color: #555; font-style: italic; margin-bottom: 4px;">${product.description}</div>
                                        <div>
                                            <span style="font-weight: bold;">Preço:</span> R$ <fmt:formatNumber value="${product.price}" type="number" minFractionDigits="2"/>
                                            &nbsp;&nbsp;&nbsp;
                                            <span style="font-weight: bold;">Quantidade:</span> <fmt:formatNumber value="${product.quantity}" type="number"/>
                                        </div>
                                    </div>
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

    <p class="back-link"><a href="${pageContext.request.contextPath}/clients/info">Voltar</a></p>
</div>
</body>
</html>
