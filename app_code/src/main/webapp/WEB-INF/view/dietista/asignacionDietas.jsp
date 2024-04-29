<%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 29/4/24
  Time: 13:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dietas</title>
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
    <div class="col-md-6">
      <h2 class="text-center">Clientes</h2>
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Buscar cliente..." aria-label="Buscar cliente" aria-describedby="button-addon2">
        <div class="input-group-append">
          <button class="btn btn-outline-primary" type="button" id="button-buscarClientes">Buscar</button>
        </div>
      </div>
      <table class="table">
        <thead>
        <tr>
          <th>Seleccionar</th>
          <th>Nombre</th>
          <th>Apellidos</th>
          <th>Edad</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td><input type="radio" name="cliente"></td>
          <td>Nombre Cliente 1</td>
          <td>Apellidos Cliente 1</td>
          <td>25</td>
        </tr>
        <tr>
          <td><input type="radio" name="cliente"></td>
          <td>Nombre Cliente 2</td>
          <td>Apellidos Cliente 2</td>
          <td>30</td>
        </tr>
        </tbody>
      </table>
    </div>
    <div class="col-md-6">
      <h2 class="text-center">Dietas</h2>
      <div class="input-group mb-3">
        <input type="text" class="form-control" placeholder="Buscar dieta..." aria-label="Buscar dieta" aria-describedby="button-addon2">
        <div class="input-group-append">
          <button class="btn btn-outline-primary" type="button" id="button-buscarDietas">Buscar</button>
        </div>
      </div>
      <table class="table">
        <thead>
        <tr>
          <th>Seleccionar</th>
          <th>Dieta</th>
          <th>Tipo</th>
          <th>Calorías</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td><input type="radio" name="dieta"></td>
          <td>Dieta 1</td>
          <td>Tipo 1</td>
          <td>500</td>
        </tr>
        <tr>
          <td><input type="radio" name="dieta"></td>
          <td>Dieta 2</td>
          <td>Tipo 2</td>
          <td>700</td>
        </tr>
        </tbody>
      </table>
    </div>
  </div>
  <div class="row justify-content-center">
    <div class="col-md-6 text-center">
      <button class="btn btn-primary">Asignar</button>
    </div>
  </div>
</div>
<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

