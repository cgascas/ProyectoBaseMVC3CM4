<%-- 
    Document   : tableUsuarios
    Created on : 18 dic 2020, 17:20:14
    Author     : werog
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Table - Brand</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
    </head>

    <body id="page-top">
        <div id="wrapper">
            <div class="d-flex flex-column" id="content-wrapper">
                <div id="content">
                    <nav class="navbar navbar-light navbar-expand bg-white shadow mb-4 topbar static-top">
                        <div class="container-fluid"><button class="btn btn-link d-md-none rounded-circle mr-3" id="sidebarToggleTop" type="button"><i class="fas fa-bars"></i></button>
                            <ul class="nav navbar-nav flex-nowrap ml-auto">
                                <li class="nav-item dropdown d-sm-none no-arrow"><a class="dropdown-toggle nav-link" data-toggle="dropdown" aria-expanded="false" href="#"><i class="fas fa-search"></i></a>
                                    <div class="dropdown-menu dropdown-menu-right p-3 animated--grow-in" aria-labelledby="searchDropdown">
                                        <form class="form-inline mr-auto navbar-search w-100">
                                            <div class="input-group"><input class="bg-light form-control border-0 small" type="text" placeholder="Search for ...">
                                                <div class="input-group-append"><button class="btn btn-primary py-0" type="button"><i class="fas fa-search"></i></button></div>
                                            </div>
                                        </form>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <div class="container-fluid">
                        <h3 class="text-dark mb-4">Usuarios</h3>
                        <div class="card shadow">
                            <div class="card-header py-3">
                                <p class="text-primary m-0 font-weight-bold">Lista de Usuarios</p>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                    <table class="table my-0" id="dataTable">
                                        <thead>
                                            <tr>
                                                <th>Nombre</th>
                                                <th>Usuario</th>
                                                <th>Email</th>
                                                <th class="text-center">Descargar Reporte</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach 
                                            var="dto"
                                            items="${listaDeUsuarios}">
                                            <tr>
                                                <!--<td>--> 
                                                <%--<c:out value="${dto.entidad.idCategoria}" />--%>
                                                <!--</td>-->
                                            <td id="nombreCategoria<c:out value="${dto.entidad.idUsuario}" />">
                                                <c:out value="${dto.entidad.nombre}"/>
                                            </td>
                                            <td id="descripcion<c:out value="${dto.entidad.idUsuario}" />">
                                            <c:out value="${dto.entidad.nombreUsuario}"/>
                                            </td>
                                            <td id="descripcion<c:out value="${dto.entidad.idUsuario}" />">
                                            <c:out value="${dto.entidad.email}"/>
                                            </td>
                                            <td class="text-center"><a class="btn btn-primary" href="UsuarioServlet?accion=generarReporte&idusuario=<c:out value="${dto.entidad.idUsuario}" />" type="button" title="Descargar Reporte"><i class="fa fa-download"></i></a></td>
                                            

                                            </tr>
                                        </c:forEach>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        <tr></tr>
                                        </tbody>
                                        <tfoot>
                                            <tr></tr>
                                        </tfoot>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div><a class="border rounded d-inline scroll-to-top" href="#page-top"><i class="fas fa-angle-up"></i></a>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/chart.min.js"></script>
        <script src="assets/js/bs-init.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>
    </body>

</html>
