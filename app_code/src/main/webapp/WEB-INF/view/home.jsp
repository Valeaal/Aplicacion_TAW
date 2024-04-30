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
<body>
<jsp:include page="header-footer/navbar.jsp"></jsp:include>

<section class="container mt-5">
    <h2>Descubre nuestros servicios</h2>
    <p  class="fs-5">Bienvenido al mejor centro deportivo de la zona. Ofrecemos una amplia gama de servicios y actividades para mantenerte en forma y saludable.</p>
    <p  class="fs-6">En nuestra plataforma, tenemos actualmente <%= listaEjerciciosCompleta.size() %> ejercicios disponibles para ti.</p>
</section>

<section id="secondary-content" class="container mt-4">
    <p class="fs-6">
        Si quieres iniciar sesión, puedes hacerlo desde el navbar (YA ES FUNCIONAL).<br>
        La idea es que una vez inicies sesión tengas en el navbar las opciones específicas de tu rol.<br>
        La comprobación de usuario y contraseña ya están disponibles.<br>
        El inicio de sesión como administrador ya está disponible.
    </p>
</section>

<jsp:include page="header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
