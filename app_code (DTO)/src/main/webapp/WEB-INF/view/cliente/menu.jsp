<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Dieta" %>
<%@ page import="es.uma.proyectotaw.entity.Comida" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.DietaComida" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.Set" %>
<%@ page import="es.uma.proyectotaw.ui.ComidaFiltro" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.dto.DietaComidaDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ClienteDTO" %>
<%@ page import="es.uma.proyectotaw.dto.ComidaDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 30/4/24
  Time: 19:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String comidaFiltro = request.getParameter("comidaFiltro");
    ClienteDTO client = (ClienteDTO) request.getAttribute("client");
    List<ComidaDTO> comidasM1 = (List<ComidaDTO>) request.getAttribute("comidasM1");
    List<ComidaDTO> comidasM2 = (List<ComidaDTO>) request.getAttribute("comidasM2");
    List<ComidaDTO> comidasM3 = (List<ComidaDTO>) request.getAttribute("comidasM3");
%>
<html>
<head>
    <title>Menú semanal</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container mt-5">
    <div class="row">
        <div class="col-12 text-center">
            <h2 class="text-primary">Tu dieta actual</h2>
            <p class="lead">En la siguiente sección aparece el menú semanal, definido por su dietista.</p>
        </div>
    </div>
    <div class="row mt-4">
        <!-- Sección de Entrenamientos -->
        <div class="col-md-8">
            <%-- Desayuno --%>
            <div class="mb-4">
                <h3 class="text-secondary">Desayuno</h3>
                <ul class="list-group">
                    <% for (ComidaDTO comida : comidasM1) { %>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                        <%=comida.getNombre()%>
                    </a>
                    <% } %>
                </ul>
            </div>

            <%-- Comida --%>
            <div class="mb-4">
                <h3 class="text-secondary">Comida</h3>
                <ul class="list-group">
                    <% for (ComidaDTO comida : comidasM2) { %>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                        <%=comida.getNombre()%>
                    </a>
                    <% } %>
                </ul>
            </div>

            <%-- Cena --%>
            <div class="mb-4">
                <h3 class="text-secondary">Cena</h3>
                <ul class="list-group">
                    <% for (ComidaDTO comida : comidasM3) { %>
                    <a class="list-group-item list-group-item-action d-flex justify-content-between align-items-center" href="/comida?id=<%=comida.getId()%>&clientId=<%=client.getId()%>">
                        <%=comida.getNombre()%>
                    </a>
                    <% } %>
                </ul>
            </div>
        </div>

        <!-- Filtro -->
        <div class="col-md-4">
            <div class="card">
                <div class="card-body">
                    <h3 class="card-title text-secondary">Filtrar por desempeño de dieta:</h3>
                    <form:form modelAttribute="desempenyoFiltro" method="post" action="/filtrarDietaDesempenyo">
                        <form:hidden path="idCliente" value="<%=client.getId()%>"/>
                        <div class="form-group">
                            <form:select path="desempenyo" id="desempenyo" class="form-control">
                                <form:option value="Alto">Alto (70%-100%)</form:option>
                                <form:option value="Medio">Medio (30%-70%)</form:option>
                                <form:option value="Bajo">Bajo (0%-30%)</form:option>
                            </form:select>
                        </div>
                        <button type="submit" class="btn btn-outline-primary btn-block">Buscar</button>
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
