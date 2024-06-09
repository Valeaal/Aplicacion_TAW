<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashMap" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 30/4/24
  Time: 19:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Rutina rutina = (Rutina) request.getAttribute("rutina");
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    List<Entrenamiento> entrenamiento = (List<Entrenamiento>) request.getAttribute("entrenamientos");
    HashMap<Integer,Float> cumplimiento = (HashMap<Integer,Float>) request.getAttribute("cumplimiento");
    HashMap<Integer,Integer> dia = (HashMap<Integer,Integer>) request.getAttribute("dia");
%>
<html>
<head>
    <title>Tu rutina activa</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row mt-4">
        <h2>Tu rutina actual:</h2>
        <!-- Entrenamientos Section -->
        <div class="col-md-8">
            <h3>Entrenamientos</h3>
            <div class="list-group">
                <% for (Entrenamiento e : entrenamiento) {
                    float c = cumplimiento.get(e.getId());
                    int diaSemana = dia.get(e.getId());
                %>
                <a href="/dia?id=<%=e.getId()%>&clientId=<%=cliente.getId()%>"
                   class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    Día <%= diaSemana %>
                    <span class="badge badge-primary badge-pill"></span><%=c%> %
                </a>
                <% } %>
            </div>
        </div>

        <!-- Filtro -->
        <div class="col-md-4">
            <h3>Filtrar por nombre de rutina:</h3>
            <form:form action="/filtrarRutina" method="post" modelAttribute="rutinaFiltro">
                <form:hidden path="clienteId" value="<%=cliente.getId()%>"/>
                <form:input path="nombre"/>
                <form:button>Buscar</form:button>
            </form:form>
            <div class="container">
                <div class="col-md-6">
                    <h3>Filtrar por desempeño:</h3>
                    <form:form modelAttribute="desempenyoFiltro" method="post"
                               action="/filtrarRutinaDesempenyo">
                        <form:hidden path="idCliente" value="<%=cliente.getId()%>"/>
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

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
