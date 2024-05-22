<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Rutina rutina = (Rutina) request.getAttribute("rutina");
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

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <div class="row">
        <!-- Parte izquierda con tabla y barra de búsqueda -->
        <div class="col  flex-grow-1 col-auto">
            <form:form method="post" action="/bodybuilding/guardarCambiosEdicion" modelAttribute="rutina">
              <form:hidden path="id"/>
              <form:hidden path="entrenador"/>
              <form:hidden path="tipoRutina"/>
              <form:hidden path="clienteRutinas"/>
              <form:hidden path="fechaCreacion"/>
              <form:hidden path="entrenamientoRutinas"/>
            <table class="table">
                <thead>
                <tr>
                    <th scope="col">Nombre</th>
                    <th scope="col">Descripción</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                        <tr>
                            <td><form:input type="text" path="nombre" size="50"/></td>
                            <td><form:input type="text" path="descripcion" size="100"/></td>
                        </tr>
                </tbody>
            </table>
                <h1>Entrenamientos: </h1>
                <table class="table" border="collapse">
                    <%for(EntrenamientoRutina ent : rutina.getEntrenamientoRutinas()){%>
                    <tr>
                        <th scope="col">Día <%=ent.getDiaSemana()%> : </th>
                        <th scope="col"><%=ent.getEntrenamiento().getNombre()%></th>
                        <td scope="col"><% for(EjercicioEntrenamiento ej : ent.getEntrenamiento().getEjercicioEntrenamientos()){ %>
                            <%=ej.getEjercicio().getNombre()%><br/>
                            <%}%>
                        </td>
                    </tr>
                    <%}%>
                </table>
            <div style="text-align: center">
                <form:button class="btn btn-outline-success">Guardar</form:button>
            </div>
            </form:form>
            <form action="/bodybuilding/listar" method="get">
            <div style="text-align: center">
                <button class="btn btn-outline-danger">Descartar cambios</button>
            </div>
        </form>

        <jsp:include page="../header-footer/footer.jsp"></jsp:include>

        <!-- Enlace a Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
