<!-- Autor: Álvaro Valencia Villalón -->

<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Usuario usrActual = (Usuario) session.getAttribute("usuario");
    TipoUsuario tipoUsr = (TipoUsuario) session.getAttribute("tipo");
    String nombre = "No has iniciado sesión";
    if (usrActual != null) {
        nombre = usrActual.getNombre() + " : " + tipoUsr.getTipo();
    }
%>

<html>

<head>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>TAW G4</title>
</head>

<body class="d-flex flex-column min-vh-100">

<%
    if (tipoUsr != null && tipoUsr.getTipo().equals("admin")) { //Mostramos el navbar con las opciones del administrador
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><%= nombre.toString() %></a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/clientesEntrenadores">Clientes -> Entrenadores</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/clientesDietistas">Clientes -> Dietistas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/usuarios">Gestionar usuarios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/ejercicios">Gestionar ejercicios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/menus">Gestionar menús</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/admin/ejerciciosEntrenamientos">Gestionar dimensiones de ejercicios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/salir">Salir</a>
                </li>
            </ul>
        </div>
    </div>
</nav>


<%
} else if (tipoUsr != null && tipoUsr.getTipo().equals("dietista")) {    //Mostramos el navbar con las opciones del dietista
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><%= nombre.toString() %>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dietas">Dietas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dietistaAsignacion">Asignación Dietas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="dietistaSeguimiento">Seguimiento Dietas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/salir">Salir</a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<%
} else if (tipoUsr != null && tipoUsr.getTipo().equals("entrenador_crossfit")) {    //Mostramos el navbar con las opciones del entrenador crossfit
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><%= nombre.toString() %>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/crud">CRUD</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/ClientesRutinas">Asignar Rutinas</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/seguimientoRutinas">Seguimiento Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/salir">Salir</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%
} else if (tipoUsr != null && tipoUsr.getTipo().equals("entrenador_bodybuilding")) {    //Mostramos el navbar con las opciones del entrenador bodybuilding
%>
<h1>Hola bodybuilder ponme en forma TÚ!</h1>

<%
} else if (tipoUsr != null && tipoUsr.getTipo().equals("cliente")) {
%>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><%= nombre.toString() %>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/rutina?id=<%=usrActual.getId()%>">Rutina</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/menu?id=<%=usrActual.getId()%>">Menú</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/salir">Cerrar Sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%
} else {    //Mostramos el navbar para la persona no registrada
%>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="#"><%= nombre.toString() %>
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link active" href="/">Inicio</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Servicios</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Contacto</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/login">Iniciar Sesión</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<%
    }
%>
<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>
