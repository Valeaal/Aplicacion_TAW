<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Comida" %>
<%@ page import="es.uma.proyectotaw.entity.ComidaMenu" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 4/5/24
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Comida comida = (Comida) request.getAttribute("comida");
    Cliente client = (Cliente) request.getAttribute("client");
    Set<ComidaMenu> menus = (Set<ComidaMenu>) request.getAttribute("menus");
    HashMap<Integer, Integer> desempenyo = (HashMap<Integer, Integer>) request.getAttribute("desempenyo");
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
    <div class="row">
        <%
            for (ComidaMenu cm : menus) {
                int realizado = desempenyo.get(cm.getMenu().getId());
        %>
        <div class="col-md-6 col-lg-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title"><%= cm.getMenu().getNombre() %></h5>
                    <p class="card-text"><%= cm.getMenu().getDescripcion() %></p>
                </div>
                <div class="card-footer">
                    <%
                        if (realizado == 0) {
                    %>
                    <a href="/dietaDesempeno?id=<%=cm.getMenu().getId()%>&comidaId=<%=comida.getId()%>&clientId=<%=client.getId()%>" class="btn btn-primary">Valorar</a>
                    <%
                    } else {
                    %>
                    <a href="/verDietaDesempeno?id=<%=cm.getMenu().getId()%>&comidaId=<%=comida.getId()%>&clientId=<%=client.getId()%>" class="btn btn-secondary">Ver valoración</a>
                    <td><a href="/eliminarDesempenoMenu?id=<%=cm.getMenu().getId()%>&clientId=<%=client.getId()%>&comidaId=<%=cm.getComida().getId()%>" class="btn btn-primary">Eliminar valoración</a></td>

                    <%
                        }
                    %>
                </div>
            </div>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>
