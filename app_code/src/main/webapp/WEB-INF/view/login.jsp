<%--
  Created by IntelliJ IDEA.
  User: alvaro
  Date: 25/4/24
  Time: 18:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Enlace a Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="d-flex justify-content-center align-items-center vh-100 bg-light">
<div class="container">
    <div class="row justify-content-center">
        <div class="col-10 col-md-6 col-lg-4">
            <div class="card mt-4">
                <div class="card-body text-center">
                    <h2 class="card-title">Bienvenido a tu centro deportivo</h2>
                </div>
            </div>
            <div class="card">
                <div class="card-body">
                    <h1 class="text-center mb-4">Inicia sesión</h1>
                    <form method="post" action="/logear" >
                        <div class="mb-3">
                            <label for="username" class="form-label">Email</label>
                            <input type="text" class="form-control" id="username" name="email" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">Contraseña</label>
                            <input type="password" class="form-control" id="password" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary btn-block">Entrar</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Enlace a Bootstrap JS (opcional, solo si necesitas funcionalidades JS de Bootstrap) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
