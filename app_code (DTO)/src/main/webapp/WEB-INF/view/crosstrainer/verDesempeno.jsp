<%@ page import="java.util.List" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="es.uma.proyectotaw.service.RutinaService" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="es.uma.proyectotaw.dto.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ClienteDTO cliente = (ClienteDTO) request.getAttribute("cliente");
    List<ClienteRutinaDTO> historialRutinasCliente = (List<ClienteRutinaDTO>) request.getAttribute("historialRutinasCliente");
    List<Desempeno> desempenosCliente = (List<Desempeno>) request.getAttribute("desempenosCliente");
    List<EjercicioEntrenamientoDTO> ejercicios = (List<EjercicioEntrenamientoDTO>) request.getAttribute("ejercicios");
    RutinaService rutinaService = (RutinaService) request.getAttribute("rutinaService");
%>
<!DOCTYPE html><!--Hecho por Pablo Alonso Burgos-->
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Desempeño del Cliente</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Fuente personalizada -->
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }

        .container {
            max-width: 1100px;
            margin: auto;
            padding: 30px;
            background-color: #ffffff;
            border-radius: 15px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
        }

        h2, h3 {
            color: #333;
            font-weight: 700;
            margin-bottom: 20px;
        }

        .table {
            text-align: center;
        }

        .table-responsive {
            margin-bottom: 30px;
        }

        th, td {
            vertical-align: middle !important;
        }

        .table th {
            background-color: #4e73df;
            color: #fff;
            font-weight: 500;
        }

        .table tbody tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .table tbody tr:hover {
            background-color: #e9ecef;
        }

        .btn-custom {
            background-color: #4e73df;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .btn-custom:hover {
            background-color: #2e59d9;
        }

        .card {
            margin-bottom: 20px;
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .card-header {
            background-color: #4e73df;
            color: #fff;
            border-bottom: none;
            font-weight: 500;
            border-radius: 10px 10px 0 0;
            padding: 15px 20px;
        }

        .card-body {
            padding: 20px;
        }

        .card h5 {
            margin-bottom: 15px;
            font-weight: 500;
        }

        .grouped-header {
            border-right: 1px solid #dee2e6;
        }

        .grouped-header:last-child {
            border-right: none;
        }

        .group-title {
            font-weight: 700;
            text-align: center;
            background-color: #4e73df;
            color: #fff;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Desempeño del Cliente: <%=cliente.getUsuario().getNombre()%> <%=cliente.getUsuario().getApellidos()%>
    </h2>

    <div class="card">
        <div class="card-header">Historial de Rutinas</div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th>Nombre</th>
                        <th>Número Entrenamientos</th>
                        <th>Descripción</th>
                        <th>Fecha de Creación</th>
                        <th>Activa</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (ClienteRutinaDTO rutinacliente : historialRutinasCliente) {%>
                        <%

                        RutinaDTO rutina = rutinaService.findById(rutinacliente.getRutina());%>
                    <tr>
                        <td><%= rutina.getNombre() %>
                        </td>
                        <td><%= rutina.getEntrenamientos().size() %>
                        </td>
                        <td><%= rutina.getDescripcion() %>
                        </td>
                        <td><%= rutina.getFechaCreacion() %>
                        </td>
                        <td><%= rutinacliente.getVigente() ? "Sí" : "No" %>
                        </td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="card">
        <div class="card-header">Registro de los Últimos Ejercicios Realizados</div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr>
                        <th rowspan="2">Entrenamiento</th>
                        <th rowspan="2">Ejercicio</th>
                        <th colspan="5" class="group-title">Detalles Avanzados</th>
                        <th rowspan="2">Valoración</th>
                        <th rowspan="2">Comentario</th>
                    </tr>
                    <tr>
                        <th class="grouped-header">Series</th>
                        <th class="grouped-header">Repeticiones</th>
                        <th class="grouped-header">Peso</th>
                        <th class="grouped-header">Tiempo</th>
                        <th>Distancia</th>
                    </tr>
                    </thead>
                    <tbody>
                    <% for (Desempeno desempeno : desempenosCliente) { %>
                    <% for (EjercicioEntrenamientoDTO ejercicio : ejercicios) { %>
                    <% if (desempeno.getId() == ejercicio.getDesempeno()/*.getId()*/) { %>
                    <tr>
                        <td><%= ejercicio.getEntrenamiento().getNombre() %>
                        </td>
                        <td><%= ejercicio.getEjercicio().getNombre() %>
                        </td>
                        <td><%= ejercicio.getSeries() != null ? ejercicio.getSeries() : "" %>
                        </td>
                        <td><%= ejercicio.getRepeticiones() != null ? ejercicio.getRepeticiones() : "" %>
                        </td>
                        <td><%= ejercicio.getPeso() != null ? ejercicio.getPeso() + " kg" : "" %>
                        </td>
                        <td><%= ejercicio.getTiempo() != null ? ejercicio.getTiempo() + " minutos" : ""%>
                        </td>
                        <td><%= ejercicio.getDistancia() != null ? ejercicio.getDistancia() + " metros" : ""%>
                        </td>
                        <td><%= desempeno.getValoracion() + "/5" %>
                        </td>
                        <td><%= desempeno.getComentarios() %>
                        </td>
                    </tr>
                    <% } %>
                    <% } %>
                    <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <div class="text-center mt-4">
        <a href="/seguimientoRutinas" class="btn btn-custom">Volver</a>
    </div>
</div>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
