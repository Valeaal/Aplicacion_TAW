<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Autor: Álvaro Valencia Villalón -->


<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de ejercicios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>


<div class="container">
    <h2 class="my-4">Crear un ejercicio</h2>

    <form:form modelAttribute="ejercicioDTO" action="/admin/ejercicios/guardar" method="post">

        <form:hidden path="id" />

        <div class="mb-3">
            <form:label class="form-label" path="nombre">Nombre:</form:label>
            <form:input class="form-control" path="nombre" />
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="nombre">Descripción:</form:label>
            <form:input class="form-control" path="descripcion"  />
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="urlVideo">URL del vídeo:</form:label>
            <form:input class="form-control" path="urlVideo" />
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="tipo.id">Tipo de ejercicio:</form:label>
            <form:select class="form-select" path="tipo.id" items="${tiposEjercicio}" itemLabel="tipo" itemValue="id"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="grupoMuscular.id">Grupo Muscular:</form:label>
            <form:select class="form-select" path="grupoMuscular.id" items="${gruposMusculares}" itemLabel="grupo" itemValue="id"/>
        </div>

        <!-- Botón de enviar -->
        <button type="submit" class="btn btn-primary">Añadir Ejercicio</button>
    </form:form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
