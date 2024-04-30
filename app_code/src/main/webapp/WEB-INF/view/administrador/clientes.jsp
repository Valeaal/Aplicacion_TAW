<%--
  Created by IntelliJ IDEA.
  User: alvaro
  Date: 30/4/24
  Time: 12:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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


<div class="container-fluid ">
    <!-- Formulario -->
    <div class="row justify-content-center mt-2">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form>
                        <div class="mb-3 d-flex">
                            <input type="text" class="form-control form-control-lg me-2" id="busqueda" placeholder="Buscar por nombre" required>
                            <!-- Botón de búsqueda -->
                            <button type="submit" class="btn btn-primary float-end">Aplicar filtros y buscar</button>
                        </div>
                        <div class="col-12 mb-3 d-flex">
                            <!-- Menú desplegable 1 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select">
                                    <option selected>Género</option>
                                    <!-- Agrega más opciones según sea necesario -->
                                </select>
                            </div>
                            <!-- Menú desplegable 2 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select">
                                    <option selected>Edad</option>
                                    <!-- Agrega más opciones según sea necesario -->
                                </select>
                            </div>
                            <!-- Menú desplegable 3 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select">
                                    <option selected>Año de ingreso</option>
                                    <!-- Agrega más opciones según sea necesario -->
                                </select>
                            </div>
                            <!-- Menú desplegable 4 -->
                            <div>
                                <select class="flex-grow-1 form-select">
                                    <option selected>Rol</option>
                                    <!-- Agrega más opciones según sea necesario -->
                                </select>
                            </div>
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
                    <th scope="col"> </th>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Género</th>
                    <th scope="col">Año de ingreso</th>
                    <th scope="col">Edad</th>
                    <th scope="col">Rol</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <tr>
                    <td><input type="radio" name="selectedUser" value="Dato 1"></td>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>
                <tr>
                    <td>Dato 1</td>
                    <td>Dato 2</td>
                    <!-- Agrega más celdas según sea necesario -->
                </tr>




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
