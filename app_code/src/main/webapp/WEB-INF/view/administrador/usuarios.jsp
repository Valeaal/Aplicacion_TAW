<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Integer> Edades = (List<Integer>)request.getAttribute("edades");
    List<Integer> Ingresos = (List<Integer>)request.getAttribute("ingresos");
    List<Usuario> Usuarios = (List<Usuario>)request.getAttribute("usuarios");
    Set<String> Roles = (Set<String>)request.getAttribute("roles");
%>


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

<div class="container-fluid ">
    <!-- Formulario -->
    <div class="row justify-content-center mt-2">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/admin/usuarios/filtrar">
                        <div class="mb-3 d-flex">
                            <input type="text" class="form-control form-control-lg me-2" name="busquedaN" placeholder="Nombre">
                            <input type="text" class="form-control form-control-lg me-2" name="busquedaA1" placeholder="Apellidos">
                        </div>
                        <div class="mb-3 d-flex">
                            <!-- Menú desplegable 1 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="edad">
                                    <option selected>Selecciona Edad</option>
                                    <%
                                        for (int i = 0; i < Edades.size(); i++) {
                                    %>
                                        <option> <%= Edades.get(i) %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Menú desplegable 2 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="ingreso">
                                    <option selected>Selecciona Año de Ingreso</option>
                                    <%
                                        for (int i = 0; i < Ingresos.size(); i++) {
                                    %>
                                    <option> <%= Ingresos.get(i) %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Menú desplegable 3 -->
                            <div class= "flex-grow-1 me-3">
                                <select class="form-select" name = "rol">
                                    <option selected>Selecciona Rol</option>
                                    <%
                                        for (String rol : Roles) {
                                    %>
                                    <option> <%= rol %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Botón de búsqueda -->
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <div class="d-flex justify-content-between align-items-start mt-5">
        <!-- Tabla -->
        <div class="flex-grow-1 me-3">
            <form id="userForm">
            <table class="table">
                <thead>
                <tr>
                    <th scope="col"></th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Año de ingreso</th>
                    <th scope="col">Edad</th>
                    <th scope="col">Rol</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <% for (Usuario usr : Usuarios){ %>
                <tr>
                    <td><input type="radio" name="uSeleccionado" value="<%= usr.getId()%>>"></td>
                    <td><%= usr.getNombre()%></td>
                    <td><%= usr.getApellidos()%></td>
                    <td><%= usr.getPerteneceDesde()%></td>
                    <td><%= usr.getFechaNacimiento()%></td>
                    <td><%= usr.getTipoUsuario().getTipo()%></td>
                </tr>
                <% } %>
                </tbody>
            </table>
            </form>
        </div>

        <!-- Botones -->
        <div class="col-2 mt-auto">
            <div class="d-grid">
                <button type="button" class="btn btn-success mb-2">Añadir usuario</button>
                <button type="button" class="btn btn-warning mb-2">Modificar usuario</button>
                <button type="button" class="btn btn-danger">Eliminar usuario</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

    <!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
