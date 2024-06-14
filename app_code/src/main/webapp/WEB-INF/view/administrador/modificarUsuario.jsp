<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Set" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Autor: Álvaro Valencia Villalón -->


<%
    Usuario usr = (Usuario) request.getAttribute("usuario");
    List<TipoUsuario> roles = (List<TipoUsuario>) request.getAttribute("roles");
%>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de usuarios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container">
    <h2 class="my-4">Editar Usuario <%= usr.getNombre()%></h2>

    <form action="/admin/usuarios/actualizar" method="post">

        <!-- Campo oculto para el ID-->
        <input type="hidden" name="usuarioId" value="<%=usr.getId()%>">

        <!-- Campo de Email -->
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="text" class="form-control" id="email" name="inputEmail" value="<%=usr.getEmail()%>" required>
        </div>

        <!-- Campo de Nombre -->
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre</label>
            <input type="text" class="form-control" id="nombre" name="inputNombre" value="<%=usr.getNombre()%>" required>
        </div>

        <!-- Campo de Apellidos -->
        <div class="mb-3">
            <label for="apellidos" class="form-label">Apellidos</label>
            <input type="text" class="form-control" id="apellidos" name="inputApellidos" value="<%=usr.getApellidos()%>" required>
        </div>

        <!-- Campo de Fecha de Nacimiento -->
        <div class="mb-3">
            <label for="fechaNacimiento" class="form-label">Fecha de Nacimiento</label>
            <input type="date" class="form-control" id="fechaNacimiento" name="inputNacimiento" value="<%=usr.getFechaNacimiento()%>" required>
        </div>

        <!-- Campo de Fecha de Ingreso -->
        <div class="mb-3">
            <label for="perteneceDesde" class="form-label">Fecha de Ingreso</label>
            <input type="date" class="form-control" id="perteneceDesde" name="inputIngreso"
                   value="<%=usr.getFechaNacimiento()%>" required>
        </div>

        <!-- Campo de Rol -->
        <div class="mb-3">
            <label for="rol" class="form-label">Rol</label>
            <select class="form-select" id="rol" name="inputRol">
                <option selected><%= usr.getTipoUsuario().getTipo()%></option>
                <%
                for ( TipoUsuario rol : roles){
                    if (rol != usr.getTipoUsuario()){
                %>
                        <option><%= rol.getTipo()%></option>
                <%
                    }
                }
                %>
            </select>
        </div>

        <!-- Botón de enviar -->
        <button type="submit" class="btn btn-primary">Actualizar Usuario</button>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
