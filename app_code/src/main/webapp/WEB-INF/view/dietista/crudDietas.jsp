<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="java.util.List" %>
<%--
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
    body {
      background-color: #f8f9fa;
    }
    .header, .footer {
      background-color: #343a40;
      color: white;
      padding: 10px 0;
      text-align: center;
    }
    .container {
      margin-top: 20px;
    }
    h2 {
      margin-bottom: 20px;
    }
    .table th, .table td {
      text-align: center;
      vertical-align: middle;
    }
    .table thead {
      background-color: #007bff;
      color: white;
    }
    .btn-warning, .btn-danger {
      margin: 0 5px;
    }
    .search-section {
      margin-bottom: 20px;
      display: flex;
      justify-content: space-between;
      align-items: center;
    }
    .search-section .input-group,
    .search-section .custom-select {
      flex: 1;
      margin-right: 10px;
    }
    .search-section .input-group:last-child,
    .search-section .custom-select:last-child {
      margin-right: 0;
    }
    .new-dieta-button {
      margin-top: 20px;
      text-align: center;
    }
  </style>
</head>
<body>

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container">
  <div class="row">
    <div class="col-md-12">
      <h2 class="text-center">Dietas</h2>
      <div class="search-section">
        <div class="input-group">
          <input type="text" class="form-control" placeholder="Buscar dietas..." aria-label="Buscar dietas" aria-describedby="button-addon2">
          <div class="input-group-append">
            <button class="btn btn-outline-primary" type="button" id="button-addon2">Buscar</button>
          </div>
        </div>
        <select class="custom-select">
          <option selected>Calorías</option>
          <option value="1">Menos de 500</option>
          <option value="2">Entre 500 y 1000</option>
          <option value="3">Entre 1000 y 1500</option>
          <option value="4">Entre 1500 y 2000</option>
          <option value="5">Entre 2500 y 3000</option>
          <option value="6">Más de 3000</option>
        </select>
      </div>
      <div class="table-responsive">
        <table class="table table-hover">
          <thead>
          <tr>
            <th>Dieta</th>
            <th>Descripción</th>
            <th>Calorías</th>
            <th>Acciones</th>
          </tr>
          </thead>
          <tbody>
          <% for(Dieta dieta : dietas) { %>
          <tr>
            <td><%=dieta.getNombre()%></td>
            <td><%=dieta.getDescripcion()%></td>
            <td><%=dieta.getCalorias()%></td>
            <td>
              <a href="/dietas/modificar?id=<%=dieta.getId()%>" class="btn btn-warning">Modificar Dieta</a>
              <a href="/dietas/eliminar?id=<%=dieta.getId()%>" class="btn btn-danger">Eliminar Dieta</a>
            </td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>
      <div class="new-dieta-button">
        <a href="/dietas/nueva" class="btn btn-success">Modificar Dieta</a>
      </div>
    </div>
  </div>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
