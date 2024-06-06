<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Menu" %><%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 1/5/24
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Menu> menus = (List<Menu>) request.getAttribute("menus");
%>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Crear Dieta</title>
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
            <h2 class="text-center mb-3">Creación de Dietas</h2>
            <h5>Establece nombre, cantidad de comida y calorias de la nueva dieta:</h5>
            <div class="row">
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Nombre" aria-label="Nombre" aria-describedby="button-addon2" min="3" max="5">
                        <div class="input-group-append"></div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <input type="text" class="form-control" placeholder="Descripcion" aria-label="Descripcion" aria-describedby="button-addon2" min="3" max="5">
                        <div class="input-group-append"></div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="input-group mb-3">
                        <input type="number" class="form-control" placeholder="Calorias" aria-label="Calorias" aria-describedby="button-addon2">
                        <div class="input-group-append">
                        </div>
                    </div>
                </div>
            </div>
            <h5>Establece los menus de cada comida de la dieta:</h5>
            <div class="row">
                <div class="col-md-6">
                    <select class="custom-select mb-3">
                        <option selected>Comida</option>
                        <option value="1">Desayuno</option>
                        <option value="2">Media Mañana</option>
                        <option value="3">Comida</option>
                        <option value="4">Merienda</option>
                        <option value="5">Cena</option>
                    </select>
                </div>
                <div class="col-md-6">
                    <select class="custom-select mb-3">
                        <option selected>Menu</option>
                        <%
                            for(Menu menu : menus){
                        %>
                        <option value="<%=menu.getId()%>"><%=menu.getNombre()%></option>
                        <%
                            }
                        %>
                    </select>
                </div>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center mb-5">
                    <button class="btn btn-primary mr-2">Añadir Comida</button>
                </div>
            </div>
            <div class="table-responsive mt-5">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Comida</th>
                        <th>Menú</th>
                        <th>Calorias</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí puedes usar Thymeleaf u otra tecnología para rellenar los datos -->
                    <!-- Puedes agregar más filas según sea necesario -->
                    <tr>
                        <td>Desayuno</td>
                        <td>Menu 1</td>
                        <td>250</td>
                    </tr>
                    <tr>
                        <td>Comida</td>
                        <td>Menu 2</td>
                        <td>725</td>
                    </tr>
                    <tr>
                        <td>Cena</td>
                        <td>Menu 3</td>
                        <td>450</td>
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="row justify-content-center">
                <div class="col-md-6 text-center">
                    <button class="btn btn-success mr-2">Crear Dieta</button>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
