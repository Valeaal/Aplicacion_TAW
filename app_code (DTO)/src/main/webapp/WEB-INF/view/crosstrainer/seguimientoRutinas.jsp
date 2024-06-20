<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.dto.ClienteDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
    List<ClienteDTO> clientes = (List<ClienteDTO>) request.getAttribute("clientes");
%>
<!--Hecho por Pablo Alonso Burgos-->
<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 800px;
        margin: 40px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 1px solid #e0e0e0;
        margin-bottom: 20px;
    }

    .header h1 {
        margin: 0;
        font-size: 26px;
        color: #333;
    }

    .new-routine-btn {
        padding: 12px 25px;
        font-size: 16px;
        color: #ffffff;
        background-color: #28a745;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .new-routine-btn:hover {
        background-color: #218838;
    }

    table {
        text-align: center;
        width: 100%;
        border-collapse: collapse;
    }

    th, td {
        padding: 15px;
        text-align: center;
        border-bottom: 1px solid #e0e0e0;
    }

    th {
        text-align: center;
        background-color: #f9f9f9;
        font-weight: 600;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    .btn {
        padding: 8px 15px;
        font-size: 14px;
        color: #ffffff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .btn-modify {
        background-color: #007bff;
    }

    .btn-modify:hover {
        background-color: #0056b3;
    }

    .btn-delete {
        background-color: #dc3545;
    }

    .btn-delete:hover {
        background-color: #c82333;
    }

    .btn-reset {
        background-color: #6c757d;
    }

    .btn-reset:hover {
        background-color: #5a6268;
    }

    .btn-reset-link {
        padding: 8px 15px;
        font-size: 14px;
        color: #ffffff;
        background-color: #6c757d;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .btn-reset-link:hover {
        background-color: #5a6268;
    }

    .form-buttons {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .input-group {
        display: flex;
        align-items: center;
        gap: 10px;
    }

    .input-group .form-control {
        flex: 1;
    }
</style>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de usuarios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <!-- Formulario -->
    <form:form modelAttribute="clienteFiltro" method="get" action="/filtroClientes">
        <div class="row justify-content-center mt-2">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <div class="mb-3 input-group">
                            <form:input path="nombre" class="form-control form-control-lg"
                                        placeholder="Buscar Cliente por nombre"/>
                            <div class="form-buttons">
                                <!-- Botón de búsqueda -->
                                <button type="submit" class="btn btn-primary">Buscar</button>
                                <!-- Enlace de restablecer -->
                                <a href="/seguimientoRutinas" class="btn-reset-link">Restablecer</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>

    <div class="container">
        <div class="header">
            <h1>Mis clientes</h1>
        </div>
        <table>
            <thead>
            <tr>
                <th>Nombre</th>
                <th>Apellidos</th>
                <th>Fecha de nacimiento</th>
                <th>Desempeño</th>
            </tr>
            </thead>
            <tbody>
            <!-- Aquí se deben iterar las rutinas para generar las filas dinámicamente -->
            <% for (ClienteDTO cliente : clientes) { %>
            <tr>
                <td><%= cliente.getUsuario().getNombre() %>
                </td>
                <td><%= cliente.getUsuario().getApellidos() %>
                </td>
                <td><%= cliente.getUsuario().getFechaNacimiento() %>
                </td>
                <td>
                    <a href="/verDesempenoo?idCliente=<%= cliente.getId() %>" class="btn btn-warning mb-2">Realizar
                        Seguimiento</a>
                </td>
            </tr>
            <% } %>
            <!-- Fin de la iteración -->
            </tbody>
        </table>
    </div>

</div>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
