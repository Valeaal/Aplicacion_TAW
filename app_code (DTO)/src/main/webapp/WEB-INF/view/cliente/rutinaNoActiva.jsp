<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.EntrenamientoDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 6/6/24
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<EntrenamientoDTO> entrenamiento = (List<EntrenamientoDTO>) request.getAttribute("entrenamientos");
    HashMap<Integer,Float> cumplimiento = (HashMap<Integer,Float>) request.getAttribute("cumplimiento");
    HashMap<Integer,Integer> dia = (HashMap<Integer,Integer>) request.getAttribute("dia");

%>
<html>
<head>
    <title>Ver rutina</title>
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row mt-4">
        <!-- Entrenamientos Section -->
        <div class="col-md-8">
            <h3>Entrenamientos</h3>
            <div class="list-group">
                <% for (EntrenamientoDTO e : entrenamiento) {
                    float c = cumplimiento.get(e.getId());
                    int diaSemana = dia.get(e.getId());
                %>
                <a href="#"
                   class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    Día <%= diaSemana %>
                    <span class="badge badge-primary badge-pill"></span><%=c%> %
                </a>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
