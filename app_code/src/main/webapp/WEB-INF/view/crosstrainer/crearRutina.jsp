<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer idRutina = (Integer) request.getAttribute("idRutina");
    List<Entrenamiento> entrenamientos = (List<Entrenamiento>) request.getAttribute("entrenamientos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tablas de Entrenamientos</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 20px;
            color: #333;
        }

        h1 {
            color: #007bff;
            text-align: center;
            margin-bottom: 40px;
        }

        h2 {
            color: #333;
            border-bottom: 2px solid #007bff;
            padding-bottom: 10px;
            margin-top: 40px;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #007bff;
            color: #fff;
        }

        select {
            width: 100%;
            padding: 10px;
            margin: 20px 0;
            border: 1px solid #ddd;
            border-radius: 4px;
        }

        .error {
            color: red;
            font-weight: bold;
            margin-top: 20px;
        }

        .button-container {
            text-align: center;
            margin-top: 20px;
        }

        .add-button {
            padding: 10px 20px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            background-color: #28a745;
            color: #fff;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .add-button:hover {
            background-color: #218838;
        }

        .modern-button, .cancel-button {
            display: inline-block;
            padding: 12px 24px;
            font-size: 16px;
            font-weight: bold;
            border: none;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease, box-shadow 0.3s ease, transform 0.3s ease;
            cursor: pointer;
            text-decoration: none;
            text-align: center;
            margin: 0 10px;
        }

        .modern-button {
            color: #fff;
            background-color: #007bff;
        }

        .modern-button:hover {
            background-color: #0056b3;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            transform: translateY(-2px);
        }

        .modern-button:active {
            background-color: #004494;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transform: translateY(0);
        }

        .cancel-button {
            color: #fff;
            background-color: #dc3545;
        }

        .cancel-button:hover {
            background-color: #c82333;
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
            transform: translateY(-2px);
        }

        .cancel-button:active {
            background-color: #bd2130;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transform: translateY(0);
        }
    </style>
</head>
<body>
<h1>Escoja un entrenamiento para cada día</h1>
<%
    String numEntrenamientosStr = request.getParameter("numEntrenamientos");
    int numEntrenamientos = Integer.parseInt(numEntrenamientosStr);
    for (int i = 1; i <= numEntrenamientos; i++) {
%>
<h2>Día <%= i %>
</h2>
<table>
    <thead>
    <tr>
        <th>Seleccione Entrenamiento</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <form method="post" action="/crossfit/anadirEntrenamiento">
            <input type="hidden" name="idRutina" value="<%=idRutina%>" />
            <td>
                <select name="idEntrenamiento">
                    <% for (Entrenamiento entrenamiento : entrenamientos) { %>
                    <option value="<%= entrenamiento.getId() %>"><%= entrenamiento.getNombre() %>
                    </option>
                    <% } %>
                </select>
            </td>
        </form>
    </tr>

    </tbody>
    <tfoot>
    <tr>
        <td colspan="2">
            <div class="button-container">
                <button type="button" class="add-button">Añadir</button>
            </div>
        </td>

    </tr>
    </tfoot>
</table>
<%
    }
%>
<form>
    <div class="button-container">
        <button class="modern-button">Crear Rutina</button>
        <button class="cancel-button">Cancelar</button>
    </div>
</form>
</body>
</html>
