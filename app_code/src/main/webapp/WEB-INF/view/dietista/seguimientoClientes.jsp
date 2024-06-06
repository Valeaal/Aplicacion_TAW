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
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes")
%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seguimiento Clientes</title>
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
                        <th>Nombre</th>
                        <th>Apellidos</th>
                        <th>Edad</th>
                        <th>Año de Ingreso</th>
                        <th>Dieta</th>
                    </tr>
                    </thead>
                    <tbody>
                    <%
                        for(Cliente cliente:clientes){
                    %>
                    <tr>
                        <td><%=cliente.getUsuario().getNombre()%></td>
                        <td><%=cliente.getUsuario().getApellidos()%></td>
                        <td><td><%=cliente.getEdad()%></td></td>
                        <td><%=cliente.getUsuario().getPerteneceDesde()%></td>
                        <%
                            if(cliente.getDieta()!=null){
                        %>
                            <td><%=cliente.getDieta().getNombre()%></td>
                        <%
                            }else{
                        %>
                            <td>No tiene dieta asignada</td>
                        <%
                            }
                        %>
                        <td><button class="btn btn-primary mr-2">Hacer Seguimiento</button></td>
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
<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
