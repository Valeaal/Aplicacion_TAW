<%-- @author: Miguel Galdeano Rodríguez --%> 
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>

<%
    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Asignación Rutinas</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <form method="post" action="/bodybuilding/asignarRutinaCliente">
    <div class="row">
        <!-- Parte izquierda con tabla y barra de búsqueda -->
        <div class="col  flex-grow-1 col-auto">
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
                <% for (Cliente usr : clientes) { %>
                <tr>
                    <td><input type="radio" name="cliente" value="<%= usr.getId()%>"></td>
                    <td><%= usr.getUsuario().getNombre()%>
                    </td>
                    <td><%= usr.getUsuario().getApellidos()%>
                    </td>
                    <td><%= usr.getUsuario().getPerteneceDesde()%>
                    </td>
                    <td><%= usr.getUsuario().getFechaNacimiento()%>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
        <div class="col flex-grow-1 col-auto">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripción</th>
                </tr>
                </thead>
                <tbody>
                <% for (Rutina rt : rutinas) { %>
                <tr>
                    <td><input type="radio" name="rutina" value="<%= rt.getId()%>"></td>
                    <td><%= rt.getNombre()%></td>
                    <td><%= rt.getDescripcion()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>
            <div style="text-align: center">
               <button class="btn btn-success" type="submit">Asignar</button>
            </div>
    </div>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
