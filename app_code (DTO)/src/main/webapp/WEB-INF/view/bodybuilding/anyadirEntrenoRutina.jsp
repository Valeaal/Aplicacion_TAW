<%-- @author: Miguel Galdeano Rodríguez --%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    EntrenamientoRutinaDTO entrenamientoRutina = (EntrenamientoRutinaDTO) request.getAttribute("ER");
%>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Página</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<div class="container-fluid">
    <div class="row">
        <!-- Parte izquierda con tabla y barra de búsqueda -->
        <h1>Elige un entrenamiento para añadir a la rutina <%=entrenamientoRutina.getRutina().getNombre()%> en el día <%=entrenamientoRutina.getDiaSemana()%></h1>
        <div class="col  flex-grow-1 col-auto">
            <form:form method="post" action="/bodybuilding/guardarEntrenamientoRutina" modelAttribute="ER">
                <form:hidden path="id"/>
                <form:hidden path="rutina"/>
                <form:hidden path="diaSemana"/>
            <div style="display: flex; flex-direction:column">
                <form:radiobuttons path="entrenamiento" items="${entrenamientos}" itemLabel="nombre"/>
            </div>
            <div style="text-align: center">
                <form:button class="btn btn-outline-success">Añadir</form:button>
            </div>
            </form:form>

                <div style="text-align: center">
                    <a href="/bodybuilding/editarRutina?id=<%=entrenamientoRutina.getRutina().getId()%>"><button class="btn btn-outline-danger">Descartar cambios</button></a>
                </div>


            <jsp:include page="../header-footer/footer.jsp"></jsp:include>

            <!-- Enlace a Bootstrap JS -->
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
