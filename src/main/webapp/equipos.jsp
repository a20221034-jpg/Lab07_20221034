<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%-- Forzamos el formato numérico con punto decimal para que coincida la verificación --%>
<fmt:setLocale value="en_US"/>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Inventario de equipos</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Inventario de equipos – Telecom Andina</h1>

<%-- Ejercicio 3: combobox de sedes --%>
<form method="get" action="equipos">
    <label for="idsede">Sede:</label>
    <select name="idsede" id="idsede">
        <option value="0" ${idsedeSel == 0 ? 'selected' : ''}>Todas las sedes</option>
        <c:forEach var="s" items="${sedes}">
            <option value="${s.idSede}" ${idsedeSel == s.idSede ? 'selected' : ''}>${s.nombre}</option>
        </c:forEach>
    </select>
    <button type="submit">Filtrar</button>
</form>

<%-- Ejercicio 4: botón "Descargar PDF" -> equipos?accion=pdf&idsede=X --%>
<form method="get" action="equipos">
    <input type="hidden" name="accion" value="pdf">
    <input type="hidden" name="idsede" value="${idsedeSel}">
    <button type="submit">Descargar PDF</button>
</form>

<%-- Ejercicio 3: listado --%>
<table>
    <thead>
    <tr>
        <th>Modelo</th>
        <th>Tipo</th>
        <th>Sede</th>
        <th>Precio (S/)</th>
        <th>Cantidad</th>
        <th>Soporte mensual (S/)</th>
    </tr>
    </thead>
    <tbody>
    <c:set var="total" value="0"/>
    <c:forEach var="e" items="${equipos}">
        <%-- Polimorfismo: llamamos a calcularSoporteMensual() sin preguntar por el tipo --%>
        <c:set var="soporte" value="${e.calcularSoporteMensual()}"/>
        <c:set var="total" value="${total + soporte}"/>
        <tr>
            <td>${e.modelo}</td>
            <td>${e.tipo}</td>
            <td>${e.sede.nombre}</td>
            <td><fmt:formatNumber value="${e.precio}" minFractionDigits="2" maxFractionDigits="2" groupingUsed="false"/></td>
            <td>${e.cantidad}</td>
            <td><fmt:formatNumber value="${soporte}" minFractionDigits="2" maxFractionDigits="2" groupingUsed="false"/></td>
        </tr>
    </c:forEach>
    <tr class="total">
        <td colspan="5">TOTAL</td>
        <td><fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2" groupingUsed="false"/></td>
    </tr>
    </tbody>
</table>
</body>
</html>
