<%-- 
    Document   : index
    Created on : 7 dic 2020, 14:14:56
    Author     : werog
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
        <title>Dashboard - Brand</title>
        <link rel="stylesheet" href="assets/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i">
        <link rel="stylesheet" href="assets/fonts/fontawesome-all.min.css">
        <link rel="stylesheet" href="assets/fonts/font-awesome.min.css">
        <link rel="stylesheet" href="assets/fonts/ionicons.min.css">
        <link rel="stylesheet" href="assets/fonts/fontawesome5-overrides.min.css">
        <link href="assets/css/Chart.css" rel="stylesheet" type="text/css"/>

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
                        <li class="nav-item"><a class="nav-link active" href="CategoriaServlet?accion=listaDeCategorias"><i class="fas fa-tachometer-alt"></i><span>Dashboard</span></a></li>
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
                        <div class="alert alert-success" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><span><strong>La categoria se ha ... exitosamente</strong>.</span></div>
                        <div class="alert alert-danger" role="alert"><button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button><span><strong>Error al ... categoria.</strong></span></div>
                    </div>
                    <div class="container-fluid">
                        <div class="d-sm-flex justify-content-between align-items-center mb-4">
                            <h3 class="text-dark mb-0">Dashboard</h3><a class="btn btn-primary btn-sm d-none d-sm-inline-block" role="button" href="CategoriaServlet?accion=generarReporte"><i class="fas fa-download fa-sm text-white-50"></i>&nbsp;Generar Reporte</a></div>
                        <div class="row">
                            <div class="col-lg-7 col-xl-8">
                                <div class="card shadow mb-4">
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <h6 class="text-primary font-weight-bold m-0">Categorías</h6>
                                        <div class="dropdown no-arrow"><button class="btn btn-link btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button"><i class="fas fa-ellipsis-v text-gray-400"></i></button>
                                            <div class="dropdown-menu text-center shadow dropdown-menu-right animated--fade-in">
                                                <p class="text-center dropdown-header">Acciones:</p><a class="dropdown-item" id="crearCategoria">Crear categoría</a>
                                                <div class="dropdown-divider"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card-body">
                                        <div class="table-responsive table mt-2" id="dataTable-1" role="grid" aria-describedby="dataTable_info">
                                            <form action="CategoriaServlet?accion=nuevo" method="post">
                                                <table class="table my-0" id="dataTable">
                                                    <thead>
                                                        <tr>
                                                            <th style="text-align: left;">Nombre</th>
                                                            <th>Descripción</th>
                                                            <th style="width: 200px;text-align: center;">Accion</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <c:forEach 
                                                            var="dto"
                                                            items="${listaDeCategorias}">
                                                            <tr>
                                                                <!--<td>--> 
                                                                <%--<c:out value="${dto.entidad.idCategoria}" />--%>
                                                                <!--</td>-->
                                                                <td id="nombreCategoria<c:out value="${dto.entidad.idCategoria}" />">
                                                                    <c:out value="${dto.entidad.nombreCategoria}"/>
                                                                </td>
                                                                <td id="descripcion<c:out value="${dto.entidad.idCategoria}" />">
                                                                    <c:out value="${dto.entidad.descripcionCategoria}"/>
                                                                </td>
                                                                <td class="text-center">
                                                                    <div class="row d-inline-flex">
                                                                        <div class="col"><button class="btn btn-primary" onclick="setInputModal(<c:out value="${dto.entidad.idCategoria}" />);" type="button" data-toggle="modal" data-target="#modal" title="Editar"><i class="fa fa-edit"></i></button></div>
                                                                        <div class="col"><button class="btn btn-danger" onclick="eliminar(<c:out value="${dto.entidad.idCategoria}" />)" type="button" style="background: rgb(223,78,78);" title="Eliminar"><i class="fa fa-trash"></i></button></div>
                                                                        <div class="col"><a class="btn btn-success" onclick="ver(<c:out value="${dto.entidad.idCategoria}" />)" role="button" title="Ver productos" href="ProductoServlet?accion=listaDeProductos&idCategoria=<c:out value="${dto.entidad.idCategoria}" />"><i class="fa fa-eye"></i></a></div>
                                                                    </div>
                                                                </td>

                                                            </tr>
                                                        </c:forEach>

                                                        <tr id="nvaCategoria" style="display:none;">

                                                            <td><input name="nombre" type="text" style="width: 134px;" autofocus="" required="required"></td>
                                                            <td><input name="descripcion" type="text" autofocus="" required="required"></td>
                                                            <td class="text-center">
                                                                <div class="row d-inline-flex">
                                                                    <div class="col"><button class="btn btn-success" type="submit" title="Guardar"><i class="fa fa-save"></i></button></div>
                                                                    <div class="col"><button id="cancelarNvaCategoria"class="btn btn-danger" type="button" style="background: rgb(223,78,78);" title="Cancelar"><i class="icon ion-android-cancel"></i></button></div>
                                                                </div>
                                                            </td>

                                                        </tr> 

                                                    </tbody>
                                                    <tfoot>
                                                        <tr></tr>
                                                    </tfoot>
                                                </table>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-5 col-xl-4">
                                <div class="card shadow mb-4">
                                    <div class="card-header d-flex justify-content-between align-items-center">
                                        <h6 class="text-primary font-weight-bold m-0">Gráfica</h6>
                                    </div>
                                    <div id="canvas-holder" style="width:100%" class="card-body">
                                        <canvas id="pie-chart" width="800" height="450"></canvas>

                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <footer class="bg-white sticky-footer">
                    <div class="container my-auto">
                        <div class="text-center my-auto copyright"><span>CESAR EDUARDO GASCA SANCHEZ 4CM3</span></div>
                    </div>
                </footer>
            </div>
            <div class="modal" role="dialog" tabindex="-1" id="modal">
                <div class="modal-dialog modal-dialog-centered" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title">Editar categor&iacute;a</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                        <form id="guardarEdit" action="CategoriaServlet?accion=actualizar" method="post">
                            <div class="modal-body">
                                <input id="categoriaEditId" type="hidden" name="id">
                                <div class="form-group">Nombre: <input id="categoriaEditNombre" class="form-control" type="text" name="nombre" placeholder="Nombre" required="true"></div>
                                <div class="form-group">Descripci&oacute;n: <input id="categoriaEditDescripcion" class="form-control" type="text" name="descripcion" placeholder="Descripcion" required="required"></div>

                            </div>
                            <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button  class="btn btn-primary" type="submit">Guardar</button></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery.min.js"></script>
        <script src="assets/bootstrap/js/bootstrap.min.js"></script>
        <script src="assets/js/Chart.js" type="text/javascript"></script>
        <script src="assets/js/utils.js" type="text/javascript"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.4.1/jquery.easing.js"></script>
        <script src="assets/js/theme.js"></script>



        <script>
                                                                            function rand(min, max) {
                                                                                return min + Math.random() * (max - min);
                                                                            }

                                                                            function get_random_color() {
                                                                                var r = Math.floor(Math.random() * 255);
                                                                                var g = Math.floor(Math.random() * 255);
                                                                                var b = Math.floor(Math.random() * 255);
                                                                                return 'rgb(' + r + ',' + g + ',' + b + ')';
                                                                            }

                                                                            var poolColors = function(a) {
                                                                                var pool = [];
                                                                                for (i = 0; i < a; i++)
                                                                                    pool.push(get_random_color());
                                                                                
                                                                                return pool;
                                                                            }
                                                                            var chartdata = {
                                                                                    labels: [<c:forEach var="dto" items="${listaDeCategoriasCount}" varStatus="loop">
                                                                                                "<c:out value="${dto.nombreCategoria}"/>"
                                                                                                <c:if test="${!loop.last}">,</c:if>
                                                                                            </c:forEach>],
                                                                                    datasets: [{
                                                                                            backgroundColor: [],
                                                                                             data: [<c:forEach var="dto" items="${listaDeCategoriasCount}" varStatus="loop">
                                                                                                        "<c:out value="${dto.count}"/>"
                                                                                                        <c:if test="${!loop.last}">,</c:if>
                                                                                                    </c:forEach>]
                                                                                                }]
                                                                                    };
                                                                                    chartdata.datasets[0].backgroundColor = poolColors(chartdata.labels.length);
                                                                                    console.log(chartdata.datasets[0].backgroundColor);
                                                                            var grafica = new Chart(document.getElementById("pie-chart"), {
                                                                                    type: 'doughnut',
                                                                                    data: chartdata,
                                                                                    options: {
                                                                                    title: {
                                                                                    display: true,
                                                                                            text: 'Cantidad de productos en categor\u00EDa'
                                                                                    }
                                                                                    }
                                                                            });
                                                                            //alert( grafica.data.datasets[0].backgroundColor);
                                                                            
                                                                            //alert( grafica.data.datasets[0].backgroundColor);
        </script>
    </body>


    <script type="text/javascript">
        $("#crearCategoria").click(function () {
        $("#nvaCategoria").show();
        });
        $("#cancelarNvaCategoria").click(function () {
        $("#nvaCategoria").hide();
        });
        function setInputModal(idCategoria) {

        var nombre = $("#nombreCategoria" + idCategoria).text();
        nombre = nombre.trim();
        var descripcion = $("#descripcion" + idCategoria).text();
        descripcion = descripcion.trim();
        $("#categoriaEditId").val(idCategoria);
        $("#categoriaEditNombre").val(nombre);
        $("#categoriaEditDescripcion").val(descripcion);
        }

        function eliminar(idCategoria) {
        location.href = "CategoriaServlet?accion=eliminar&id=" + idCategoria;
        }





    </script>



</html>
