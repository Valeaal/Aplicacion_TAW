<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 29/4/24
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
  List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
%>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Dietas</title>
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
      <h2 class="text-center">Dietas</h2>
      <div class="row">
        <div class="col-md-3">
          <div class="input-group mb-3">
            <input type="text" class="form-control" placeholder="Buscar dietas..." aria-label="Buscar dietas" aria-describedby="button-addon2">
            <div class="input-group-append">
              <button class="btn btn-outline-primary" type="button" id="button-addon2">Buscar</button>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <select class="custom-select mb-3">
            <option selected>Calorías</option>
            <option value="1">Menos de 500</option>
            <option value="2">Entre 500 y 1000</option>
            <option value="3">Entre 1000 y 1500</option>
            <option value="4">Entre 1500 y 2000</option>
            <option value="5">Entre 2500 y 3000</option>
            <option value="6">Más de 3000</option>
          </select>
        </div>
      </div>
      <div class="table-responsive">
        <table class="table">
          <thead>
          <tr>
            <th>Dieta</th>
            <th>Descripcion</th>
            <th>Calorias</th>
          </tr>
          </thead>
          <tbody>
          <%
            for(Dieta dieta : dietas){
          %>
          <tr>
            <td><%=dieta.getNombre()%></td>
            <td><%=dieta.getDescripcion()%></td>
            <td>1000</td>
            <td><button class="btn btn-warning mr-2">Modificar Dieta</button></td>
            <td><button class="btn btn-danger">Eliminar Dieta</button></td>
          </tr>
          <%
            }
          %>
          </tbody>
        </table>
      </div>
      <div class="row justify-content-center">
        <div class="col-md-6 text-center">
          <button class="btn btn-success mr-2">Nueva Dieta</button>
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

