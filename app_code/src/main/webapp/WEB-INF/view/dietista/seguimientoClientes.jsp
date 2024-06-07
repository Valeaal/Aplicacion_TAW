<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 1/5/24
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seguimiento Clientes</title>
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
        .btn-group {
            display: flex;
            gap: 10px;
        }
    </style>
</head>
<body>

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h2 class="text-center">Seguimiento Clientes</h2>
            <div class="search-section">
                <div class="input-group">
                    <input type="text" class="form-control" placeholder="Buscar cliente" aria-label="Buscar cliente" aria-describedby="button-addon2">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" id="button-addon2">Buscar</button>
                    </div>
                </div>
                <select class="custom-select">
                    <option selected>Género</option>
                    <option value="1">Hombre</option>
                    <option value="2">Mujer</option>
                </select>
                <div class="input-group">
                    <input type="number" class="form-control" placeholder="Edad" aria-label="Edad" aria-describedby="button-addon2" min="14" max="100">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" id="buscarEdad">Buscar</button>
                    </div>
                </div>
                <div class="input-group">
                    <input type="number" class="form-control" placeholder="Ingreso" aria-label="Ingreso" aria-describedby="button-addon2" min="14" max="100">
                    <div class="input-group-append">
                        <button class="btn btn-outline-primary" type="button" id="buscarIngreso">Buscar</button>
                    </div>
                </div>
            </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Edad</th>
                        <th>Año de Ingreso</th>
                        <th>Dieta</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(Cliente cliente : clientes) {
                    %>
                    <tr>
                        <td><%=cliente.getUsuario().getNombre()%></td>
                        <td><%=cliente.getUsuario().getApellidos()%></td>
                        <td><%=cliente.getEdad()%></td>
                        <td><%=cliente.getUsuario().getPerteneceDesde()%></td>
                        <td><%= cliente.getDieta() != null ? cliente.getDieta().getNombre() : "No tiene dieta asignada" %></td>
                        <td>
                            <div class="btn-group">
                                <a href="/seguimiento/hacer?id=<%=cliente.getId()%>" class="btn btn-primary">Hacer Seguimiento</a>
                            </div>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                    </tbody>
                </table>
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
