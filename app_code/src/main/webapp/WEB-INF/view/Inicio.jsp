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

<body class="d-flex flex-column min-vh-100">

<!-- Navbar con Bootstrap, perdón aún no tengo claro cómo hacer correctamente la navegación entre páginas -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Centro Deportivo</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="#">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Servicios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Registrarse</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Iniciar Sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<section id="main-content" class="container mt-5">
    <h2>Descubre nuestros servicios</h2>
    <p>Bienvenido al mejor centro deportivo de la zona. Ofrecemos una amplia gama de servicios y actividades para mantenerte en forma y saludable.</p>
    <p>En nuestra plataforma, tenemos actualmente <%= listaEjerciciosCompleta.size() %> ejercicios disponibles para ti.</p>
</section>

<!-- Footer -->
<footer class="footer mt-auto py-3 bg-light">
    <div class="container text-center">
        <p class="mb-0">&copy; 2024 Universidad de Málaga.</p>
        <p class="mb-0">Grupo 4: Alba, Torrecilla, Miki, Alonso, Álvaro.</p>
    </div>
</footer>

<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
