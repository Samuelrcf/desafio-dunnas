<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Lista de Produtos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f6f8;
            padding: 20px;
            margin: 0;
        }
        a.back-btn {
            display: inline-block;
            margin-bottom: 20px;
            text-decoration: none;
            color: #1976d2;
            font-weight: bold;
        }
        a.back-btn:hover {
            text-decoration: underline;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        #productsContainer {
            max-width: 900px;
            margin: 0 auto 80px auto;
            display: flex;
            flex-direction: column;
            gap: 20px;
        }
        .product-card {
            background: white;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
            display: flex;
            flex-direction: column;
        }
        .product-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 8px;
        }
        .product-name {
            font-size: 1.2em;
            font-weight: bold;
            color: #222;
        }
        .price {
            font-size: 1.1em;
            color: #1976d2;
            font-weight: 600;
        }
        .product-description {
            color: #555;
            margin-bottom: 15px;
            min-height: 40px;
        }
        .discounts, .coupons {
            margin-bottom: 10px;
            font-size: 0.9em;
            color: #444;
        }
        .discounts strong, .coupons strong {
            color: #d32f2f;
        }
        .quantity-control {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
        }
        .quantity-control button {
            width: 30px;
            height: 30px;
            border: 1px solid #ccc;
            background: white;
            cursor: pointer;
            font-size: 1.2em;
            line-height: 1;
            user-select: none;
            border-radius: 4px;
            color: #333;
            transition: background-color 0.2s;
        }
        .quantity-control button:hover {
            background-color: #1976d2;
            color: white;
            border-color: #1976d2;
        }
        .qty-input {
            width: 50px;
            margin: 0 10px;
            padding: 5px;
            font-size: 1em;
            border: 1px solid #ccc;
            border-radius: 4px;
            text-align: center;
        }
        .add-cart-btn {
            background-color: #1976d2;
            border: none;
            color: white;
            padding: 10px 0;
            font-size: 1em;
            font-weight: 600;
            border-radius: 6px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .add-cart-btn:hover {
            background-color: #145ca4;
        }

        /* Botão fixo carrinho */
        #openCartBtn {
            position: fixed;
            bottom: 20px;
            right: 20px;
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 30px;
            font-weight: bold;
            cursor: pointer;
            box-shadow: 0 4px 10px rgba(25, 118, 210, 0.4);
            transition: background-color 0.3s ease;
            z-index: 1000;
        }
        #openCartBtn:hover {
            background-color: #145ca4;
        }

        /* Modal carrinho */
        .modal {
            display: none;
            position: fixed;
            z-index: 1100;
            left: 0; top: 0;
            width: 100%; height: 100%;
            background-color: rgba(0,0,0,0.5);
            overflow: auto;
        }
        .modal-content {
            background-color: #fff;
            margin: 60px auto;
            padding: 20px 30px 30px 30px;
            border-radius: 8px;
            max-width: 500px;
            position: relative;
            box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        }
        .close-modal {
            position: absolute;
            top: 12px;
            right: 16px;
            font-size: 24px;
            font-weight: bold;
            color: #888;
            cursor: pointer;
            user-select: none;
        }
        .close-modal:hover {
            color: #333;
        }
        #cartItemsContainer {
            max-height: 300px;
            overflow-y: auto;
            margin-bottom: 20px;
        }
        .cart-item {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid #eee;
            font-size: 1em;
            color: #444;
        }
        .cart-item-name {
            font-weight: 600;
            color: #222;
        }
        .cart-item-quantity {
			margin-left: auto;
            color: #555;
        }
        .cart-total {
            font-size: 1.2em;
            font-weight: 700;
            margin-bottom: 15px;
            text-align: right;
            color: #1976d2;
        }
		.remove-item-btn {
		    background: none;
		    border: none;
		    color: red;
		    font-size: 16px;
		    cursor: pointer;
		}
        .finalize-btn {
            background-color: #1976d2;
            color: white;
            border: none;
            padding: 12px 0;
            width: 100%;
            font-size: 1.1em;
            border-radius: 6px;
            cursor: pointer;
            font-weight: 600;
            transition: background-color 0.3s ease;
        }
        .finalize-btn:disabled {
            background-color: #aaa;
            cursor: not-allowed;
        }
        .finalize-btn:hover:enabled {
            background-color: #145ca4;
        }
		.alert {
		    padding: 12px 20px;
		    margin: 15px 0;
		    border-radius: 5px;
		    font-weight: 600;
		    font-size: 1em;
		    width: 100%;
		    box-sizing: border-box;
		}

		.alert-success {
		    background-color: #d4edda;  
		    color: #155724;             
		    border: 1px solid #c3e6cb;
		}

		.alert-danger {
		    background-color: #f8d7da;  
		    color: #721c24;             
		    border: 1px solid #f5c6cb;
		}
		input[type=number]::-webkit-inner-spin-button, 
		input[type=number]::-webkit-outer-spin-button {
		    -webkit-appearance: none;
		    margin: 0;
		}
		input[type=number] {
		    -moz-appearance: textfield;
		}
    </style>
