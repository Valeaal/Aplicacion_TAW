<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="es.uma.proyectotaw.entity.Desempeno" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %>
<%@ page import="es.uma.proyectotaw.dto.ClienteDTO" %>
<%@ page import="es.uma.proyectotaw.dto.EjercicioDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 2/5/24
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EjercicioDTO ejercicio = (EjercicioDTO) request.getAttribute("ejercicio");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    Integer entrenamientoId = (Integer) request.getAttribute("entrenamientoId");
%>
<html>
<head>
    <title>Desempeño del ejercicio</title>
</head>
<body class="bg-light">
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container mt-5">
    <div class="row">
        <div class="col-12">
            <h2 class="text-center text-primary">Valorar Ejercicio</h2>
            <p class="lead text-center">
                Está valorando el ejercicio <strong><%=ejercicio.getNombre()%></strong>, incluya una nota, el peso realizado y un comentario para su entrenador:
            </p>
        </div>
    </div>
    <div class="row justify-content-center mt-4">
        <div class="col-md-6">
            <form:form modelAttribute="desempeno" method="post" action="/guardarDesempeno">
                <form:hidden path="ejercicio" value="<%=ejercicio.getId()%>"/>
                <form:hidden path="entrenamiento" value="<%=entrenamientoId%>"/>
                <form:hidden path="cliente" value="<%=cliente.getId()%>"/>

                <div class="form-group">
                    <label for="valoracion">Valoración:</label>
                    <form:input path="valoracion" id="valoracion" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="pesoRealizado">Peso realizado:</label>
                    <form:input path="pesoRealizado" id="pesoRealizado" class="form-control"/>
                </div>

                <div class="form-group">
                    <label for="comentarios">Comentarios:</label>
                    <form:input path="comentarios" id="comentarios" class="form-control"/>
                </div>

                <div class="text-center">
                    <form:button class="btn btn-primary">Enviar</form:button>
                </div>
            </form:form>
        </div>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
