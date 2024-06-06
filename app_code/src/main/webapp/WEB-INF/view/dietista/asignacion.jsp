<%--
  Created by IntelliJ IDEA.
  User: Javier Torrecilla
  Date: 06/06/2024
  Time: 19:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>

<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List<Dieta> dietas = (List<Dieta>) request.getAttribute("dietas");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación Dieta</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">



    <form action="/dietista/asignacion/asignar">

        <div class="row">

            <!-- Parte izquierda con tabla de clientes -->
            <div class="col  flex-grow-1 col-auto">

                <h1>Lista de clientes</h1>
                <p>¿A qué cliente quieres asignarle una dieta?<p>

                <table class="table">
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

                    <% for (Cliente c : clientes) { %>
                    <tr>
                        <td><input type="radio" name="clienteSeleccionado" value="<%= c.getId()%>"></td>
                        <td><%= c.getUsuario().getNombre()%>
                        </td>
                        <td><%= c.getUsuario().getApellidos()%>
                        </td>
                        <td><%= c.getUsuario().getPerteneceDesde()%>
                        </td>
                        <td><%= c.getUsuario().getFechaNacimiento()%>
                        </td>
                    </tr>
                    <% } %>

                    </tbody>
                </table>
            </div>


            <!-- Parte derecha con tabla de entrenadores -->
            <div class="col flex-grow-1 col-auto">

                <h1>Lista de dietas</h1>
                <p>¿Qué dietas vas a asignarle?<p>


                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripcion</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                    <% for (Dieta d : dietas) { %>
                    <tr>
                        <td><input type="radio" name="dietaSeleccionado" value="<%= d.getId()%>"></td>
                        <td><%= d.getNombre()%>
                        </td>
                        <td><%= d.getDescripcion()%>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

        </div>

        <!-- Botón de asignar -->
        <div class="col col-auto mt-auto">
            <button type="submit" class="btn btn-success">Asignar</button>
        </div>

    </form>


</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
