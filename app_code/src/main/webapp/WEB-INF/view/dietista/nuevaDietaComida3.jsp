<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.Comida" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% List<Comida> comidas = (List<Comida>) request.getAttribute("comidas"); %>
<html>
<head>
    <title>Nueva Dieta - 3 Ingestas</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
        }
        .table {
            width: 100%;
            margin-bottom: 20px;
            border: 1px solid #dee2e6;
        }
        .table th, .table td {
            border: 1px solid #dee2e6;
            padding: .75rem;
            text-align: center;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }
    </style>
</head>
<body>
<div>
    <h2 class="mb-4">Nueva Dieta - 3 Ingestas</h2>
    <form:form method="post" modelAttribute="DietaCrearForm" action="/dietista/generar">
        <table class="table table-bordered">
            <thead>
            <tr>
                <th></th>
                <th>Día 1</th>
                <th>Día 2</th>
                <th>Día 3</th>
                <th>Día 4</th>
                <th>Día 5</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th>Ingesta 1</th>
                <td>
                    <form:select path="ingesta1Dia1">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta1Dia2">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta1Dia3">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta1Dia4">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta1Dia5">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <th>Ingesta 2</th>
                <td>
                    <form:select path="ingesta2Dia1">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta2Dia2">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta2Dia3">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta2Dia4">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta2Dia5">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
            </tr>
            <tr>
                <th>Ingesta 3</th>
                <td>
                    <form:select path="ingesta3Dia1">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta3Dia2">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta3Dia3">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta3Dia4">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
                <td>
                    <form:select path="ingesta3Dia5">
                        <form:option value="">Selecciona</form:option>
                        <form:options items="${comidas}" itemLabel="nombre" itemValue="id"/>
                    </form:select>
                </td>
            </tr>
            </tbody>
        </table>
        <button type="submit" class="btn btn-primary">Generar</button>
    </form:form>
</div>
</body>
</html>
