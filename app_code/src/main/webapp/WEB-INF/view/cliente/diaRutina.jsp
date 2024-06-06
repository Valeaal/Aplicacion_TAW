<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 1/5/24
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    HashMap<Integer, List<Float>> map = (HashMap<Integer, List<Float>>) request.getAttribute("map");
    Integer clientId = (Integer) request.getAttribute("clientId");
    Integer entrenamientoId = (Integer) request.getAttribute("entrenamientoId");
%>
<html>
<head>
    <title>Día</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Ejercicio</th>
            <th>Video</th>
            <th>Series</th>
            <th>Repeticiones</th>
            <th>Peso</th>
            <th>Valoración</th>
        </tr>
        </thead>
        <tbody>
        <% for(Ejercicio ejercicio : ejercicios) {
            List<Float> descripcion = map.get(ejercicio.getId());
            int series = (int)(float)descripcion.get(0);
            int reps = (int)(float)descripcion.get(1);
            Float peso = descripcion.get(2);
            int realizado = (int) (float)descripcion.get(3);
        %>
        <tr>
            <td><%= ejercicio.getNombre() %></td>
            <td>
                <a href="<%= ejercicio.getUrlVideo() %>" target="_blank" class="btn btn-primary">
                     <i class="fa fa-play"></i>
                </a>
            </td>
            <td><%= series %></td>
            <td><%= reps %></td>
            <td><%= peso %></td>
            <%
                if(realizado == 0){
            %>
            <td><a href="/desempeno?id=<%=ejercicio.getId()%>&clientId=<%=clientId%>&entrenamientoId=<%=entrenamientoId%>" class="btn btn-primary">Valorar</a></td>
            <%
                } else {
            %>
            <td><a href="/verDesempeno?id=<%=ejercicio.getId()%>&clientId=<%=clientId%>&entrenamientoId=<%=entrenamientoId%>" class="btn btn-primary">Ver valoración</a></td>

            <%
                }
            %>
        </tr>
        <% } %>
        </tbody>
    </table>
</div>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
</body>
</html>
