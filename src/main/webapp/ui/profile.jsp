<%-- 
    Document   : profile
    Created on : 7 dic 2020, 14:17:22
    Author     : werog
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    String ServerPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + "/img/";
%>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Profile - Brand</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
    </head>

    <body id="page-top">



        <div id="wrapper">
            <nav class="navbar navbar-dark align-items-start sidebar sidebar-dark accordion bg-gradient-primary p-0">
                <div class="container-fluid d-flex flex-column p-0">
                    <a class="navbar-brand d-flex justify-content-center align-items-center sidebar-brand m-0" href="#">
                        <div class="sidebar-brand-icon rotate-n-15"></div>
                        <div class="sidebar-brand-text mx-3"><img src="assets/img/logoESCOMBlanco.png" style="width: 72px;"></div>
                    </a>
                    <hr class="sidebar-divider my-0">
                    <ul class="nav navbar-nav text-light" id="accordionSidebar">
                        <li class="nav-item"><a class="nav-link" href="CategoriaServlet?accion=listaDeCategorias"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                        <li class="nav-item"></li>
                    </ul>
                    <div class="text-center d-none d-md-inline"></div>
                </div>
            </nav>
            <div class="d-flex flex-column" id="content-wrapper">

                <div id="content">
                    <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                        <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                            <ul class="nav navbar-nav flex-nowrap ml-auto">
                                <li class="nav-item dropdown no-arrow">
                                    <div class="nav-item dropdown no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><span class="d-none d-lg-inline mr-2 text-gray-600 small"><c:out value="${usuario.entidad.nombreUsuario}" /></span><svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" viewBox="0 0 16 16" fill="currentColor" class="bi bi-list">
                                            <path fill-rule="evenodd" d="M2.5 11.5A.5.5 0 0 1 3 11h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 7h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4A.5.5 0 0 1 3 3h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"></path>
                                            </svg></a>
                                        <div class="dropdown-menu shadow dropdown-menu-right animated--grow-in"><a class="dropdown-item" href="Login?accion=perfil&email=<c:out value="${usuario.entidad.email}" />"><i class="fas fa-user fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Perfil</a>
                                            <div class="dropdown-divider"></div><a class="dropdown-item" href="Login?accion=salir"><i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>&nbsp;Salir</a></div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <div class="d-none" id="alerta">
                        <div class="alert alert-success" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><span><strong>El Producto se ha ... exitosamente</strong>.</span></div>
                        <div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><span><strong>Erro al ... producto.</strong></span></div>
                    </div>

                    <div class="container-fluid">
                        <h3 class="text-dark mb-4">Perfil</h3>
                        <div class="row mb-3">
                            <div class="col-lg-4">
                                <div class="card mb-3">
                                    <div class="card-body text-center shadow"><img class="rounded-circle mb-3 mt-4" src="assets/img/nophoto.png" width="160" height="160">

                                        <div class="custom-file">
                                            <!--<input type="file" name="foto" class="custom-file-input" id="customFile" enctype = "multipart/form-data">-->
                                            <form id="guardarFoto" action="" method="post" enctype='multipart/form-data'> 
                                                <input type="file" name="foto" accept="image/x-png,image/gif,image/jpeg" />
                                                <div class="form-group"><button id="submitboton" class="btn btn-primary btn-sm" type="submit">Guardar Foto</button></div>
                                            </form>
                                        </div>


                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-8">
                                <div class="row">
                                    <div class="col">
                                        <div class="card shadow mb-3">
                                            <div class="card-header py-3">
                                                <p class="text-primary m-0 font-weight-bold">Configuracion de usuario</p>
                                            </div>
                                            <form id="guardarEdit" action="Login?accion=update" method="post">    
                                                <div class="card-body">
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div class="form-group"><label for="nombreUsuario"><strong>Nombre de usuario</strong></label><input class="form-control" type="text" value='<c:out value="${usuario.entidad.nombreUsuario}" />' placeholder='Nombre de usuario' name="nombreUsuario" required=""></div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group"><label for="email"><strong>Correo Electronico</strong></label><input class="form-control" type="email" value='<c:out value="${usuario.entidad.email}" />' placeholder='Email' name="email" required="" readonly></div>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div class="form-group"><label for="paterno"><strong>Apellido Paterno</strong></label><input class="form-control" type="text" value="<c:out value="${usuario.entidad.paterno}" />" name="paterno" placeholder='Apellido Paterno' required=""></div>
                                                        </div>
                                                        <div class="col">
                                                            <div class="form-group"><label for="materno"><strong>Apellido Materno</strong></label><input class="form-control" type="text" value="<c:out value="${usuario.entidad.materno}" />" name="materno" placeholder='Apellido Materno' required=""></div>
                                                        </div>
                                                    </div>
                                                    <div class="form-row">
                                                        <div class="col">
                                                            <div class="form-group"><label for="nombre"><strong>Nombre</strong></label><input class="form-control" type="text" value="<c:out value="${usuario.entidad.nombre}" />" name="nombre" placeholder='Nombre' required=""></div>
                                                        </div>
                                                        <div class="col"></div>
                                                    </div>
                                                    <div class="form-group"><button id="submitboton" class="btn btn-primary btn-sm" type="submit">Guardar</button></div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <footer class="bg-white sticky-footer">
                        <div class="container my-auto">
                            <div class="text-center my-auto copyright"><span>C&Eacute;SAR EDUARDO GASCA S&Aacute;NCHEZ 4CM3<br>CARLOS ARMANDO ROJAS DE LA ROSA <br>
                                EDGAR ADRI&Aacute;N NAVA ROMO <br>4CM3
                            </span></div>
                        </div>
                    </footer>
                </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a></div>
            <script src="assets/js/jquery.min.js"></script>
            <script src="assets/bootstrap/js/bootstrap.min.js"></script>
            <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
            <script src="assets/js/theme.js"></script>
            <script type="text/javascript">
            </script>

    </body>
    <script>
        $(window).load(function () {
            var img = "<c:out value="${usuario.entidad.path}"/>";
            img.replace('\',' / ');
            alert(img);
        });

    </script>
</html>