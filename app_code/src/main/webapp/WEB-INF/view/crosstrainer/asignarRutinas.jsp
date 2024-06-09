<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>

<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");

%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en"> <!--Hecho por Pablo Alonso Burgos-->
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación de Clientes y Rutinas</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
        body {
            background-color: #f8f9fa;
        }

        .container-fluid {
            padding-top: 50px;
        }

        .table {
            background-color: #fff;
        }

        .btn-success {
            background-color: #198754;
            border-color: #198754;
        }

        .btn-success:hover {
            background-color: #157347;
            border-color: #157347;
        }
    </style>
</head>


<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <form action="/ClientesRutinas/asignar" method="post">
        <div class="row">
            <!-- Parte izquierda con tabla de clientes -->
            <div class="col-md-6">
                <table class="table table-striped">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Apellidos</th>
                        <th scope="col">Ingreso</th>
                        <th scope="col">Fecha Nacimiento</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Filas de clientes -->
                    <% for (Cliente cliente : clientes) { %>
                    <tr>
                        <td><input type="radio" name="idUser" value="<%= cliente.getUsuario().getId()%>"></td>
                        <td><%= cliente.getUsuario().getNombre()%>
                        </td>
                        <td><%= cliente.getUsuario().getApellidos()%>
                        </td>
                        <td><%= cliente.getUsuario().getPerteneceDesde()%>
                        </td>
                        <td><%= cliente.getUsuario().getFechaNacimiento()%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- Parte derecha con tabla de rutinas -->
            <div class="col-md-6">
                <div class="d-flex justify-content-center">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col"></th>
                            <th scope="col">Nombre</th>
                            <th scope="col">Número Entrenamientos</th>
                            <th scope="col">Descripcion</th>
                            <th scope="col">FechaCreacion</th>
                        </tr>
                        </thead>
                        <tbody>
                        <!-- Filas de rutinas -->
                        <% for (Rutina rutina : rutinas) { %>
                        <tr>
                            <td><input type="radio" name="idRutina" value="<%= rutina.getId()%>"></td>
                            <td><%= rutina.getNombre()%>
                            </td>
                            <td><%= rutina.getEntrenamientos().size()%>
                            </td>
                            <td><%= rutina.getDescripcion()%>
                            </td>
                            <td><%= rutina.getFechaCreacion()%>
                            </td>


                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Botón de asignar -->
        <div class="row justify-content-center mt-4">
            <div class="col-auto">
                <button type="submit" class="btn btn-success">Asignar</button>
            </div>
        </div>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
