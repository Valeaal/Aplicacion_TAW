<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>

<%
    List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Página</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <!-- Parte izquierda con tabla y barra de búsqueda -->
        <div class="col  flex-grow-1 col-auto">
            <form action="/admin/clientesEntrenadores/filtrarCLientes">
                <div class="mb-3 d-flex">
                    <input type="text" class="form-control form-control-lg me-2" name="busquedaN"
                           placeholder="Nombre cliente">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </div>
            </form>
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
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <% for (Usuario usr : clientes) { %>
                <tr>
                    <td><input type="radio" name="cSeleccionado" value="<%= usr.getId()%>>"></td>
                    <td><%= usr.getNombre()%>
                    </td>
                    <td><%= usr.getApellidos()%>
                    </td>
                    <td><%= usr.getPerteneceDesde()%>
                    </td>
                    <td><%= usr.getFechaNacimiento()%>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>


        <!-- Parte derecha con tabla y barra de búsqueda -->
        <div class="col flex-grow-1 col-auto">
            <form action="/admin/clientesEntrenadores/filtrarEntrenadores">
                <div class="mb-3 d-flex">
                    <input type="text" class="form-control form-control-lg me-2" name="busquedaN"
                           placeholder="Nombre entrenador">
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </div>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripción</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <% for (Rutina rt : rutinas) { %>
                <tr>
                    <td><input type="radio" name="cSeleccionado" value="<%= rt.getId()%>>"></td>
                    <td><%= rt.getNombre()%></td>
                    <td><%= rt.getDescripcion()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>

        </div>
        <!-- Botón de asignar -->
        <div class="col col-auto mt-auto">
            <button type="button" class="btn btn-success">Asignar</button>
        </div>
    </div>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
