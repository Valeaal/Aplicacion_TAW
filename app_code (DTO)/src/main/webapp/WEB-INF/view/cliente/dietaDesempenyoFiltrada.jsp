<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 16/6/24
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Dieta> dietasFiltradas = (List<Dieta>) request.getAttribute("dietasFiltradas");
    List<Dieta> dietas= (List<Dieta>) request.getAttribute("dietas");
    HashMap<Integer, Float> cumplimiento = (HashMap<Integer, Float>) request.getAttribute("cumplimiento");
%>
<html>
<head>
    <title>Dietas filtradas por desempeño</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container mt-3">
    <div class="row">
        <div class="col-md-6">
            <h2>Dietas Filtradas</h2>
            <div class="list-group">
                <% for (Dieta d : dietasFiltradas) { %>
                <a href="#" class="list-group-item list-group-item-action">
                    <%= d.getNombre() %> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(d.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
        <div class="col-md-6">
            <h2>Resto de Dietas</h2>
            <div class="list-group">
                <% for (Dieta d : dietas) { %>
                <a href="#" class="list-group-item list-group-item-action">
                    <%= d.getNombre() %> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(d.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>