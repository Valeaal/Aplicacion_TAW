<%@ page import="es.uma.proyectotaw.entity.Dieta" %><%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 07/06/2024
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<% Dieta dieta = (Dieta) request.getAttribute("dieta"); %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Dieta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Editar Dieta</h2>
    <form:form action="/editarDieta" modelAttribute="nuevaDieta" method="post" class="form-horizontal">
        <form:hidden path="id" value="<%=dieta.getId()%>"/>
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <form:input path="nombre" class="form-control" id="nombre" value="<%=dieta.getNombre()%>"/>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <form:input path="descripcion" class="form-control" id="descripcion" value="<%=dieta.getDescripcion()%>"/>
        </div>
        <div class="form-group">
            <label for="calorias">Calorías</label>
            <form:input path="calorias" class="form-control" id="calorias" type="number" value="<%=dieta.getCalorias()%>"/>
        </div>
        <button type="submit" class="btn btn-primary">Siguiente</button>
    </form:form>
</div>
</body>
</html>
