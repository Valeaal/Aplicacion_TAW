<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Ejercicio> listaEjerciciosCompleta = (List<Ejercicio>) request.getAttribute("listaEjerciciosCompleta");
%>

<html>

<head>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>TAW G4</title>
</head>

<jsp:include page="header-footer/navbar.jsp"></jsp:include>

<section id="main-content" class="container mt-5">
    <h2>Descubre nuestros servicios</h2>
    <p>Bienvenido al mejor centro deportivo de la zona. Ofrecemos una amplia gama de servicios y actividades para mantenerte en forma y saludable.</p>
    <p>En nuestra plataforma, tenemos actualmente <%= listaEjerciciosCompleta.size() %> ejercicios disponibles para ti.</p>
</section>

<jsp:include page="header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
