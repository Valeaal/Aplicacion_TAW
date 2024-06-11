<%--
  Created by IntelliJ IDEA.
  User: Usuario
  Date: 07/06/2024
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Dieta - Comidas</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <h2>Comidas Diarias (3)</h2>
    <div class="form-group">
    <form:form action="/anadirComida" modelAttribute="ComidasDieta" method="post" class="form-horizontal">
        <div class="form-group">
            <label for="comida0">Desayuno</label>
            <form:select path="desayuno" class="form-control" id="comida0">
                <!-- Opciones -->
                <form:option value="" label="Seleccionar..."/>
                <form:option value="Opción 1" label="Opción 1"/>
                <form:option value="Opción 2" label="Opción 2"/>
            </form:select>
        </div>
        <div class="form-group">
            <label for="comida1">Comida</label>
            <form:select path="comida" class="form-control" id="comida1">
                <!-- Opciones -->
                <form:option value="" label="Seleccionar..."/>
                <form:option value="Opción 1" label="Opción 1"/>
                <form:option value="Opción 2" label="Opción 2"/>
            </form:select>
        </div>
        <div class="form-group">
            <label for="comida2">Cena</label>
            <form:select path="cena" class="form-control" id="comida2">
                <!-- Opciones -->
                <form:option value="" label="Seleccionar..."/>
                <form:option value="Opción 1" label="Opción 1"/>
                <form:option value="Opción 2" label="Opción 2"/>
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary">Guardar</button>
    </form:form>
</div>
</body>
</html>

