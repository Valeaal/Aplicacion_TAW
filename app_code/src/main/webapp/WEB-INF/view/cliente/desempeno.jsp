<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="es.uma.proyectotaw.entity.Desempeno" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 2/5/24
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Ejercicio ejercicio = (Ejercicio) request.getAttribute("ejercicio");
    Integer clientId = (Integer) request.getAttribute("clientId");
%>
<html>
<head>
    <title>Desempeño del ejercicio</title>
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<form method="post" action="/guardarDesempeno">
    Está valorando el ejercicio: <br>

    Valoración: <input type="text" name="valoracion">  <br>
    Peso realizado: <input type="text" name="peso">  <br>
    Comentarios: <input type="text" name="comentario">  <br>
    <input type="hidden" name="clientId" value="<%= clientId %>">
    <input type="hidden" name="ejercicioId" value="<%= ejercicio.getId() %>">
    <button>Enviar</button>
</form>

</body>
</html>
