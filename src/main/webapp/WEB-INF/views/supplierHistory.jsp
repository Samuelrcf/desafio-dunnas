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
        /* Reset simples */
        *, *::before, *::after {
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f9fafb;
            margin: 0;
            padding: 30px 20px;
            color: #333;
            -webkit-font-smoothing: antialiased;
            -moz-osx-font-smoothing: grayscale;
        }

        h1 {
            text-align: center;
            color: #222;
            margin-bottom: 40px;
            font-weight: 700;
            font-size: 2.4rem;
        }

        table {
            width: 100%;
            border-collapse: separate;
            border-spacing: 0 10px;
            background: transparent;
        }

        thead tr th {
            background-color: #2563eb; /* azul mais vivo */
            color: #fff;
            font-weight: 600;
            padding: 15px 20px;
            text-align: left;
            border-radius: 8px 8px 0 0;
            user-select: none;
            letter-spacing: 0.02em;
            box-shadow: 0 2px 5px rgb(37 99 235 / 0.3);
        }

        tbody tr {
            background-color: #ffffff;
            box-shadow: 0 1px 3px rgb(0 0 0 / 0.1);
            transition: background-color 0.2s ease;
            border-radius: 8px;
        }
        tbody tr:hover {
            background-color: #eff6ff;
        }

        tbody tr td {
            padding: 15px 20px;
            vertical-align: middle;
            border: none;
            font-size: 0.95rem;
            color: #444;
        }

        tbody tr + tr {
            margin-top: 10px;
        }

        /* Lista de produtos no pedido */
        .product-list {
            margin: 0;
            padding-left: 18px;
            list-style-type: disc;
            max-width: 320px;
        }
        .product-list li {
            margin-bottom: 6px;
            line-height: 1.3;
            color: #555;
        }
        .product-list strong {
            color: #222;
        }

        /* Paginação */
        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 30px;
            gap: 8px;
            flex-wrap: wrap;
            user-select: none;
        }
        .pagination a, .pagination span {
            padding: 8px 14px;
            border-radius: 6px;
            border: 1.8px solid transparent;
            font-weight: 600;
            font-size: 0.9rem;
            cursor: pointer;
            transition: all 0.3s ease;
            min-width: 36px;
            text-align: center;
            color: #2563eb;
            background-color: #e0e7ff;
            text-decoration: none;
            box-shadow: 0 2px 4px rgb(37 99 235 / 0.2);
        }
        .pagination a:hover {
            background-color: #1e40af;
            color: white;
            box-shadow: 0 4px 8px rgb(30 64 175 / 0.4);
            border-color: #1e40af;
        }
        .pagination .active {
            background-color: #2563eb;
            color: white;
            border-color: #2563eb;
            cursor: default;
            box-shadow: 0 4px 10px rgb(37 99 235 / 0.6);
        }

        /* Mensagem sem pedidos */
        p {
            text-align: center;
            font-size: 1.15rem;
            color: #666;
            margin-top: 40px;
        }
        p a {
            color: #2563eb;
            font-weight: 600;
            text-decoration: none;
            transition: color 0.3s ease;
        }
        p a:hover {
            text-decoration: underline;
            color: #1e40af;
        }

        /* Ajuste responsivo */
        @media (max-width: 768px) {
            table, thead, tbody, tr, th, td {
                display: block;
            }
            thead tr {
                display: none;
            }
            tbody tr {
                margin-bottom: 20px;
                box-shadow: none;
                background-color: transparent;
                border-radius: 0;
            }
            tbody tr td {
                padding: 10px 10px;
                text-align: right;
                position: relative;
                border-bottom: 1px solid #ddd;
                font-size: 0.9rem;
            }
            tbody tr td::before {
                content: attr(data-label);
                position: absolute;
                left: 10px;
                width: 50%;
                font-weight: 700;
                color: #2563eb;
                text-align: left;
            }
            .product-list {
                max-width: 100%;
                padding-left: 10px;
            }
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
                        <td data-label="Código do Pedido">${order.orderCode}</td>
                        <td data-label="Cliente">${order.clientName}</td>
                        <td data-label="Produtos">
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
                        <td data-label="Total">R$ <fmt:formatNumber value="${order.total}" type="number" minFractionDigits="2" /></td>
                        <td data-label="Data do Pedido">
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
