<%@ page import="java.util.*" %>
<%@ page import="es.uma.proyectotaw.entity.Usuario" %>

<%
  List<Usuario> clientes = (List<Usuario>) request.getAttribute("clientes");
  List<Usuario> dietistas = (List<Usuario>) request.getAttribute("dietistas");
%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Clientes -> Dietistas</title>
  <!-- Enlace a Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<jsp:include page="../header-footer/navbar.jsp"></jsp:include>

<div class="container-fluid">



  <form action="/admin/clientesDietistas/asignar">

    <div class="row">

      <!-- Parte izquierda con tabla de clientes -->
      <div class="col  flex-grow-1 col-auto">

        <h1>Lista de clientes</h1>
        <p>¿A qué cliente quieres asignarle un dietista?<p>

        <table class="table">
          <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Ingreso</th>
            <th scope="col">Fecha Nacimiento</th>
          </tr>
          </thead>
          <tbody>

          <% for (Usuario usr : clientes) { %>
          <tr>
            <td><input type="radio" name="clienteSeleccionado" value="<%= usr.getId()%>"></td>
            <td><%= usr.getNombre()%>
            </td>
            <td><%= usr.getApellidos()%>
            </td>
            <td><%= usr.getPerteneceDesde()%>
            </td>
            <td><%= usr.getFechaNacimiento()%>
            </td>
          </tr>
          <% } %>

          </tbody>
        </table>
      </div>


      <!-- Parte derecha con tabla de entrenadores -->
      <div class="col flex-grow-1 col-auto">

        <h1>Lista de dietistas</h1>
        <p>¿Qué dietista vas a asignarle?<p>


        <table class="table">
          <thead>
          <tr>
            <th scope="col"></th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellidos</th>
            <th scope="col">Rol</th>
          </tr>
          </thead>
          <tbody>
          <!-- Aquí se pueden agregar filas dinámicamente con datos -->
          <% for (Usuario usr : dietistas) { %>
          <tr>
            <td><input type="radio" name="dietistaSeleccionado" value="<%= usr.getId()%>"></td>
            <td><%= usr.getNombre()%>
            </td>
            <td><%= usr.getApellidos()%>
            </td>
            <td><%= usr.getTipoUsuario().getTipo()%>
            </td>
          </tr>
          <% } %>
          </tbody>
        </table>
      </div>

    </div>

    <!-- Botón de asignar -->
    <div class="col col-auto mt-auto">
      <button type="submit" class="btn btn-success">Asignar</button>
    </div>

  </form>


</div>

<jsp:include page="../header-footer/footer.jsp"></jsp:include>

<!-- Enlace a Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
