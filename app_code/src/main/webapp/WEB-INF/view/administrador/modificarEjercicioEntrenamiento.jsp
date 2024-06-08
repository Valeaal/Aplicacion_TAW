<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de dimensiones de ejercicios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container">
    <h2 class="my-4">Modificar las dimensiones de un ejercicio</h2>

    <!-- Tarjeta explicativa -->
    <div class="card mb-4">
        <div class="card-body">
            <p class="card-text">
                Si desea retirar una propiedad de un ejercicio concreto, como por ejemplo el peso, puede borrar el valor del campo correspondiente en lugar de usar las flechas para ajustar el valor.
            </p>
        </div>
    </div>

    <form:form modelAttribute="ejercicioEntrenamiento" action="/admin/ejerciciosEntrenamientos/guardar" method="post">

        <form:hidden path="id" />
        <form:hidden path="desempeno" />

        <div class="mb-3">
            <form:label class="form-label" path="ejercicio">Ejercicio al que instancia:</form:label>
            <form:select class="form-select" path="ejercicio" items="${ejercicios}" itemValue="id" itemLabel="nombre" />
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="entrenamiento">Entrenamiento al que pertenece:</form:label>
            <form:select class="form-select" path="entrenamiento" items="${entrenamientos}" itemLabel="nombre" itemValue="id" />
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="series">Número de series:</form:label>
            <form:input class="form-control" path="series" type="number" min="0"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="repeticiones">Número de repeticiones:</form:label>
            <form:input class="form-control" path="repeticiones" type="number" min="0"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="peso">Peso:</form:label>
            <form:input class="form-control" path="peso" type="number" min="0"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="tiempo">Tiempo:</form:label>
            <form:input class="form-control" path="tiempo" type="number" min="0"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="distancia">Distancia:</form:label>
            <form:input class="form-control" path="distancia" type="number" min="0"/>
        </div>

        <div class="mb-3">
            <form:label class="form-label" path="orden">Orden dentro del entrenamiento*:</form:label>
            <form:input class="form-control" path="orden" type="number" min="1" required="required"/>
        </div>

        <!-- Botón de enviar -->
        <button type="submit" class="btn btn-primary">Modificar las dimensiones del ejercicio</button>
    </form:form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