</head>
<body>

<a href="${pageContext.request.contextPath}/clients/info" class="back-btn">&larr; Voltar</a>

<h1>Produtos</h1>

<div id="productsContainer">
    <c:forEach var="product" items="${products}">
        <div class="product-card" data-id="${product.id}" data-name="${product.name}" data-price="${product.price}">
            <div class="product-header">
                <span class="product-name">${product.name}</span>
                <span class="price">R$ <fmt:formatNumber value="${product.price}" type="number" minFractionDigits="2" /></span>
            </div>
            <div class="product-description">${product.description}</div>

            <c:if test="${not empty product.discounts}">
                <div class="discounts">
                    <strong>Desconto(s):</strong>
                    <ul>
                        <c:forEach var="discount" items="${product.discounts}">
                            <li>
                                <c:out value="${discount.description}"/> - R$ 
                                <fmt:formatNumber value="${discount.value}" type="number" minFractionDigits="2"/>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <c:if test="${not empty product.coupons}">
                <div class="coupons">
                    <strong>Cupons:</strong>
                    <ul>
                        <c:forEach var="coupon" items="${product.coupons}">
                            <li>
                                <c:out value="${coupon.name}"/> (Código: <c:out value="${coupon.code}"/>) - 
                                <c:choose>
                                    <c:when test="${not empty coupon.discount}">
                                        R$ <fmt:formatNumber value="${coupon.discount.value}" type="number" minFractionDigits="2"/>
                                    </c:when>
                                    <c:otherwise>-</c:otherwise>
                                </c:choose>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </c:if>

            <div class="quantity-control">
                <button type="button" class="qty-decrease">-</button>
                <input type="number" min="1" value="1" class="qty-input" />
                <button type="button" class="qty-increase">+</button>
            </div>

            <button type="button" class="add-cart-btn">Adicionar ao carrinho</button>
        </div>
    </c:forEach>
	<c:if test="${not empty success}">
	    <div class="alert alert-success">${success}</div>
	</c:if>

	<c:if test="${not empty error}">
	    <div class="alert alert-danger">${error}</div>
	</c:if>

</div>

<!-- Botão fixo para abrir carrinho -->
<button id="openCartBtn">Carrinho (0)</button>

<!-- Modal do carrinho -->
<div id="cartModal" class="modal" role="dialog" aria-modal="true" aria-labelledby="cartTitle">
    <div class="modal-content">
        <button class="close-modal" title="Fechar" aria-label="Fechar">&times;</button>
        <h2 id="cartTitle">Carrinho de Compras</h2>
        <div id="cartItemsContainer">
            <!-- Itens do carrinho serão inseridos aqui -->
        </div>
        <div class="cart-total" id="cartTotal">Total: R$ 0,00</div>

        <form id="checkoutForm" method="post" action="${pageContext.request.contextPath}/orders/create">
            <!-- Campos hidden para enviar os produtos e quantidades -->
            <div id="hiddenInputsContainer"></div>
            <button type="submit" class="finalize-btn" disabled>Finalizar Pedido</button>
        </form>
    </div>
