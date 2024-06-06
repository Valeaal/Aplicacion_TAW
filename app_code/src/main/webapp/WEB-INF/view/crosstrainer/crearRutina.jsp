<%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 19/05/2024
  Time: 12:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Tablas de Entrenamientos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
        }

        h1, h2 {
            color: #333;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        input[type="text"] {
            width: 80%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<h1>Tablas de Entrenamientos</h1>
<%
    String numEntrenamientosStr = request.getParameter("numEntrenamientos");

        int numEntrenamientos = Integer.parseInt(numEntrenamientosStr);
        for (int i = 1; i <= numEntrenamientos; i++) {
%>
<h2>Entrenamiento <%= i %>
</h2>
<table>
    <thead>
    <tr>
        <row
        <th>Tipo Ejercicio</th>
        <th>Ejercicio</th>
        <th>Repeticiones</th>
        <th>Series</th>
    </tr>
    </thead>
    <tbody>
    <% for (int j = 1; j <= 5; j++) { %>
    <tr>
        <td><input type="text" name="ejercicio<%= i %>_<%= j %>"></td>
        <td><input type="text" name="repeticiones<%= i %>_<%= j %>"></td>
        <td><input type="text" name="series<%= i %>_<%= j %>"></td>
    </tr>
    <% } %>
    </tbody>
</table>
<br>

<%
    }
%>
</body>
</html>

