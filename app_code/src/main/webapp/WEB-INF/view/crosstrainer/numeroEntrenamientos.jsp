<%@ page import="java.util.Date" %>
<%@ page import="java.time.LocalDate" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 19/05/2024
  Time: 12:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Generador de Rutinas de Gimnasio</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }
        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
        }
        h1, h2, h5 {
            color: #333;
            margin: 10px 0;
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
            color: #555;
        }
        input[type="text"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }
        select {
            width: calc(100% - 20px);
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button:hover {
            background-color: #0056b3;
        }
        button a {
            color: #fff;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Creación de rutina:</h2>
    <form action="/crossfit/crearRutina" method="post">
        <input type="hidden" value="0" name="numeroDia">
        <h5>Nombre</h5>
        <input type="text" name="nombreRutina">
        <h5>Descripción:</h5>
        <input type="text" name="descripcionRutina">
        <h5>Número de entrenamientos</h5>
        <select name="numEntrenamientos">
            <% for(int i = 1; i < 8; i++){%>
            <option><%=i%></option>
            <% }%>
        </select>
        <br>
        <button type="submit">OK</button>
    </form>
</div>
</body>
</html>
