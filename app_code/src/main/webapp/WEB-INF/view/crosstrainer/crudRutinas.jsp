<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Ejercicio> listaEjerciciosCompleta = (List<Ejercicio>) request.getAttribute("listaEjerciciosCompleta");
%>

<html>

<head>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <title>TAW G4</title>
</head>

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">
    <h1>Bienvenido al crud</h1> <br>
</div>
<!-- Columna que contiene tres columnas internas -->
<div class="container-sm">
    <div class="card">
        <div class="card-body">
            <input type="text" class="form-control form-control-sm" name="rutinas" value=""
                   placeholder="Buscar Rutina"/>

            <br>
            <div class="col-md-6">
                <!-- Columna 1 -->
                <div class="row">
                    <div class="col-md-4">
                        <select name="Nº ENTRENAMIENTOS">
                            <option>NºENTRENAMIENTOS</option>
                            <option>1</option>
                            <option>2</option>
                            <option>3</option>
                            <option>4</option>
                            <option>5</option>
                            <option>6</option>
                            <option>7</option>
                        </select>
                    </div>
                    <!-- Columna 2 -->
                    <div class="col-md-2">
                        <select name="TIPO">
                            <option>TIPO</option>
                            <option>FF</option>
                            <option>DS</option>
                            <option>RLKF</option>
                            <option>FREE</option>
                        </select>
                    </div>
                    <!-- Columna 3 -->
                    <div class="col-md-1">
                        <select name="OBJETIVO">
                            <option>OBJETIVO</option>
                            <option>A</option>
                            <option>B</option>
                            <option>C</option>
                            <option>4</option>
                            <option>5</option>
                        </select>
                    </div>
                </div>
                <br>

                <div class="row">
                    <table border="1">
                        <tr class="text-center">
                            <th>Nombre</th>
                            <th>Nº ENTRENAMIENTOS</th>
                            <th>TIPO</th>
                            <th>OBJETIVO</th>
                        </tr>
                        <tr class="text-center">
                            <td>a</td>
                            <td>b</td>
                            <td>c</td>
                            <td>d</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="container-sm">
        <div class="card">
            <div class="card-body">
                <button>Crear Rutina</button>
                <button>Actualizar Rutina</button>
                <button>Eliminar Rutina</button>
            </div>
        </div>
    </div>
</div>


<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS (opcional, solo si queremos funcionalidades como los dropdowns del navbar) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>
