<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Comida" %>
<%@ page import="es.uma.proyectotaw.entity.ComidaMenu" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.dto.ComidaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ClienteDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ComidaMenuDTO" %>
<%@ page import="es.uma.proyectotaw.dto.MenuDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 4/5/24
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ComidaDTO comida = (ComidaDTO) request.getAttribute("comida");
    ClienteDTO client = (ClienteDTO) request.getAttribute("client");
    Set<ComidaMenuDTO> menus = (Set<ComidaMenuDTO>) request.getAttribute("menus");
    HashMap<Integer, Integer> desempenyo = (HashMap<Integer, Integer>) request.getAttribute("desempenyo");
    HashMap<Integer, List<String>> desc = (HashMap<Integer, List<String>>) request.getAttribute("desc");
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
            for (ComidaMenuDTO cm : menus) {
                int realizado = desempenyo.get(cm.getMenu());
                List<String> list = desc.get(cm.getId());
        %>
        <div class="col-md-6 col-lg-4 mb-4">
            <div class="card h-100">
                <div class="card-body">
                    <h5 class="card-title"><%= list.get(0) %></h5>
                    <p class="card-text"><%= list.get(1) %></p>
                </div>
                <div class="card-footer">
                    <%
                        if (realizado == 0) {
                    %>
                    <a href="/dietaDesempeno?id=<%=cm.getMenu()%>&comidaId=<%=comida.getId()%>&clientId=<%=client.getId()%>" class="btn btn-primary">Valorar</a>
                    <%
                    } else {
                    %>
                    <a href="/verDietaDesempeno?id=<%=cm.getMenu()%>&comidaId=<%=comida.getId()%>&clientId=<%=client.getId()%>" class="btn btn-secondary">Ver valoración</a>
                    <td><a href="/eliminarDesempenoMenu?id=<%=cm.getMenu()%>&clientId=<%=client.getId()%>&comidaId=<%=cm.getComida()%>" class="btn btn-primary">Eliminar valoración</a></td>

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
