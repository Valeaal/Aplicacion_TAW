<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 6/6/24
  Time: 20:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Rutina> rutinasFiltradas = (List<Rutina>) request.getAttribute("rutinasFiltradas");
    List<Rutina> rutinas= (List<Rutina>) request.getAttribute("rutinas");
    HashMap<Integer, Float> cumplimiento = (HashMap<Integer, Float>) request.getAttribute("cumplimiento");
%>
<html>
<head>
    <title>Rutinas filtradas por desempeño</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container mt-3">
    <div class="row">
        <div class="col-md-6">
            <h2>Rutinas Filtradas</h2>
            <div class="list-group">
                <% for (Rutina r : rutinasFiltradas) { %>
                <a href="/verRutina?id=<%=r.getId()%>" class="list-group-item list-group-item-action">
                    <%= r.getNombre() %> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(r.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
        <div class="col-md-6">
            <h2>Resto de Rutinas</h2>
            <div class="list-group">
                <% for (Rutina r : rutinas) { %>
                <a href="/verRutina?id=<%=r.getId()%>" class="list-group-item list-group-item-action">
                    <%= r.getNombre() %> - Cumplimiento: <%= String.format("%.2f%%", cumplimiento.get(r.getId())) %>
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
