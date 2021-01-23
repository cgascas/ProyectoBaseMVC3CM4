<%-- 
    Document   : login
    Created on : 7 dic 2020, 14:15:22
    Author     : werog
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Login - Brand</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
    </head>

    <body class="bg-gradient-primary">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-md-9 col-lg-12 col-xl-10">
                    <div class="card shadow-lg o-hidden border-0 my-5">
                        <div class="card-body p-0">
                            <div class="row">
                                <div class="col-lg-6 offset-lg-3 offset-xl-3">
                                    <div class="p-5">
                                        <div class="text-center">
                                            <h4 class="text-dark mb-4">Bienvenido</h4>
                                        </div>

                                        <form class="user" id="entrar" action="Login?accion=entrar" method="post">
                                            <div class="form-group"><input class="form-control form-control-user" type="text"  placeholder="Ingresa nombre de usuario" name="username" required=""></div>
                                            <div class="form-group"><input class="form-control form-control-user" type="password"  placeholder="Contraseña" name="password" required=""></div><button class="btn btn-primary btn-block text-white btn-user" type="submit">Entrar</button>
                                            <hr>
                                            <hr>
                                        </form>
                                        <div class="text-center"><a class="small" href="Login?accion=registrar">Crear cuenta</a></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>
    </body>

</html>
