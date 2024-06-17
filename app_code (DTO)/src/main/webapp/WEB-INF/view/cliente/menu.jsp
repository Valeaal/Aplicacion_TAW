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
                            <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                                <%=comida.getNombre()%>
                            </a>
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
                            <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                                <%=comida.getNombre()%>
                            </a>
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
                            <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                                <%=comida.getNombre()%>
                            </a>
                        <% } %>
                    </ul>
                </div>
            </div>

        </div>

        <div class="col-md-4">
            <div class="container">
                <div class="col-md-6">
                    <h3>Filtrar por desempeño de dieta:</h3>
                    <form:form modelAttribute="desempenyoFiltro" method="post"
                               action="/filtrarDietaDesempenyo">
                        <form:hidden path="idCliente" value="<%=client.getId()%>"/>
                        <div class="form-group">
                            <form:select path="desempenyo" id="desempenyo">
                                <form:option value="Alto">Alto (70%-100%)</form:option>
                                <form:option value="Medio">Medio (30%-70%)</form:option>
                                <form:option value="Bajo">Bajo (0%-30%)</form:option>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-outline-secondary">Buscar</button>
                    </form:form>
                </div>
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
