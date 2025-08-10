<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<h2>Novo Produto</h2>

<form:form action="/products" method="post" modelAttribute="product">
    <div>
        <label>Nome</label>
        <form:input path="name" />
        <form:errors path="name" cssClass="error" />
    </div>

    <div>
        <label>Descrição</label>
        <form:input path="description" />
        <form:errors path="description" cssClass="error" />
    </div>

    <div>
        <label>Preço</label>
        <form:input path="price" />
        <form:errors path="price" cssClass="error" />
    </div>

    <button type="submit">Salvar</button>
</form:form>
