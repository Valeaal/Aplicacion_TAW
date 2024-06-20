<%-- @author: Miguel Galdeano Rodríguez --%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>

<%
    List<UsuarioDTO> clientes = (List<UsuarioDTO>) request.getAttribute("clientes");
    Map<ClienteDTO,RutinaDTO> rutinas = (Map<ClienteDTO,RutinaDTO>) request.getAttribute("rutinas");
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
        <div class="col  flex-grow-1 col-auto">
            <table class="table">
                <thead>
                <tr>
                    <%--<th scope="col"></th>--%>
                    <th scope="col">Nombre</th>
                    <th scope="col">Apellidos</th>
                    <th scope="col">Rutina actual </th>
                    <th scope="col">Ver comentarios</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                <% for (ClienteDTO cliente : rutinas.keySet()) { %>
                <tr>
                    <td><%= cliente.getUsuario().getNombre()%></td>
                    <td><%= cliente.getUsuario().getApellidos()%></td>
                    <td><% if(rutinas.get(cliente)!=null){%>
                    <%=rutinas.get(cliente).getNombre()%>
                    <%}%>
                    </td>
                    <td><a href="/bodybuilding/verComentarios?id=<%=cliente.getId()%>">
                        <button class="btn btn-primary" type="button">Ver</button>
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
