<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: pablo
  Date: 07/06/2024
  Time: 18:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    List<Entrenamiento> entrenamientos = (List<Entrenamiento>) request.getAttribute("entrenamientos");
%>
<html> <!--Hecho por Pablo Alonso Burgos-->
<head>
    <title>Title</title>
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

        .main-container {
            display: flex;
            justify-content: space-between;
            width: 100%;
            max-width: 1200px;
            padding: 20px;
        }

        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 45%;
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

        table {
            width: 100%;
            border-collapse: collapse;
            height: fit-content;
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #e0e0e0;
        }

        th {
            background-color: #f9f9f9;
            font-weight: 600;
        }

        tr:nth-child(even) {
            background-color: #f9f9f9;
        }

        .container {
            background-color: #fff;
            padding: 40px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 400px;
        }

        .button-container {
            text-align: center;
        }

        button, a.btn {
            display: inline-block;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-align: center;
            text-decoration: none;
            box-sizing: border-box;
        }

        .add-button {
            display: inline-block;
            background-color: #007bff;
            color: #fff;
            border: none;
            padding: 10px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 20px;
        }

        .add-button:hover {
            background-color: #0056b3;
        }

        .btn-borrar {
            background-color: #dc3545;
        }

        .btn-borrar:hover {
            background-color: darkred;
        }
    </style>
</head>
<body>
<div class="container">
    <form method="post" action="/guardarEntrenamientoNuevoDeRutina">
        <input type="hidden" name="idRutina" value="<%=idRutina%>"/>
        <h2>Añadir entrenamiento a la rutina</h2>
        <table>
            <tbody>
            <tr>
                <td>
                    <h5>Seleccione un día</h5>
                    <select name="diaSemana">
                        <option value="1">Lunes</option>
                        <option value="2">Martes</option>
                        <option value="3">Miércoles</option>
                        <option value="4">Jueves</option>
                        <option value="5">Viernes</option>
                        <option value="6">Sábado</option>
                        <option value="7">Domingo</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>
                    <h5>Seleccione un entrenamiento</h5>
                    <select name="idEntrenamiento">
                        <% for (Entrenamiento entrenamiento : entrenamientos) { %>
                        <option value="<%= entrenamiento.getId() %>"><%= entrenamiento.getNombre() %>
                        </option>
                        <% } %>
                    </select>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="2">
                    <div class="button-container">
                        <button type="submit" class="add-button">Añadir</button>
                        <a class="btn btn-borrar" href="crud/editar?idRutina=<%=idRutina%>">Cancelar</a>
                    </div>
                </td>
            </tr>

            </tfoot>

        </table>

    </form>


</div>
</body>
</html>
