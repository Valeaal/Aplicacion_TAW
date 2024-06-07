<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Menu> Menus = (List<Menu>) request.getAttribute("menus");
%>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de menus</title>
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
                    <form action="/admin/menus/filtrar">
                        <div class="mb-3 d-flex">
                            <input type="text" class="form-control form-control-lg me-2" name="inputNombre"
                                   placeholder="Nombre">
                            <input type="text" class="form-control form-control-lg me-2" name="inputAlergenos" placeholder="Alérgenos">
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <form action="/admin/menus/seleccionar">
        <div class="d-flex justify-content-between align-items-start mt-5">

            <!-- Tabla -->

            <div class="flex-grow-1 me-3">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Alérgenos</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                    <% for (Menu m : Menus) { %>
                    <tr>
                        <td><input type="radio" name="mSeleccionado" value= "<%= m.getId() %>" ></td>
                        <td><%= m.getNombre()%></td>
                        <td><%= m.getDescripcion()%></td>
                        <td><%= m.getAlergenos()%></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- Botones -->
            <div class="col-2 mt-auto">
                <div class="d-grid">
                    <button type="submit" class="btn btn-success mb-2" name="Boton" value="Añadir">Añadir menú</button>
                    <button type="submit" class="btn btn-warning mb-2" name="Boton" value="Modificar">Modificar menú</button>
                    <button type="submit" class="btn btn-danger" name="Boton" value="Eliminar">Eliminar menú</button>
                </div>
            </div>

        </div>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
