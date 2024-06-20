<!-- Autor: Álvaro Valencia Villalón -->

<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.dto.EjercicioDTO" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<EjercicioDTO> listaEjerciciosCompleta = (List<EjercicioDTO>) request.getAttribute("listaEjerciciosCompleta");
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
    <p  class="fs-6">En nuestra plataforma, tenemos actualmente <strong><%= listaEjerciciosCompleta.size() %></strong> ejercicios disponibles para ti.</p>
</section>

<section id="secondary-content" class="container mt-5">
    <p class="fs-6">
        <strong>DTO version</strong> <hr>
        <ul class="list-group list-group-flush">
            <li class="list-item">
                <strong>Para iniciar sesión</strong> puede usar el icono correspondiente del NavBar.
            </li>
            <li class="list-item">
                <strong>El NavBar</strong> se actualizará dependiendo del rol con el que haya iniciado sesión, permitiéndole realizar las diferentes operaciones disponibles.
            </li>
            <li class="list-item">
                Para la realización de la vista hemos usado <strong>Bootstrap y Composite-View</strong>.
            </li>
            <li class="list-item">
                Con la entrega se proporcionan scripts para crear e insertar los datos necesarios en la <strong>base de datos</strong>.
            </li>
        </ul>
    </p>
</section>

<jsp:include page="header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
