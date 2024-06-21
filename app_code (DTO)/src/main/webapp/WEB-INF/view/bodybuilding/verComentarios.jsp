<%-- @author: Miguel Galdeano Rodríguez --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>
<%@ page import="es.uma.proyectotaw.entity.Desempeno" %>

<%
    Map<Integer,EjercicioEntrenamientoDTO> ENR = (Map<Integer,EjercicioEntrenamientoDTO>) request.getAttribute("ENR");
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<DesempenoDTO> desempenos = (List<DesempenoDTO>) request.getAttribute("desempenos");
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
<div class="row">
    <!-- Parte izquierda con tabla y barra de búsqueda -->
    <h2> Valoraciones del cliente: <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%></h2>
        <% if(ENR.isEmpty()){%>
            <h3>No hay valoraciones.</h3>
        <%}else{ %>
    <div class="col  flex-grow-1 col-auto">
        <table class="table">
            <thead>
            <tr>
                <%--<th scope="col"></th>--%>
                <th scope="col">Nombre del ejercicio </th>
                <th scope="col">Series</th>
                <th scope="col">Repeticiones</th>
                <th scope="col">Peso objetivo</th>
                <th scope="col">Peso realizado </th>
                <th scope="col">Comentarios</th>
                <th scope="col">Valoración</th>
            </tr>
            </thead>
            <tbody>
            <%  for (DesempenoDTO desempeno : desempenos) {
            if(ENR.get(desempeno.getId())!=null){%>
            <tr>
                <td><%= ENR.get(desempeno.getId()).getEjercicio().getNombre()%></td>
                <td><%= ENR.get(desempeno.getId()).getSeries()%></td>
                <td><%= ENR.get(desempeno.getId()).getRepeticiones()%></td>
                <td><%= ENR.get(desempeno.getId()).getPeso()%></td>
                <td><%= desempeno.getPesoRealizado()%></td>
                <td><%= desempeno.getComentarios()%></td>
                <td><%= desempeno.getValoracion()%></td>
                <td></td>
            </tr>
            <%
                    }
                }
            } %>
            </tbody>
        </table>
        <a href="/bodybuilding/seguimientoClientes">
            <button class="btn btn-primary" type="button">Volver</button>
        </a>
    </div>

    <jsp:include page="../header-footer/footer.jsp"></jsp:include>

    <!-- Enlace a Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
