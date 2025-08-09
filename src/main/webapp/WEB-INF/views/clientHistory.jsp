<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Histórico de Pedidos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f9fafb;
            padding: 20px;
        }
        .orders-container {
            max-width: 900px;
            margin: auto;
            background: white;
            padding: 20px 25px;
            border-radius: 8px;
            box-shadow: 0 2px 8px rgba(0,0,0,0.1);
        }
        h2 {
            text-align: center;
            margin-bottom: 20px;
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        thead th {
            background: #1976d2;
            color: white;
            padding: 10px;
            text-align: left;
        }
        tbody td {
            border-bottom: 1px solid #ddd;
            padding: 10px;
            vertical-align: top;
        }
        tbody tr:hover {
            background: #f0f7ff;
        }
        .pagination {
            text-align: center;
        }
        .page-link {
            display: inline-block;
            padding: 8px 12px;
            margin: 0 5px;
            background: #1976d2;
            color: white;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }
        .page-link.disabled {
            background: #ccc;
            cursor: default;
        }
        .products-list {
            margin: 0;
            padding-left: 15px;
            list-style-type: disc;
            font-size: 0.9em;
            color: #555;
        }
    </style>
</head>
<body>
<div class="orders-container">
    <h2>Histórico de Pedidos</h2>

    <table>
        <thead>
            <tr>
                <th>Código do Pedido</th>
                <th>Fornecedor</th>
                <th>Produtos</th>
                <th>Total (R$)</th>
                <th>Data do Pedido</th>
            </tr>
        </thead>
        <tbody id="ordersTableBody">
            <!-- Conteúdo será carregado via JS -->
        </tbody>
    </table>

    <div class="pagination">
        <a href="#" id="prevPage" class="page-link">Anterior</a>
        <span id="pageInfo" style="margin: 0 10px;"></span>
        <a href="#" id="nextPage" class="page-link">Próximo</a>
    </div>
</div>

<script>
    const ordersTableBody = document.getElementById('ordersTableBody');
    const prevPageBtn = document.getElementById('prevPage');
    const nextPageBtn = document.getElementById('nextPage');
    const pageInfo = document.getElementById('pageInfo');

    let currentPage = 0;
    const pageSize = 10; // default conforme seu controlador

    function formatDateTime(dateTimeStr) {
        const dt = new Date(dateTimeStr);
        return dt.toLocaleString('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric',
            hour: '2-digit',
            minute: '2-digit'
        });
    }

    function loadOrders(page) {
        fetch(`${window.location.origin}/clients/history?page=${page}&size=${pageSize}`)
            .then(response => {
                if (!response.ok) throw new Error('Erro ao carregar histórico');
                return response.json();
            })
            .then(data => {
                const ordersPage = data.data;
                ordersTableBody.innerHTML = '';

                if (ordersPage.content.length === 0) {
                    ordersTableBody.innerHTML = `<tr><td colspan="5" style="text-align:center;">Nenhum pedido encontrado.</td></tr>`;
                } else {
                    ordersPage.content.forEach(order => {
                        const productsHtml = order.products.map(p =>
                            `<li><strong>${p.name}</strong>: ${p.description}</li>`
                        ).join('');
                        ordersTableBody.innerHTML += `
                            <tr>
                                <td>${order.orderCode}</td>
                                <td>${order.supplierName}</td>
                                <td><ul class="products-list">${productsHtml}</ul></td>
                                <td>R$ ${order.total.toFixed(2)}</td>
                                <td>\${formatDateTime(order.creationDate)}</td>
                            </tr>`;
                    });
                }

                currentPage = ordersPage.number;
                pageInfo.textContent = `Página ${currentPage + 1} de ${ordersPage.totalPages}`;

                prevPageBtn.classList.toggle('disabled', currentPage === 0);
                nextPageBtn.classList.toggle('disabled', currentPage + 1 >= ordersPage.totalPages);
            })
            .catch(err => {
                ordersTableBody.innerHTML = `<tr><td colspan="5" style="color:red; text-align:center;">${err.message}</td></tr>`;
                pageInfo.textContent = '';
                prevPageBtn.classList.add('disabled');
                nextPageBtn.classList.add('disabled');
            });
    }

    prevPageBtn.addEventListener('click', e => {
        e.preventDefault();
        if (currentPage > 0) loadOrders(currentPage - 1);
    });

    nextPageBtn.addEventListener('click', e => {
        e.preventDefault();
        loadOrders(currentPage + 1);
    });

    // Inicializa carregando a primeira página
    loadOrders(0);
</script>
</body>
</html>
