<%-- 
    Document   : table.jsp
    Created on : 7 dic 2020, 14:16:38
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
                        <h3 class="text-dark mb-4"><c:out value="${categoria}" /></h3>
                        <div class="card shadow">
                            <div class="card-header d-flex py-3">
                                <h5 class="text-primary m-0 font-weight-bold" style="width: 1490px;">Informacion de productos</h5>
                                <div class="dropdown no-arrow" style="width: 50px;"><button class="btn btn-link btn-sm dropdown-toggle" data-toggle="dropdown" aria-expanded="false" type="button"><i class="fas fa-ellipsis-v text-gray-400"></i></button>
                                    <div class="dropdown-menu shadow dropdown-menu-right animated--fade-in">
                                        <p class="text-center dropdown-header">Acciones:</p><a class="dropdown-item" id="crearProducto">&nbsp;Crear Producto</a>
                                        <div class="dropdown-divider"></div>
                                    </div>
                                </div>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive table mt-2" id="dataTable" role="grid" aria-describedby="dataTable_info">
                                    <form  action="ProductoServlet?accion=nuevo" method="post">


                                        <table class="table my-0" id="dataTable">
                                            <thead>
                                                <tr>
                                                    <th>Nombre</th>
                                                    <th>Descripcion</th>
                                                    <th>Precio</th>
                                                    <th>Existencia</th>
                                                    <th style="text-align: center;">Acción</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach 
                                                    var="dto"
                                                    items="${listaDeProductos}">
                                                    <tr>
                                                        <!--<td>--> 
                                                        <%--<c:out value="${dto.entidad.idProducto}" />--%>
                                                        <!--</td>-->
                                                        <td id="nombreProducto<c:out value="${dto.entidad.idProducto}" />">
                                                            <c:out value="${dto.entidad.nombreProducto}"/>
                                                        </td>
                                                        <td id="descripcion<c:out value="${dto.entidad.idProducto}" />">
                                                            <c:out value="${dto.entidad.descripcionProducto}"/>
                                                        </td>
                                                        <td id="precio<c:out value="${dto.entidad.idProducto}" />">
                                                            <c:out value="${dto.entidad.precio}"/>
                                                        </td>
                                                        <td id="existencia<c:out value="${dto.entidad.idProducto}" />">
                                                            <c:out value="${dto.entidad.existencia}"/>
                                                        </td>

                                                        <td class="text-center">
                                                            <div class="row d-inline-flex">
                                                                <div class="col"><button class="btn btn-primary" onclick="setInputModal(<c:out value="${dto.entidad.idProducto}" />,<c:out value="${dto.entidad.idCategoria.idCategoria}" />);"type="button" data-toggle="modal" data-target="#modal" title="Editar"><i class="fa fa-edit"></i></button></div>
                                                                <div class="col"><button class="btn btn-primary" onclick="eliminar(<c:out value="${dto.entidad.idProducto}" />)" type="button" style="background: rgb(223,78,78);" title="Eliminar"><i class="fa fa-trash"></i></button></div>
                                                            </div>
                                                        </td>

                                                    </tr>
                                                </c:forEach>
                                                <tr id="nvoProducto"  style="display:none;">
                                            <input  type="hidden" name="idCategoria" value="<c:out value="${idCategoria}" />">
                                            <td><input name="nombre" type="text" required="required"></td>
                                            <td><input name="descripcion" type="text" required="required">&nbsp;</td>
                                            <td><input name="precio" type="number" style="width: 66px;" min="0"  required="required"></td>
                                            <td><input name="existencia" type="number" style="width: 66px;" min="0" required="required"></td>

                                            <td class="text-center">
                                                <div class="row d-inline-flex">
                                                    <div class="col"><button class="btn btn-success" type="submit" title="Guardar"><i class="fa fa-save"></i></button></div>
                                                    <div class="col"><button class="btn btn-primary" id="cancelarNvoProducto" type="button" style="background: rgb(223,78,78);" title="Cancelar"><i class="icon ion-android-cancel"></i></button></div>
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
                </div>
                <footer class="bg-white sticky-footer">
                    <div class="container my-auto">
                        <div class="text-center my-auto copyright"></div>
                    </div>
                </footer>
                <div class="modal" role="dialog" tabindex="-1" id="modal">
                    <div class="modal-dialog modal-dialog-centered" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h4 class="modal-title">Editar producto</h4><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button></div>
                            <form id="guardarEdit" action="ProductoServlet?accion=actualizar" method="post">
                                <div class="modal-body">
                                    <input id="productoEditId" type="hidden" name="productoEditId">
                                    <input id="categoriaEditId" type="hidden" name="categoriaEditId">
                                    <div class="form-group">Nombre: <input id="nombreEdit" class="form-control" type="text" name="nombre" placeholder="Nombre" required="required"></div>
                                    <div class="form-group">Descripci&oacute;n: <input id="descripcionEdit" class="form-control" type="text" name="descripcion" placeholder="Descripcion"  required="required" ></div>
                                    <div class="form-group">Precio: <input id="precioEdit" class="form-control" type="number" name="precio" placeholder="precio" min="0"  required="required"></div>
                                    <div class="form-group">Existencia: <input id="existenciaEdit" class="form-control" type="number" name="existencia" placeholder="Existencia" min="0"  required="required"></div>
                                    <div class="form-group">Categor&iacute;a: 
                                        <select name="idCategoria" id="txtNombreCategoria" class="form-control">
                                            <c:forEach 
                                                var="dtoc"
                                                items="${listaDeCategorias}">
                                                <option value="${dtoc.entidad.idCategoria}">${dtoc.entidad.nombreCategoria}</option>  
                                            </c:forEach>
                                        </select>

                                    </div>

                                </div>
                                <div class="modal-footer"><button class="btn btn-light" type="button" data-dismiss="modal">Cancelar</button><button class="btn btn-primary" type="submit">Guardar</button></div>
                            </form>
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


    <script type="text/javascript">
                                                                    $("#crearProducto").click(function () {
                                                                        $("#nvoProducto").show();
                                                                    });

                                                                    $("#cancelarNvoProducto").click(function () {
                                                                        $("#nvoProducto").hide();
                                                                    });

                                                                    function eliminar(idProducto) {
                                                                        location.href = "ProductoServlet?accion=eliminar&idProducto=" + idProducto;
                                                                    }

                                                                    function setInputModal(idProducto, idCategoria) {

                                                                        var nombre = $("#nombreProducto" + idProducto).text();
                                                                        nombre = nombre.trim();
                                                                        var descripcion = $("#descripcion" + idProducto).text();
                                                                        descripcion = descripcion.trim();
                                                                        var precio = $("#precio" + idProducto).text();
                                                                        precio = precio.trim();
                                                                        var existencia = $("#existencia" + idProducto).text();
                                                                        existencia = existencia.trim();


                                                                        $("#productoEditId").val(idProducto);
                                                                        $("#categoriaEditId").val(idCategoria);

                                                                        $("#nombreEdit").val(nombre);
                                                                        $("#descripcionEdit").val(descripcion);
                                                                        $("#precioEdit").val(precio);
                                                                        $("#existenciaEdit").val(existencia);
                                                                        $("#txtNombreCategoria").val(idCategoria);
                                                                    }
    </script>



</html>