<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 1/5/24
  Time: 12:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<Ejercicio> ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
%>
<html>
<head>
    <title>Día</title>
</head>
<body>
<table>
    <%
        for( Ejercicio ejercicio : ejercicios ){
    %>
        <tr><%=ejercicio.getNombre()%></tr>
        <tr><%=ejercicio.getUrlVideo()%></tr>
        <tr><%=ejercicio.getUrlVideo()%></tr>
        <tr><%=ejercicio.getUrlVideo()%></tr>
        <tr><%=ejercicio.getUrlVideo()%></tr>
        <tr><%=ejercicio.getUrlVideo()%></tr>
    <%
        }
    %>
</table>
</body>
</html>
