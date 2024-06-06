<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 6/6/24
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Entrenamiento> entrenamiento = (List<Entrenamiento>) request.getAttribute("entrenamientos");
    HashMap<Integer,Float> cumplimiento = (HashMap<Integer,Float>) request.getAttribute("cumplimiento");
%>
<html>
<head>
    <title>Ver rutina</title>
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
<div class="container-fluid">
    <div class="row mt-4">
        <!-- Entrenamientos Section -->
        <div class="col-md-8">
            <h3>Entrenamientos</h3>
            <%
                int dia = 1;
            %>
            <div class="list-group">
                <% for (Entrenamiento e : entrenamiento) {
                    float c = cumplimiento.get(e.getId());
                %>
                <a href="#"
                   class="list-group-item list-group-item-action d-flex justify-content-between align-items-center">
                    Día <%= dia %>
                    <span class="badge badge-primary badge-pill"></span><%=c%> %
                </a>
                <% dia++; %>
                <% } %>
            </div>
        </div>
    </div>
</div>
</body>
</html>
