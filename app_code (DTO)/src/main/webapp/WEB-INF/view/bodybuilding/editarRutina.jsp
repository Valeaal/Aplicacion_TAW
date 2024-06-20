<%-- @author: Miguel Galdeano Rodríguez --%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    RutinaDTO rutina = (RutinaDTO) request.getAttribute("rutina");
    Map<Integer,EjercicioDTO> ejercicios = (Map<Integer,EjercicioDTO>) request.getAttribute("ejercicios");
    Map<Integer,EntrenamientoDTO> entrenamientos = (Map<Integer,EntrenamientoDTO>) request.getAttribute("entrenamientos");
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
        <div class="col  flex-grow-1 col-auto">
            <form:form method="post" action="/bodybuilding/guardarCambiosEdicion" modelAttribute="rutina">
              <form:hidden path="id"/>
              <form:hidden path="entrenador.id"/>
              <form:hidden path="tipoRutina.id"/>
              <form:hidden path="fechaCreacion"/>
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
                <% if(rutina.getId()==null){%>
                <h3>Para añadir nuevos entrenamientos debe guardar la rutina primero. </h3>
                <table class="table" border="collapse">
                    <%for(int i = 1; i<8;i++){%>
                <tr>
                    <th scope="col">Día <%=i%> : </th>
                    <th scope="col">No hay entrenamiento</th>
                    <td></td>
                    <td scope="col">
                        <button class="btn btn-warning" type="button" disabled>Añadir entrenamiento</button>
                    </td>
                </tr>
                    <%  } %>
                </table>
              <% }else {%>
                <h2>Entrenamientos: </h2>
                <table class="table" border="collapse">
                    <%
                    EntrenamientoRutinaDTO entreno = null;
                    for(int i = 1; i<8;i++){
                    for(EntrenamientoRutinaDTO ent: rutina.getEntrenamientos()) {
                        if(ent.getDiaSemana() == i){
                            entreno = ent;
                        }
                    }
                        if(entreno != null){
                    %>
                    <tr>
                        <th scope="col">Día <%=i%> : </th>
                        <th scope="col"><%=entrenamientos.get(entreno.getEntrenamiento())%></th>
                        <td scope="col"><% for(Integer ej : entrenamientos.get(entreno.getEntrenamiento()).getEjercicios()){ %>
                            <%=ejercicios.get(ej).getNombre()%><br/>
                            <%}%>
                        </td>
                        <td scope="col">
                            <a href="/bodybuilding/eliminarEntrenamientoRutina?id=<%=entreno.getId()%>">
                                <button class="btn btn-danger" type="button">Eliminar entrenamiento</button>
                            </a>
                        </td>
                    </tr>
                    <%}else{%>
                    <tr>
                        <th scope="col">Día <%=i%> : </th>
                        <th scope="col">No hay entrenamiento</th>
                        <td></td>
                        <td scope="col">
                            <a href="/bodybuilding/anyadirEntrenamientoRutina?id=<%=rutina.getId()%>&dia=<%=i%>">
                                <button class="btn btn-warning" type="button">Añadir entrenamiento</button>
                            </a>
                        </td>
                    </tr>
                    <%}
                        entreno = null;
                    }%>
                </table>
                <%}%>
            <div style="text-align: center">
                <form:button class="btn btn-outline-success">Guardar</form:button>
            </div>
            </form:form>

            <form action="/bodybuilding/" method="get">
            <div style="text-align: center">
                <button class="btn btn-outline-danger">Descartar cambios</button>
            </div>
        </form>

        <jsp:include page="../header-footer/footer.jsp"></jsp:include>

        <!-- Enlace a Bootstrap JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
