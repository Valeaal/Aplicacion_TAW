<%@ page import="org.springframework.web.bind.annotation.RequestParam" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.GrupoMuscular" %><%--
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
    List<GrupoMuscular> grupomuscular= (List<GrupoMuscular>) request.getAttribute("grupomuscular");
%>
<html>
<head>
    <title>Tus rutinas</title>
    <!-- Include Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row mt-4">
        <!-- Entrenamientos Section -->
        <div class="col-md-8">
            <h3>Entrenamientos</h3>
            <%
                int dia = 1;
            %>
            <div class="list-group">
                <% for (Entrenamiento e : entrenamiento) { %>
                <a href="/dia?id=<%=e.getId()%>" class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    Día <%= dia %>
                    <span class="badge badge-primary badge-pill">%%</span> <!-- Aquí va el porcentaje real -->
                </a>
                <% dia++; %>
                <% } %>
            </div>
        </div>

        <!-- Filtro -->
        <div class="col-md-4">
            <h3>Buscar</h3>
            <div class="input-group mb-3">
                <input type="text" class="form-control" placeholder="Buscar..." aria-label="Buscar">
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button">Buscar</button>
                </div>
            </div>
            <div class="mb-3">
                <select class="custom-select">
                    <option selected>% Desempeño</option>
                    <option value="1">Alto</option>
                    <option value="2">Medio</option>
                    <option value="3">Bajo</option>
                </select>
            </div>
            <div class="mb-3">
                <select class="custom-select">
                    <option selected>Peso</option>
                    <option value="1">Ligero</option>
                    <option value="2">Medio</option>
                    <option value="3">Pesado</option>
                </select>
            </div>
            <div class="mb-3">
                <select class="custom-select">
                    <option selected>Zona del Cuerpo</option>
                    <%
                        for(GrupoMuscular grupo : grupomuscular) {

                    %>
                    <option value="<%=grupo.getId()%>"><%=grupo.getGrupo()%></option>
                    <%
                        }
                    %>
                </select>
            </div>
        </div>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
