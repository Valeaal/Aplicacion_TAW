<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Ejercicio> Ejercicios = (List<Ejercicio>) request.getAttribute("ejercicios");
    List<TipoEjercicio> TiposEjercicio = (List<TipoEjercicio>) request.getAttribute("tiposEjercicio");
    List<GrupoMuscular> GruposMusculares = (List<GrupoMuscular>) request.getAttribute("gruposMusculares");
%>

<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de ejercicios</title>
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
                    <form action="/admin/ejercicios/filtrar">
                        <div class="mb-3 d-flex">
                            <input type="text" class="form-control form-control-lg me-2" name="inputNombre"
                                   placeholder="Nombre">
                        </div>
                        <div class="mb-3 d-flex">
                            <!-- Menú desplegable 1 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringGrupo">
                                    <option selected>Selecciona Grupo Muscular</option>
                                    <%
                                        for (int i = 0; i < GruposMusculares.size(); i++) {
                                    %>
                                    <option><%= GruposMusculares.get(i).getGrupo() %></option>
                                    <%
                                        }
                                    %>
                                </select>
                            </div>
                            <!-- Menú desplegable 2 -->
                            <div class="flex-grow-1 me-3">
                                <select class="form-select" name="StringTipo">
                                    <option selected>Selecciona Tipo de Ejercicio</option>
                                    <%
                                        for (int i = 0; i < TiposEjercicio.size(); i++) {
                                    %>
                                    <option><%= TiposEjercicio.get(i).getTipo() %></option>
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

    <form action="/admin/ejercicios/seleccionar">
        <div class="d-flex justify-content-between align-items-start mt-5">

            <!-- Tabla -->

            <div class="flex-grow-1 me-3">

                <table class="table">
                    <thead>
                    <tr>
                        <th scope="col"></th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripción</th>
                        <th scope="col">Grupo muscular</th>
                        <th scope="col">Tipo de ejercicio</th>
                    </tr>
                    </thead>
                    <tbody>
                    <!-- Aquí se pueden agregar filas dinámicamente con datos -->
                    <% for (Ejercicio ejr : Ejercicios) { %>
                    <tr>
                        <td><input type="radio" name="eSeleccionado" value= "<%= ejr.getId() %>" ></td>
                        <td><%= ejr.getNombre()%></td>
                        <td><%= ejr.getDescripcion()%></td>
                        <td><%= ejr.getGrupoMuscular().getGrupo()%></td>
                        <td><%= ejr.getTipo().getTipo()%></td>
                    </tr>
                    <% } %>
                    </tbody>
                </table>
            </div>

            <!-- Botones -->
            <div class="col-2 mt-auto">
                <div class="d-grid">
                    <button type="submit" class="btn btn-success mb-2" name="Boton" value="Añadir">Añadir ejercicio</button>
                    <button type="submit" class="btn btn-warning mb-2" name="Boton" value="Modificar">Modificar ejercicio</button>
                    <button type="submit" class="btn btn-danger" name="Boton" value="Eliminar">Eliminar ejercicio</button>
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
