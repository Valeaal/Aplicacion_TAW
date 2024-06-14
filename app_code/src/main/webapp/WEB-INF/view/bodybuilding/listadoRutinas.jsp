<%-- @author: Miguel Galdeano Rodríguez --%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>

<%
   List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nueva Página</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <form:form action="/bodybuilding/filtrarRutinas" method="post" modelAttribute="filtro">
        <div class="mb-3 d-flex w-100">
            <div class="table-responsive w-100">
                 <table class="table w-100" style="border-collapse: separate; border-spacing: 20px;">
                    <thead>
                        <tr>
                            <th>Nombre de la rutina </th>
                            <th>Numero de entrenamientos </th>
                            <th>Creado a partir de :  </th>
                            <th>Creador </th>
                            <th></th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td> <form:input type="text" name="ejercicio" path="nombre"/></td>
                            <td><form:select path="numEntrenamientos" items="${['-',1,2,3,4,5,6,7]}"/></td>
                            <td><form:input type="date" path="fecha" /></td>
                            <td><form:select path="entrenadorCreador">
                                <form:option value="-1">TODOS</form:option>
                                <form:options items="${entrenadores}" itemLabel="nombre"/>
                            </form:select></td>
                            <td> <form:button type="submit" class="btn btn-primary">Buscar</form:button></td>
                            <td><a href="/bodybuilding/">
                                <button class="btn btn-danger" type="button">Eliminar filtros</button>
                            </a></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </form:form>
    <div class="row">
        <!-- Parte izquierda con tabla y barra de búsqueda -->
        <div class="col  flex-grow-1 col-auto">
            <form action="/bodybuilding/crearRutina" method="get">
                <div style="text-align: center">
                    <button class="btn btn-success">Crear nueva rutina</button>
                </div>
            </form>
            <table class="table">
                <thead>
                <tr>
                    <%--<th scope="col"></th>--%>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripción</th>
                    <th scope="col">Creador</th>
                    <th scope="col">Nº Entrenamientos</th>
                    <th scope="col">Tipo de rutina</th>
                    <th scope="col">Fecha creación</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <% for (Rutina rutina : rutinas) { %>
                <tr>
                    <td><%= rutina.getNombre()%></td>
                    <td><%= rutina.getDescripcion()%></td>
                    <td><%= rutina.getEntrenador().getNombre()%> <%= rutina.getEntrenador().getApellidos()%></td>
                    <td><%= rutina.getEntrenamientos().size()%></td>
                    <td><%= rutina.getTipoRutina().getTipo()%></td>
                    <td><%= rutina.getFechaCreacion()%></td>
                    <td><a href="/bodybuilding/editarRutina?id=<%=rutina.getId()%>">
                        <button class="btn btn-primary" type="button">Editar</button>
                    </a></td>
                    <td><a href="/bodybuilding/eliminarRutina?id=<%=rutina.getId()%>">
                         <button class="btn btn-danger" type="button">Eliminar</button>
                    </a></td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
