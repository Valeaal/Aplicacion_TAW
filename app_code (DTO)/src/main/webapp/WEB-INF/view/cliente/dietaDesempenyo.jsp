<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Menu" %>
<%@ page import="es.uma.proyectotaw.entity.Desempeno" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 2/5/24
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Menu menu = (Menu) request.getAttribute("menu");
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Integer comidaId = (Integer) request.getAttribute("comidaId");
%>

<html>
<head>
    <title>Desempeño del menu</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
Está valorando el plato <%=menu.getNombre()%>: <br>
<form:form modelAttribute="desempeno" method="post" action="/guardarDesempenoDieta">
    <form:hidden path="menu" value="<%=menu.getId()%>"/>
    <form:hidden path="comida" value="<%=comidaId%>"/>
    Valoración: <form:input path="valoracion"/>
    <form:hidden path="cliente" value="<%=cliente.getId()%>"/>
    Comentarios: <form:input path="comentarios"/>
    <form:button>Enviar</form:button>
</form:form>
</body>
</html>