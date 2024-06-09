<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Ejercicio" %>
<%@ page import="es.uma.proyectotaw.entity.Desempeno" %>
<%@ page import="es.uma.proyectotaw.entity.Cliente" %><%--
  Created by IntelliJ IDEA.
  User: albadelatorres
  Date: 2/5/24
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Ejercicio ejercicio = (Ejercicio) request.getAttribute("ejercicio");
    Cliente cliente = (Cliente) request.getAttribute("cliente");
    Integer entrenamientoId = (Integer) request.getAttribute("entrenamientoId");
%>
<html>
<head>
    <title>Desempeño del ejercicio</title>
</head>
<body>
<jsp:include page="../header-footer/navbar.jsp"></jsp:include>
Está valorando el ejercicio <%=ejercicio.getNombre()%>: <br>
<form:form modelAttribute="desempeno" method="post" action="/guardarDesempeno">
    <form:hidden path="ejercicio" value="<%=ejercicio.getId()%>"/>
    <form:hidden path="entrenamiento" value="<%=entrenamientoId%>"/>
    Valoración: <form:input path="valoracion"/>
    Peso realizado: <form:input path="pesoRealizado"/>
    <form:hidden path="cliente" value="<%=cliente.getId()%>"/>
    Comentarios: <form:input path="comentarios"/>
    <form:button>Enviar</form:button>
</form:form>

</body>
</html>
