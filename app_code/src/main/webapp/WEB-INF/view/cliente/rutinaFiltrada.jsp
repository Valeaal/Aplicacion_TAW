<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 5/5/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Rutinas filtradas</title>
</head>
<body>
<%
    for(Ejercicio ejercicio : ejercicios) {
%>
<p><%=ejercicio.getNombre()%></p>
<%
    }
%>
</body>
</html>
