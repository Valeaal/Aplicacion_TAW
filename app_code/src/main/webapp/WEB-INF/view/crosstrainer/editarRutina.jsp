<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="es.uma.proyectotaw.entity.Entrenamiento" %>
<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page import="es.uma.proyectotaw.entity.EntrenamientoRutina" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Rutina rutina = (Rutina) request.getAttribute("rutina");
    List<EntrenamientoRutina> entrenamientosdeRutina = (List<EntrenamientoRutina>) request.getAttribute("entrenamientosdeRutina");
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
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            padding: 10px;
            box-sizing: border-box;
        }

        .main-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 100%;
            max-width: 1200px;
            padding: 20px;
        }

        .container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.2);
            text-align: center;
            width: 100%;
            max-width: 600px;
            margin-bottom: 20px;
            box-sizing: border-box;
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

        input[type="text"], select {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
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

        button:hover, a.btn:hover {
            background-color: #0056b3;
        }

        button {
            background-color: #007bff;
            width: auto;
        }

        .btn-add {
            background-color: #17a2b8;
            width: auto;
        }

        select {
            white-space: nowrap;
            overflow: hidden;
            text-overflow: ellipsis;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
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

        .btn-ok {
            background-color: #28a745;
        }

        .btn-ok:hover {
            background-color: #218838;
        }

        .btn-borrar {
            background-color: #dc3545;
        }

        .btn-borrar:hover {
            background-color: #c82333;
        }

        .btn-add:hover {
            background-color: #138496;
        }

        @media (min-width: 768px) {
            .main-container {
                flex-direction: row;
                justify-content: space-between;
            }

            .container {
                width: 45%;
                margin-bottom: 0;
            }
        }
    </style>
</head>
<body>
<div class="main-container">
    <div class="container">
        <h2>Datos de la rutina:</h2>
        <form action="/guardarDatosRutina" method="post">
            <input type="hidden" name="idRutina" value="<%= rutina.getId() %>">
            <h5>Nombre</h5>
            <input type="text" name="nombreRutina" value="<%= rutina.getNombre() %>">
            <h5>Descripción:</h5>
            <input type="text" name="descripcionRutina" value="<%= rutina.getDescripcion() %>">
            <button type="submit">Guardar</button>
        </form>
    </div>

    <div class="container">
        <h2>Entrenamientos de la rutina:</h2>
        <h4>¿Desea modificar algún entrenamiento de esta rutina?</h4>
        <table>
            <tr>
                <th>Día</th>
                <th>Entrenamiento</th>
                <th></th>
                <th></th>
            </tr>
            <% for (EntrenamientoRutina entrenamientoRutina : entrenamientosdeRutina) { %>
            <form action="/editarEntrenamientosdeRutina" method="post">
                <tr>
                    <td>
                        <select name="diaSemana" id="diaSemana">
                            <option value="1" <%= entrenamientoRutina.getDiaSemana() == 1 ? "selected" : "" %>>Lunes
                            </option>
                            <option value="2" <%= entrenamientoRutina.getDiaSemana() == 2 ? "selected" : "" %>>Martes
                            </option>
                            <option value="3" <%= entrenamientoRutina.getDiaSemana() == 3 ? "selected" : "" %>>
                                Miércoles
                            </option>
                            <option value="4" <%= entrenamientoRutina.getDiaSemana() == 4 ? "selected" : "" %>>Jueves
                            </option>
                            <option value="5" <%= entrenamientoRutina.getDiaSemana() == 5 ? "selected" : "" %>>Viernes
                            </option>
                            <option value="6" <%= entrenamientoRutina.getDiaSemana() == 6 ? "selected" : "" %>>Sábado
                            </option>
                            <option value="7" <%= entrenamientoRutina.getDiaSemana() == 7 ? "selected" : "" %>>Domingo
                            </option>
                        </select>
                    </td>
                    <td>
                        <select name="idEntrenamiento">
                            <% for (Entrenamiento entrenamiento : entrenamientos) { %>
                            <option value="<%= entrenamiento.getId() %>"
                                    <%= entrenamientoRutina.getEntrenamiento().getId() == entrenamiento.getId() ? "selected" : "" %>>
                                <%= entrenamiento.getNombre() %>
                            </option>
                            <% } %>
                        </select>
                    </td>
                    <td>
                        <input type="hidden" name="idEntrenamientoRutina" value="<%= entrenamientoRutina.getId() %>">
                        <button type="submit" class="btn btn-ok">OK</button>
                    </td>
            </form>
            <td>
                <a class="btn btn-borrar" href="/borrarEntrenamientosdeRutina?id=<%= entrenamientoRutina.getId() %>">Borrar</a>
            </td>
            </tr>
            <% } %>

        </table>
        <a class="btn btn-add" href="/addEntrenamientosdeRutina?id=<%= rutina.getId() %>">Añadir entrenamiento</a>
    </div>
</div>
</body>
</html>
