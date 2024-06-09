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
    <style>
        body {
            background-color: #f8f9fa;
            font-family: Arial, sans-serif;
        }

        .container {
            max-width: 500px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            margin-bottom: 30px;
            color: #007bff;
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            font-weight: bold;
            color: #555;
        }

        input[type="text"],
        input[type="number"],
        select {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .btn-primary {
            background-color: #007bff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-primary:hover {
            background-color: #0056b3;
        }
    </style>
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
