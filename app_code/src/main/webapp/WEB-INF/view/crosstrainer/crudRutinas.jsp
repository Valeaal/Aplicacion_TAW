<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>
<%@ page import="es.uma.proyectotaw.entity.Rutina" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Rutina> rutinas = (List<Rutina>) request.getAttribute("rutinas");
%>

<style>
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background-color: #f0f2f5;
        margin: 0;
        padding: 0;
    }

    .container {
        max-width: 1200px;
        margin: 40px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding-bottom: 20px;
        border-bottom: 1px solid #e0e0e0;
        margin-bottom: 20px;
    }

    .header h1 {
        margin: 0;
        font-size: 26px;
        color: #333;
    }

    .new-routine-btn {
        padding: 12px 25px;
        font-size: 16px;
        color: #ffffff;
        background-color: #28a745;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .new-routine-btn:hover {
        background-color: #218838;
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

    .btn {
        padding: 8px 15px;
        font-size: 14px;
        color: #ffffff;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        text-decoration: none;
        transition: background-color 0.3s;
    }

    .btn-modify {
        background-color: #007bff;
    }

    .btn-modify:hover {
        background-color: #0056b3;
    }

    .btn-delete {
        background-color: #dc3545;
    }

    .btn-delete:hover {
        background-color: #c82333;
    }
</style>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de usuarios</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid ">
    <!-- Formulario -->
    <div class="row justify-content-center mt-2">
        <div class="col-12">
            <div class="card">
                <div class="card-body">
                    <form> <!--Aqui filtras-->
                        <div class="mb-3 d-flex">
                            <input type="text" class="form-control form-control-lg me-2" name="inputRutina"
                                   placeholder="Buscar Rutina">

                        </div>
                        <div class="mb-3 d-flex">
                            <!-- Menú desplegable 1 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringEdad">
                                    <option selected>Nº ENTRENAMIENTOS</option>

                                    <option>1</option>
                                    <option>2</option>
                                    <option>3</option>
                                    <option>4</option>
                                    <option>5</option>
                                    <option>6</option>
                                    <option>7</option>

                                </select>
                            </div>
                            <!-- Menú desplegable 2 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringIngreso">
                                    <option selected>TIPO</option>
                                    <%
                                        //   for (int i = 0; i < Ingresos.size(); i++) {
                                    %>
                                    <option><% %>
                                    </option>
                                    <%
                                        //   }
                                    %>
                                </select>
                            </div>
                            <!-- Menú desplegable 3 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringRol">
                                    <option selected>OBJETIVO</option>
                                    <%
                                        // for (String rol : Roles) {
                                    %>
                                    <option><% %>
                                    </option>
                                    <%
                                        //    }
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

    <form action=""> <!-- Aqui guardamos el objeto seleccionado-->
        <div class="container">
            <div class="header">
                <h1>Rutinas</h1>
                <a href="/crossfit/numeroEntrenamientos" class="new-routine-btn">Nueva Rutina</a>
            </div>
            <table>
                <thead>
                <tr>
                    <th>Nombre</th>
                    <th>Número Entrenamientos</th>
                    <th>Descripción</th>
                    <th>Fecha de Creación</th>
                    <th>Modificar</th>
                    <th>Borrar</th>
                </tr>
                </thead>
                <tbody>
                <!-- Aquí se deben iterar las rutinas para generar las filas dinámicamente -->
                <%for (Rutina rutina : rutinas) {%>
                <tr>
                    <td><%=rutina.getNombre()%></td>
                    <td><%=rutina.getEntrenamientos().size()%></td>
                    <td><%=rutina.getDescripcion()%></td>
                    <td><%=rutina.getFechaCreacion()%></td>
                    <td>
                        <a href="/crossfit/crud/editar?idRutina=<%=rutina.getId()%>" class="btn btn-warning mb-2">Editar</a>
                    </td>
                    <td>
                        <a href="/crossfit/crud/borrar?idRutina=<%=rutina.getId()%>" class="btn btn-danger mb-2">Borrar</a>
                    </td>
                </tr>
                <%}%>


                <!-- Fin de la iteración -->
                </tbody>
            </table>
        </div>
    </form>
</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
