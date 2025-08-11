<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<c:url var="baseUrl" value="/" />

<html>
<head>
  <title>Meus Produtos</title>
  <%-- Recomendo fortemente mover este CSS para um arquivo .css externo --%>
  <style>
    body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; margin: 30px auto; background-color: #f9fafb; max-width: 1100px; padding: 0 20px; color: #1f2937; }
    h1 { font-weight: 700; font-size: 2.2rem; margin-bottom: 24px; text-align: center; letter-spacing: 0.03em; }
    .btn-container { text-align: center; margin-top: 20px; }
    a.btn { display: inline-block; padding: 12px 28px; background-color: #2563eb; color: white; font-weight: 700; font-size: 16px; border-radius: 12px; text-decoration: none; user-select: none; box-shadow: 0 4px 12px rgb(37 99 235 / 0.4); transition: background-color 0.3s ease; text-align: center; }
    a.btn:hover { background-color: #1e40af; box-shadow: 0 6px 20px rgb(30 64 175 / 0.5); }
    table { width: 100%; border-collapse: separate; border-spacing: 0 12px; background: white; font-size: 15px; box-shadow: 0 6px 20px rgb(0 0 0 / 0.05); border-radius: 12px; overflow: hidden; }
    thead tr { background-color: #2563eb; color: white; font-weight: 600; }
    thead th { padding: 16px 20px; text-align: center; }
    tbody tr { background-color: #f3f4f6; transition: background-color 0.3s ease; }
    tbody tr:hover { background-color: #e0e7ff; }
    tbody td { padding: 14px 20px; text-align: center; vertical-align: middle; }
    .actions-container { display: flex; gap: 8px; justify-content: center; align-items: center; }
    .actions-container form { margin: 0; }
    .btn-action { border: none; padding: 8px 16px; border-radius: 8px; font-weight: 600; font-size: 14px; cursor: pointer; transition: background-color 0.25s ease; color: white; }
    .btn-delete { background-color: #ef4444; }
    .btn-delete:hover { background-color: #b91c1c; }
    .btn-discount { background-color: #10b981; }
    .btn-discount:hover { background-color: #059669; }
    .btn-coupon { background-color: #8b5cf6; }
    .btn-coupon:hover { background-color: #6d28d9; }
    .btn-remove-discount, .btn-remove-coupon { background-color: #f59e0b; }
    .btn-remove-discount:hover, .btn-remove-coupon:hover { background-color: #b45309; }
    .modal { display: none; position: fixed; z-index: 9999; left: 0; top: 0; width: 100%; height: 100%; background-color: rgba(0,0,0,0.4); align-items: center; justify-content: center; }
    .modal .modal-content { background: white; padding: 20px; border-radius: 12px; width: 380px; box-shadow: 0 4px 15px rgba(0,0,0,0.3); }
    .modal label { display: block; margin-top: 10px; font-weight: 500; }
    .modal input { width: 100%; box-sizing: border-box; padding: 8px; margin-top: 4px; border-radius: 6px; border: 1px solid #ccc; }
    .modal-actions { margin-top: 20px; display: flex; gap: 8px; justify-content: flex-end; }
    .btn-save { background-color: #2563eb; color: white; border: none; padding: 8px 12px; border-radius: 6px; cursor: pointer; }
    .btn-save:hover { background-color: #1e40af; }
    .btn-cancel { background-color: #9ca3af; color: white; border: none; padding: 8px 12px; border-radius: 6px; cursor: pointer; }
    .btn-cancel:hover { background-color: #6b7280; }
  </style>
</head>
<body>

<h1>Meus Produtos</h1>

<%-- Bloco para exibir mensagens de feedback do Controller --%>
<c:if test="${not empty successMessage}">
  <div style="padding: 15px; background-color: #d4edda; color: #155724; border: 1px solid #c3e6cb; border-radius: 8px; margin-bottom: 20px;">
      ${successMessage}
  </div>
</c:if>
<c:if test="${not empty errorMessage}">
  <div style="padding: 15px; background-color: #f8d7da; color: #721c24; border: 1px solid #f5c6cb; border-radius: 8px; margin-bottom: 20px;">
      ${errorMessage}
  </div>
</c:if>

<c:choose>
  <c:when test="${not empty products}">
    <table>
      <thead>
      <tr>
        <th>Nome</th>
        <th>Descrição</th>
        <th>Preço</th>
        <th>Desconto</th>
        <th>Cupom</th>
        <th>Ações</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="product" items="${products}">
        <tr>
          <td>${product.name}</td>
          <td>${product.description}</td>
          <td><fmt:formatNumber value="${product.price}" type="currency" currencySymbol="R$ "/></td>
          <td>
            <c:choose>
              <c:when test="${not empty product.discount}">
                <fmt:formatNumber value="${product.discount.value * 100}" maxFractionDigits="0"/>%
              </c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose>
          </td>
          <td>
            <c:choose>
              <c:when test="${not empty product.coupon}">
                <span style="font-weight: bold; color: #6d28d9;">${product.coupon.code}</span>
				<fmt:formatNumber value="${product.coupon.discount.value * 100}" maxFractionDigits="0"/>%
              </c:when>
              <c:otherwise>-</c:otherwise>
            </c:choose>
          </td>
          <td>
            <div class="actions-container">
                <%-- Botão de Excluir --%>
              <c:url var="deleteUrl" value="/products/${product.id}"/>
              <form action="${deleteUrl}" method="post" onsubmit="return confirm('Tem certeza que deseja excluir este produto?');">
                <input type="hidden" name="_method" value="DELETE" />
                <button type="submit" class="btn-action btn-delete">Excluir</button>
              </form>

                <%-- Botão de Desconto --%>
              <c:set var="currentDiscountValue" value=""/>
              <c:if test="${not empty product.discount}"><c:set var="currentDiscountValue"><fmt:formatNumber value="${product.discount.value * 100}" maxFractionDigits="0" /></c:set></c:if>
              <button type="button" class="btn-action btn-discount" onclick="openDiscountModal('${product.id}', '${product.name}', '${currentDiscountValue}')">
                <c:choose>
                  <c:when test="${not empty product.discount}">Editar Desconto</c:when>
                  <c:otherwise>Adicionar Desconto</c:otherwise>
                </c:choose>
              </button>

                <%-- Botão de Cupom --%>
              <c:set var="currentCouponId" value=""/>
              <c:set var="currentCouponCode" value=""/>
              <c:set var="currentCouponValue" value=""/>
              <c:if test="${not empty product.coupon}">
                <c:set var="currentCouponId" value="${product.coupon.id}"/>
                <c:set var="currentCouponCode" value="${product.coupon.code}"/>
				<c:set var="currentCouponValue"><fmt:formatNumber value="${product.coupon.discount.value * 100}" maxFractionDigits="0" /></c:set>
              </c:if>
              <button type="button" class="btn-action btn-coupon" onclick="openCouponModal('${product.id}', '${product.name}', '${currentCouponId}', '${currentCouponCode}', '${currentCouponValue}')">
                <c:choose>
                  <c:when test="${not empty product.coupon}">Editar Cupom</c:when>
                  <c:otherwise>Adicionar Cupom</c:otherwise>
                </c:choose>
              </button>

            </div>
          </td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </c:when>
  <c:otherwise>
    <p style="text-align: center; margin-top: 20px;">Você ainda não cadastrou nenhum produto.</p>
  </c:otherwise>
</c:choose>

<div class="btn-container">
  <c:url var="newProductUrl" value="/products/new"/>
  <a href="${newProductUrl}" class="btn">Adicionar Produto</a>
</div>
<c:url var="supplierInfoUrl" value="/suppliers/info"/>
<p style="text-align: center; margin-top: 20px;"><a href="${supplierInfoUrl}">Voltar</a></p>

<!-- Modal de desconto (Existente) -->
<div id="discountModal" class="modal">
  <div class="modal-content">
    <h2 id="modalDiscountTitle">Adicionar Desconto</h2>
    <c:url var="discountFormUrl" value="/products/discount"/>
    <form id="discountForm" method="post" action="${discountFormUrl}">
      <input type="hidden" name="productId" id="discountProductIdHidden"/>
      <label for="discountProductName">Produto</label>
      <input type="text" id="discountProductName" disabled />
      <label for="discountValue">Valor do Desconto (%)</label>
      <input type="text" id="discountValue" placeholder="Ex: 25" required />
      <div class="modal-actions">
        <button type="submit" class="btn-save">Salvar</button>
        <button type="button" class="btn-cancel" onclick="closeDiscountModal()">Cancelar</button>
        <button type="button" id="removeDiscountBtn" class="btn-action btn-remove-discount" style="display: none;">Remover Desconto</button>
      </div>
    </form>
  </div>
</div>

<!-- Modal de Cupom -->
<div id="couponModal" class="modal">
  <div class="modal-content">
    <h2 id="modalCouponTitle">Adicionar Cupom</h2>
    <c:url var="couponFormUrl" value="/products/coupon"/>
    <form id="couponForm" method="post" action="${couponFormUrl}">
      <input type="hidden" name="productId" id="couponProductIdHidden"/>

      <label for="couponProductName">Produto</label>
      <input type="text" id="couponProductName" disabled />

      <%-- CAMPO ADICIONADO: Nome do cupom, necessário para o DTO --%>
      <label for="couponName">Nome do Cupom</label>
      <input type="text" name="name" id="couponName" placeholder="Ex: Cupom de Lançamento" required />

      <label for="couponCode">Código do Cupom</label>
      <input type="text" name="code" id="couponCode" placeholder="Ex: PROMO10" required />

      <label for="couponValue">Valor do Desconto do Cupom (%)</label>
      <input type="text" id="couponValue" placeholder="Ex: 10" required />

      <div class="modal-actions">
        <button type="submit" class="btn-save">Salvar</button>
        <button type="button" class="btn-cancel" onclick="closeCouponModal()">Cancelar</button>
        <button type="button" id="removeCouponBtn" class="btn-action btn-remove-coupon" style="display: none;">Remover Cupom</button>
      </div>
    </form>
  </div>
</div>


<script>
  // ===================================================================
  // LÓGICA PARA MODAL DE DESCONTO
  // ===================================================================
  function openDiscountModal(productId, productName, currentDiscount) {
    const modal = document.getElementById('discountModal');
    const title = document.getElementById('modalDiscountTitle');
    const nameInput = document.getElementById('discountProductName');
    const valueInput = document.getElementById('discountValue');
    const saveBtn = modal.querySelector('.btn-save');
    const removeBtn = document.getElementById('removeDiscountBtn');

    document.getElementById('discountProductIdHidden').value = productId;
    nameInput.value = productName;

    if (currentDiscount) {
      title.textContent = 'Editar Desconto';
      valueInput.value = currentDiscount;
      valueInput.disabled = true;
      saveBtn.style.display = 'none';
      removeBtn.style.display = 'inline-block';
      const removeUrl = '${baseUrl}products/discount/delete';
      removeBtn.onclick = () => removeDiscount(removeUrl, productId);
    } else {
      title.textContent = 'Adicionar Desconto';
      valueInput.value = '';
      valueInput.disabled = false;
      saveBtn.style.display = 'inline-block';
      removeBtn.style.display = 'none';
    }
    modal.style.display = 'flex';
  }

  function closeDiscountModal() {
    document.getElementById('discountModal').style.display = 'none';
  }

  document.getElementById('discountForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const percentValue = document.getElementById('discountValue').value;
    const decimalValue = (parseFloat(percentValue) || 0) / 100;
    const hiddenValueInput = document.createElement('input');
    hiddenValueInput.type = 'hidden';
    hiddenValueInput.name = 'value';
    hiddenValueInput.value = decimalValue.toFixed(2);
    form.appendChild(hiddenValueInput);
    form.submit();
  });

  function removeDiscount(actionUrl, productId) {
    if (!confirm('Tem certeza que deseja remover o desconto deste produto?')) return;
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = actionUrl;
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'productId';
    input.value = productId;
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
  }

  // ===================================================================
  // LÓGICA PARA MODAL DE CUPOM
  // ===================================================================
  function openCouponModal(productId, productName, couponId, currentCouponCode, currentCouponValue) {
    const modal = document.getElementById('couponModal');
    const title = document.getElementById('modalCouponTitle');
    const nameInput = document.getElementById('couponProductName');
    const couponNameInput = document.getElementById('couponName');
    const codeInput = document.getElementById('couponCode');
    const valueInput = document.getElementById('couponValue');
    const saveBtn = modal.querySelector('.btn-save');
    const removeBtn = document.getElementById('removeCouponBtn');

    document.getElementById('couponProductIdHidden').value = productId;
    nameInput.value = productName;

    if (currentCouponCode) {
      title.textContent = 'Editar Cupom';
      couponNameInput.value = ''; 
      codeInput.value = currentCouponCode;
      valueInput.value = currentCouponValue;

      couponNameInput.disabled = true;
      codeInput.disabled = true;
      valueInput.disabled = true;

      saveBtn.style.display = 'none';
      removeBtn.style.display = 'inline-block';

      const removeUrl = '${baseUrl}products/coupon/delete';
      removeBtn.onclick = () => removeCoupon(removeUrl, couponId);
    } else {
      // Se NÃO EXISTE um cupom
      title.textContent = 'Adicionar Cupom';
      couponNameInput.value = '';
      codeInput.value = '';
      valueInput.value = '';

      couponNameInput.disabled = false;
      codeInput.disabled = false;
      valueInput.disabled = false;

      saveBtn.style.display = 'inline-block';
      removeBtn.style.display = 'none';
    }
    modal.style.display = 'flex';
  }

  function closeCouponModal() {
    document.getElementById('couponModal').style.display = 'none';
  }

  document.getElementById('couponForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const form = e.target;
    const percentValue = document.getElementById('couponValue').value;
    const decimalValue = (parseFloat(percentValue) || 0) / 100;

    const hiddenValueInput = document.createElement('input');
    hiddenValueInput.type = 'hidden';
    hiddenValueInput.name = 'value';
    hiddenValueInput.value = decimalValue.toFixed(2);
    form.appendChild(hiddenValueInput);
    form.submit();
  });

  function removeCoupon(actionUrl, couponId) {
    if (!confirm('Tem certeza que deseja remover o cupom deste produto?')) return;
    const form = document.createElement('form');
    form.method = 'POST';
    form.action = actionUrl;
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'couponId';
    input.value = couponId;
    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
  }

  // ===================================================================
  // LÓGICA GERAL
  // ===================================================================
  window.onclick = function(event) {
    if (event.target.classList.contains('modal')) {
      closeDiscountModal();
      closeCouponModal();
    }
  }

  function validatePercentInput(inputId) {
    const input = document.getElementById(inputId);
    input.addEventListener('input', function (e) {
      let value = e.target.value.replace(/\D/g, '');
      if (value) {
        value = Math.min(parseInt(value, 10), 100);
        e.target.value = value;
      } else {
        e.target.value = '';
      }
    });
  }
  validatePercentInput('discountValue');
  validatePercentInput('couponValue');

</script>

</body>
</html>