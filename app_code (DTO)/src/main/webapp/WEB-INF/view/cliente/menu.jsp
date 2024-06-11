<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="es.uma.proyectotaw.entity.Comida" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.DietaComida" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="es.uma.proyectotaw.ui.ComidaFiltro" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 30/4/24
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Set<DietaComida> dietaComidas = (Set<DietaComida>) request.getAttribute("dietaComidas");
    String comidaFiltro = request.getParameter("comidaFiltro");
    Cliente client = (Cliente) request.getAttribute("client");
%>
<html>
<head>
    <title>Menú semanal</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<h2>Tu dieta actual:</h2>
<%
    List<Comida> comidasM1 = new ArrayList<>();
    List<Comida> comidasM2 = new ArrayList<>();
    List<Comida> comidasM3 = new ArrayList<>();
    for(DietaComida dietaComida: dietaComidas){
        if(dietaComida.getMomentoDia()==1){
            comidasM1.add(dietaComida.getComida());
        } else if(dietaComida.getMomentoDia()==2){
            comidasM2.add(dietaComida.getComida());
        } else if(dietaComida.getMomentoDia()==3){
            comidasM3.add(dietaComida.getComida());
        }
    }
%>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <%-- Desayuno --%>
            <div class="row">
                <div class="col">
                    <h2 class="mt-5">Desayuno</h2>
                    <ul class="list-group">
                        <% for (Comida comida : comidasM1) { %>
                        <li class="list-group-item"><a href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>"><%=comida.getNombre()%></a></li>
                        <% } %>
                    </ul>
                </div>
            </div>

            <%-- Comida --%>
            <div class="row">
                <div class="col">
                    <h2 class="mt-5">Comida</h2>
                    <ul class="list-group">
                        <% for (Comida comida : comidasM2) { %>
                        <li class="list-group-item"><a href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>"><%=comida.getNombre()%></a></li>
                        <% } %>
                    </ul>
                </div>
            </div>

            <%-- Cena --%>
            <div class="row">
                <div class="col">
                    <h2 class="mt-5">Cena</h2>
                    <ul class="list-group">
                        <% for (Comida comida : comidasM3) { %>
                        <li class="list-group-item"><a href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>"><%=comida.getNombre()%></a></li>
                        <% } %>
                    </ul>
                </div>
            </div>
        </div>

        <div class="col-md-4">
            <div class="mt-5">
                <h2>Filtrar Comidas</h2>
                <form:form method="post" action="/filtrarComida" modelAttribute="comidaFiltro">
                    <div class="form-group">
                        Nombre de Comida: <br>
                        <form:input path="nombre"></form:input>
                    </div>
                    <div class="form-group">
                        Momento del Día:<br>
                        <form:select path="momentoDia">
                            <option value="1">Desayuno</option>
                            <option value="2">Comida</option>
                            <option value="3">Cena</option>
                        </form:select>
                    </div>
                    <form:button class="btn btn-outline-secondary"> Buscar</form:button>
                </form:form>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
