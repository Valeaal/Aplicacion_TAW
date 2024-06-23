<%@ page import="es.uma.proyectotaw.dto.DesempenoDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.ComidaMenuDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ComidaDTO" %><%--
  Created by IntelliJ IDEA.
  User: javiertorrecilla
  Date: 1/5/24
  Time: 18:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<DesempenoDTO> desempenos = (List<DesempenoDTO>) request.getAttribute("desempenos"); %>
<% List<ComidaDTO> comidasmenus = (List<ComidaDTO>) request.getAttribute("comidasmenus"); %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Seguimiento de Dieta del Cliente</title>
    <!-- Importar Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        /* Estilo para centrar el contenido de las celdas */
        .table th,
        .table td {
            text-align: center;
            vertical-align: middle;
        }
    </style>
</head>
<body>
    <div class="container mt-5">
        <h1 class="text-center">Seguimiento de Dieta del Cliente</h1>
        <table class="table table-bordered table-striped mt-3">
            <thead class="thead-dark">
                <tr>
                    <th>Comida</th>
                    <th>Comentarios</th>
                    <th>Valoración</th>
                </tr>
            </thead>
            <tbody>
                <% for (int i = 0; i < desempenos.size(); i++) {
                    ComidaDTO cm = comidasmenus.get(i);
                    DesempenoDTO d = desempenos.get(i);
                %>
                <tr>
                    <td><%= cm.getNombre() %></td>
                    <td><%= d.getComentarios() %></td>
                    <td><%= d.getValoracion() %></td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <!-- Importar Bootstrap JS (opcional, solo si necesitas funcionalidades de Bootstrap que requieran JavaScript) -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
