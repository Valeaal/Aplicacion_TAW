<%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 1/5/24
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seguimiento dieta de cliente</title>
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
            <h2 class="text-center mb-4">Seguimiento de Dieta de Cliente</h2>
            <div class="row justify-content-center mb-5">
                <div class="col-md-6 text-center">
                    <button class="btn btn-primary mr-2">Dia 1</button>
                    <button class="btn btn-primary mr-2">Dia 2</button>
                    <button class="btn btn-primary mr-2">Dia 3</button>
                    <button class="btn btn-primary mr-2">Dia 4</button>
                    <button class="btn btn-primary mr-2">Dia 5</button>
                    <button class="btn btn-primary mr-2">Dia 6</button>
                </div>
            </div>
            <h5>Dia 1</h5>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Comida</th>
                        <th>Menú</th>
                        <th>Completado</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí puedes usar Thymeleaf u otra tecnología para rellenar los datos -->
                    <!-- Puedes agregar más filas según sea necesario -->
                    <tr>
                        <td>Comida 1</td>
                        <td>Menu 1</td>
                        <td><input type="checkbox" name="cliente"></td>
                    </tr>
                    <tr>
                        <td>Comida 2</td>
                        <td>Menu 2</td>
                        <td><input type="checkbox" name="cliente"></td>
                    </tr>
                    <tr>
                        <td>Comida 3</td>
                        <td>Menu 3</td>
                        <td><input type="checkbox" name="cliente"></td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-center mt-5">
                <div class="col-md-6 text-center">
                    <button class="btn btn-primary mr-2">Volver a Seguimiento</button>
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