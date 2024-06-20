<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 6/6/24
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaDTO> rutinasFiltradas = (List<RutinaDTO>) request.getAttribute("rutinasFiltradas");
    List<RutinaDTO> rutinas= (List<RutinaDTO>) request.getAttribute("rutinas");
    HashMap<Integer, Float> cumplimiento = (HashMap<Integer, Float>) request.getAttribute("cumplimiento");
%>
<html>
<head>
    <title>Rutinas filtradas por desempeño</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>


<div class="container mt-5">
    <p class="lead text-center">Aquí aparecen sus rutinas, tanto actuales como pasadas. Compare las rutinas por su desempeño.</p>
    <div class="row">
        <div class="col-md-6">
            <h2 class="text-primary">Rutinas Filtradas</h2>
            <div class="list-group">
                <% for (RutinaDTO r : rutinasFiltradas) { %>
                <a href="/verRutina?id=<%=r.getId()%>" class="list-group-item list-group-item-action">
                    <strong><%= r.getNombre() %></strong> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(r.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
        <div class="col-md-6">
            <h2 class="text-secondary">Resto de Rutinas</h2>
            <div class="list-group">
                <% for (RutinaDTO r : rutinas) { %>
                <a href="/verRutina?id=<%=r.getId()%>" class="list-group-item list-group-item-action">
                    <strong><%= r.getNombre() %></strong> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(r.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
