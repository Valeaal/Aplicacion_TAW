<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<EjercicioEntrenamiento> EjerciciosEntrenamientos = (List<EjercicioEntrenamiento>) request.getAttribute("ejerciciosEntrenamientos");
    List<Ejercicio> Ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    List<Entrenamiento> Entrenamientos = (List<Entrenamiento>) request.getAttribute("entrenamientos");
%>


<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestionar dimensiones de ejercicios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <!-- Formulario -->
    <div class="row justify-content-center mt-2">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form action="/admin/ejerciciosEntrenamientos/filtrar">
                        <div class="mb-3 d-flex">
                            <input type="number" class="form-control form-control-lg me-2" name="inputSeries" placeholder="Número de series">
                            <input type="number" class="form-control form-control-lg me-2" name="inputRepeticiones" placeholder="Número de repeticiones">
                            <input type="number" class="form-control form-control-lg me-2" name="inputPeso" placeholder="Kilos">
                        </div>
                        <div class="mb-3 d-flex">
                            <input type="number" class="form-control form-control-lg me-2" name="inputTiempo" placeholder="Tiempo">
                            <input type="number" class="form-control form-control-lg me-2" name="inputDistancia" placeholder="Distancia">
                            <input type="number" class="form-control form-control-lg me-2" name="inputOrden" placeholder="Posición en el entreno">
                        </div>
                        <div class="mb-3 d-flex">
                            <!-- Menú desplegable 1 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringEntrenamiento">
                                    <option selected>Selecciona Entrenamiento</option>
                                    <%
                                        for (int i = 0; i < Entrenamientos.size(); i++) {
                                    %>
                                    <option><%= Entrenamientos.get(i).getNombre() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Menú desplegable 2 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringEjercicio">
                                    <option selected>Selecciona Ejercicio</option>
                                    <%
                                        for (int i = 0; i < Ejercicios.size(); i++) {
                                    %>
                                    <option><%= Ejercicios.get(i).getNombre() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Botón de búsqueda -->
                            <button type="submit" class="btn btn-primary">Buscar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Tarjeta para el texto -->
    <div class="row justify-content-center mt-3">
        <div class="col-10">
            <div class="card">
                <div class="card-body text-center">
                    <h3>Sírvase de esta página para instanciar un ejercicio</h3>
                    <h5>Es decir, para dado un ejercicio, determinar sus características concretas en una sesión de entrenamiento</h5>
                </div>
            </div>
        </div>
    </div>

    <!-- Tabla -->
    <form action="/admin/ejerciciosEntrenamientos/seleccionar">
        <div class="d-flex justify-content-between align-items-start mt-4">
            <div class="flex-grow-1 me-3">
                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Ejercicio</th>
                        <th scope="col">Entrenamiento</th>
                        <th scope="col">Series</th>
                        <th scope="col">Repeticiones</th>
                        <th scope="col">Peso</th>
                        <th scope="col">Tiempo</th>
                        <th scope="col">Distancia</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                    <% for (EjercicioEntrenamiento ej : EjerciciosEntrenamientos) { %>
                    <tr>
                        <td><input type="radio" name="eSeleccionado" value= "<%= ej.getId() %>" ></td>
                        <td><%= ej.getEjercicio().getNombre()%></td>
                        <td><%= ej.getEntrenamiento().getNombre()%></td>
                        <td><%= ej.getSeries() != null ? ej.getSeries() : "-" %></td>
                        <td><%= ej.getRepeticiones() != null ? ej.getRepeticiones() : "-" %></td>
                        <td><%= ej.getPeso() != null ? ej.getPeso() : "-" %></td>
                        <td><%= ej.getTiempo() != null ? ej.getTiempo() : "-" %></td>
                        <td><%= ej.getDistancia() != null ? ej.getDistancia() : "-" %></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- Botones -->
            <div class="col-2 mt-auto">
                <div class="d-grid">
                    <button type="submit" class="btn btn-success mb-2" name="Boton" value="Añadir">Añadir</button>
                    <button type="submit" class="btn btn-warning mb-2" name="Boton" value="Modificar">Modificar</button>
                    <button type="submit" class="btn btn-danger" name="Boton" value="Eliminar">Eliminar</button>
                </div>
            </div>


        </div>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
