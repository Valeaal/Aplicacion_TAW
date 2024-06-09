<%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 10/5/24
  Time: 12:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="es"><head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificar Dieta</title>
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
            <h2 class="text-center">Modificar Dieta</h2>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Buscar cliente" aria-label="Buscar cliente" aria-describedby="button-addon2">
                <div class="input-group-append">
                    <button class="btn btn-outline-primary" type="button" id="button-addon2">Buscar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <select class="custom-select mb-3">
                        <option selected="">Comida</option>
                        <option value="1">Desayuno</option>
                        <option value="2">Media Mañana</option>
                        <option value="3">Comida</option>
                        <option value="4">Merienda</option>
                        <option value="5">Cena</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <select class="custom-select mb-3">
                        <option selected="">Menu</option>
                        <option value="1">Menu 1</option>
                        <option value="2">Menu 2</option>
                        <option value="3">Menu 3</option>
                        <option value="4">Menu 4</option>
                        <option value="5">Menu 5</option>
                    </select>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Seleccionar</th>
                        <th>Comida</th>
                        <th>Menú</th>
                        <th>Calorias</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td><input type="radio" name="cliente"></td>
                        <td>Desayuno</td>
                        <td>Menu 1</td>
                        <td>250</td>
                    </tr>
                    <tr>
                        <td><input type="radio" name="cliente"></td>
                        <td>Comida</td>
                        <td>Menu 2</td>
                        <td>725</td>
                    </tr>
                    <tr>
                        <td><input type="radio" name="cliente"></td>
                        <td>Cena</td>
                        <td>Menu 3</td>
                        <td>450</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-3">
                    <button class="btn btn-primary mr-2">Añadir Comida</button>
                    <button class="btn btn-warning mr-2">Actualizar Comida</button>
                    <button class="btn btn-danger mr-2">Eliminar Comida</button>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center">
                    <button class="btn btn-success mr-2">Guardar Cambios</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body></html>