</div>

<script>
    const cart = {};

    function formatPrice(price) {
        return price.toFixed(2).replace('.', ',');
    }

    function updateCartButton() {
        const count = Object.values(cart).reduce((sum, item) => sum + item.quantity, 0);
        document.getElementById('openCartBtn').textContent = 'Carrinho (' + count + ')';
    }

    function removeFromCart(productId) {
        delete cart[productId];
        updateCartModal();
        updateCartButton();
    }

    function updateCartModal() {
        const container = document.getElementById('cartItemsContainer');
        container.innerHTML = '';

        let total = 0;
        for (const productId in cart) {
            const item = cart[productId];
            const itemTotal = item.price * item.quantity;
            total += itemTotal;

            const div = document.createElement('div');
            div.className = 'cart-item';

            div.innerHTML =
                '<span class="cart-item-name">' + item.name + '</span>' +
                '<span class="cart-item-quantity">x ' + item.quantity + ' (R$ ' + formatPrice(itemTotal) + ')</span>' +
                '<button class="remove-item-btn" title="Remover" onclick="removeFromCart(\'' + productId + '\')">&times;</button>';

            container.appendChild(div);
        }

        document.getElementById('cartTotal').textContent = 'Total: R$ ' + formatPrice(total);

        const hiddenContainer = document.getElementById('hiddenInputsContainer');
        hiddenContainer.innerHTML = '';

        for (const productId in cart) {
            const item = cart[productId];

            const inputId = document.createElement('input');
            inputId.type = 'hidden';
            inputId.name = 'productIds';
            inputId.value = productId;
            hiddenContainer.appendChild(inputId);

            const inputQty = document.createElement('input');
            inputQty.type = 'hidden';
            inputQty.name = 'quantities';
            inputQty.value = item.quantity;
            hiddenContainer.appendChild(inputQty);
        }

        const checkoutBtn = document.querySelector('#checkoutForm button[type="submit"]');
        checkoutBtn.disabled = Object.keys(cart).length === 0;
    }

    // Abrir modal carrinho
    document.getElementById('openCartBtn').addEventListener('click', () => {
        updateCartModal();
        document.getElementById('cartModal').style.display = 'block';
    });

    // Fechar modal carrinho
    document.querySelector('.close-modal').addEventListener('click', () => {
        document.getElementById('cartModal').style.display = 'none';
    });

    // Fecha modal ao clicar fora do conteúdo
    window.addEventListener('click', (e) => {
        const modal = document.getElementById('cartModal');
        if (e.target === modal) {
            modal.style.display = 'none';
        }
    });

    // Controles de quantidade e adicionar ao carrinho
    document.querySelectorAll('.product-card').forEach(card => {
        const decreaseBtn = card.querySelector('.qty-decrease');
        const increaseBtn = card.querySelector('.qty-increase');
        const qtyInput = card.querySelector('.qty-input');

        decreaseBtn.addEventListener('click', () => {
            let val = parseInt(qtyInput.value);
            if (val > 1) qtyInput.value = val - 1;
        });

        increaseBtn.addEventListener('click', () => {
            let val = parseInt(qtyInput.value);
            qtyInput.value = val + 1;
        });

        card.querySelector('.add-cart-btn').addEventListener('click', () => {
            const productId = card.getAttribute('data-id');
            const productName = card.getAttribute('data-name');
            const productPrice = parseFloat(card.getAttribute('data-price'));
            const quantity = parseInt(qtyInput.value);

            if (quantity < 1) {
                alert('Quantidade deve ser pelo menos 1.');
                return;
            }

            if (cart[productId]) {
                cart[productId].quantity += quantity;
            } else {
                cart[productId] = { name: productName, price: productPrice, quantity: quantity };
            }

            updateCartButton();
            alert('Adicionado ' + quantity + ' x ' + productName + ' ao carrinho.');
        });
    });
</script>

</body>
</html>
