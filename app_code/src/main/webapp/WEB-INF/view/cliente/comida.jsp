<%@ page import="es.uma.proyectotaw.entity.Comida" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 4/5/24
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Comida comida = (Comida) request.getAttribute("comida");
%>
<html>
<head>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>Ingredientes y pasos para <%=comida.getNombre()%></title>
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container mt-4">
    <h1 class="display-4 mb-3"><%= comida.getNombre() %></h1>
    <p class="lead"><%= comida.getDescripcion() %></p>
</div>
</body>
</html>
