/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.utilerias.EnviarEmail;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author werog
 */
@WebServlet(name = "ProductoServlet", urlPatterns = {"/ProductoServlet"})
public class ProductoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        UsuarioDTO dto = (UsuarioDTO) request.getSession().getAttribute("usuario");
        if (dto == null) {
            RequestDispatcher rd = request.getRequestDispatcher("ui/login.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("usuario", dto);
            if (accion.equals("listaDeProductos")) {
                listaDeProductos(request, response);
            } else if (accion.equals("nuevo")) {
                agregarProducto(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarProducto(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarProducto(request, response);
            } else {
                System.out.println("opcion invalida");
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private void listaDeProductos(HttpServletRequest request, HttpServletResponse response) {
        try {
            CategoriaDAO daoc = new CategoriaDAO();
            CategoriaDTO dtoc = new CategoriaDTO();
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            dtoc.getEntidad().setIdCategoria(idCategoria);
            dtoc = daoc.read(dtoc);
            ProductoDAO daop = new ProductoDAO();
            List lista = daop.readAllByCategoria(dtoc);
            List listaCat = daoc.readAll();
            request.setAttribute("listaDeProductos", lista);
            request.setAttribute("listaDeCategorias", listaCat);
            request.setAttribute("idCategoria", idCategoria);
            request.setAttribute("categoria", dtoc.getEntidad().getNombreCategoria());
            RequestDispatcher rd = request.getRequestDispatcher("ui/table.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarProducto(HttpServletRequest request, HttpServletResponse response) {

        try {
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            ProductoDAO daop = new ProductoDAO();
            ProductoDTO dtop = new ProductoDTO();
            dtop.getEntidad().setNombreProducto(nombre);
            dtop.getEntidad().setDescripcionProducto(descripcion);
            dtop.getEntidad().setPrecio(precio);
            dtop.getEntidad().setExistencia(existencia);
            CategoriaDAO daoc = new CategoriaDAO();
            CategoriaDTO dtoc = new CategoriaDTO();
            dtoc.getEntidad().setIdCategoria(idCategoria);
            dtoc = daoc.read(dtoc);
            dtop.getEntidad().setIdCategoria(dtoc.getEntidad());
            daop.create(dtop);
            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Producto Creado", "Se ha crado un producto nuevo: " + dtop.getEntidad().getNombreProducto());
            RequestDispatcher vista = request.getRequestDispatcher("ProductoServlet?accion=listaDeProductos&idCategoria=" + idCategoria);
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("idProducto")));
            
            dto = dao.read(dto);
            dao.delete(dto);
            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Producto Eliminado", "Se ha eliminado un producto: " + dto.getEntidad().getNombreProducto());
            RequestDispatcher vista = request.getRequestDispatcher("ProductoServlet?accion=listaDeProductos&idCategoria=" + dto.getEntidad().getIdCategoria().getIdCategoria());
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void actualizarProducto(HttpServletRequest request, HttpServletResponse response) {
        try {
            ProductoDAO dao = new ProductoDAO();
            ProductoDTO dto = new ProductoDTO();
            dto.getEntidad().setIdProducto(Integer.parseInt(request.getParameter("productoEditId")));
            
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            float precio = Float.parseFloat(request.getParameter("precio"));
            int existencia = Integer.parseInt(request.getParameter("existencia"));
            int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
            int idCategoriaAnterior = Integer.parseInt(request.getParameter("categoriaEditId"));
            CategoriaDAO daoc = new CategoriaDAO();
            CategoriaDTO dtoc = new CategoriaDTO();
            dtoc.getEntidad().setIdCategoria(idCategoria);
            dto = dao.read(dto);
            System.out.println("dto recuperado " + dto.getEntidad().getIdCategoria());
            dto.getEntidad().setNombreProducto(nombre);
            dto.getEntidad().setDescripcionProducto(descripcion);
            dto.getEntidad().setPrecio(precio);
            dto.getEntidad().setExistencia(existencia);
            dto.getEntidad().setIdCategoria(dtoc.getEntidad());
            dao.update(dto);
            UsuarioDTO usuario = (UsuarioDTO) request.getSession().getAttribute("usuario");
            EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Producto Actualizado", "Se ha actualizado un producto: " + dto.getEntidad().getNombreProducto());
            RequestDispatcher vista = request.getRequestDispatcher("ProductoServlet?accion=listaDeProductos&idCategoria=" + idCategoria);
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ProductoServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
