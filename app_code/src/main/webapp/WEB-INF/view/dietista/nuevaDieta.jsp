<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 07/06/2024
  Time: 16:04
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Dieta</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Agregar Nueva Dieta</h2>
    <form:form action="/crearDieta" modelAttribute="nuevaDieta" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <form:input path="nombre" class="form-control" id="nombre"/>
        </div>
        <div class="form-group">
            <label for="descripcion">Descripción</label>
            <form:input path="descripcion" class="form-control" id="descripcion" />
        </div>
        <div class="form-group">
            <label for="calorias">Calorías</label>
            <form:input path="calorias" class="form-control" id="calorias" type="number" />
        </div>
        <div class="form-group">
            <label for="comidasDiarias">Número de comidas diarias</label>
            <form:select path="comidasDiarias" class="form-control" id="comidasDiarias" >
                <form:option value="" label="Seleccionar..."/>
                <form:option value="3" label="3"/>
                <form:option value="4" label="4"/>
                <form:option value="5" label="5"/>
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary">Siguiente</button>
    </form:form>
</div>
</body>
</html>
