<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="es.uma.proyectotaw.dto.RutinaDTO" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 5/5/24
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    List<RutinaDTO> rutinas = (List<RutinaDTO>) request.getAttribute("rutina");
%>
<html>
<head>
    <title>Rutinas filtradas</title>
</head>
<body>
<%
    for(RutinaDTO r: rutinas) {
%>
<p><%=r.getNombre()%></p> Desempeño:
<%
    }
%>
</body>
</html>
