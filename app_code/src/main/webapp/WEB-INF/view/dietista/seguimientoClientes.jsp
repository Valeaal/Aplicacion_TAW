<%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 1/5/24
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<htm<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tablas Bootstrap con Buscador</title>
    <!-- Importar Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilo para centrar el contenido de las celdas */
        .table th,
        .table td {
            text-align: center;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center">Seguimiento Clientes</h2>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Buscar cliente" aria-label="Buscar cliente" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <select class="custom-select mb-3">
                        <option selected>Género</option>
                        <option value="1">Hombre</option>
                        <option value="2">Mujer</option>
                    </select>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <input type="number" class="form-control" placeholder="Edad" aria-label="Edad" aria-describedby="button-addon2" min="14" max="100">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="button" id="buscarEdad">Buscar</button>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <input type="number" class="form-control" placeholder="Ingreso" aria-label="Ingreso" aria-describedby="button-addon2" min="14" max="100">
                        <div class="input-group-append">
                            <button class="btn btn-outline-primary" type="button" id="buscarIngreso">Buscar</button>
                        </div>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Seleccionar</th>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Género</th>
                        <th>Edad</th>
                        <th>Año de Ingreso</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí puedes usar Thymeleaf u otra tecnología para rellenar los datos -->
                    <!-- Puedes agregar más filas según sea necesario -->
                    <tr>
                        <td><input type="radio" name="cliente"></td>
                        <td>Nombre Cliente 1</td>
                        <td>Apellidos Cliente 1</td>
                        <td>Hombre</td>
                        <td>25</td>
                        <td>2015</td>
                    </tr>
                    <tr>
                        <td><input type="radio" name="cliente"></td>
                        <td>Nombre Cliente 2</td>
                        <td>Apellidos Cliente 2</td>
                        <td>Mujer</td>
                        <td>30</td>
                        <td>2021</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center">
                    <button class="btn btn-primary mr-2">Hacer Seguimiento</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
