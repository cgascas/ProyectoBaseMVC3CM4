/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.GraficaDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.GraficaDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.utilerias.Conexion;
import com.ipn.mx.utilerias.EnviarEmail;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import org.jfree.chart.ChartFactory;

import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

/**
 *
 * @author darkdestiny
 */
@WebServlet(name = "CategoriaServlet", urlPatterns = {"/CategoriaServlet"})
public class CategoriaServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");

        UsuarioDTO dto = (UsuarioDTO)  request.getSession().getAttribute("usuario");
        if (dto == null) {
            RequestDispatcher rd = request.getRequestDispatcher("ui/login.jsp");
            rd.forward(request, response);
        } else {
            request.setAttribute("usuario", dto);
            String accion = request.getParameter("accion");
            if (accion.equals("listaDeCategorias")) {
                listaDeCategorias(request, response);
            } else if (accion.equals("nuevo")) {
                agregarCategoria(request, response);
            } else if (accion.equals("eliminar")) {
                eliminarCategoria(request, response);
            } else if (accion.equals("actualizar")) {
                actualizarCategoria(request, response);
            } else if (accion.equals("guardar")) {
                almacenarCateroria(request, response);
            } else if (accion.equals("ver")) {
                mostrarCategoria(request, response);

            } else if (accion.equals("generarReporte")) {
                generarReporte(request, response);
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

    private void listaDeCategorias(HttpServletRequest request, HttpServletResponse response) {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            List lista = dao.readAll();
            List count = dao.countByCategoria();
            request.setAttribute("listaDeCategorias", lista);
            request.setAttribute("listaDeCategoriasCount", count);
            RequestDispatcher rd = request.getRequestDispatcher("ui/index.jsp");
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void agregarCategoria(HttpServletRequest request, HttpServletResponse response) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        dto.getEntidad().setNombreCategoria(nombre);
        dto.getEntidad().setDescripcionCategoria(descripcion);
        dao.create(dto);
        UsuarioDTO usuario = (UsuarioDTO)  request.getSession().getAttribute("usuario");
        EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Categoria Creada", "Se ha creado una categoría nueva: "  + dto.getEntidad().getNombreCategoria());
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
        try {
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void eliminarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        dto = dao.read(dto);
        dao.delete(dto);
        UsuarioDTO usuario = (UsuarioDTO)  request.getSession().getAttribute("usuario");
        EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Categoria Eliminada", "Se ha eliminado una categoría: "  + dto.getEntidad().getNombreCategoria());
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
        vista.forward(request, response);
    }

    private void actualizarCategoria(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
        dto.getEntidad().setIdCategoria(Integer.parseInt(request.getParameter("id")));

        System.out.println("Id cateogira   /////" + dto.getEntidad().getIdCategoria());
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        dto = dao.read(dto);
        System.out.println("dto recuperado " + dto.getEntidad().getIdCategoria());
        dto.getEntidad().setNombreCategoria(nombre);
        dto.getEntidad().setDescripcionCategoria(descripcion);
        dao.update(dto);
        UsuarioDTO usuario = (UsuarioDTO)  request.getSession().getAttribute("usuario");
        EnviarEmail.enviarCorreo(usuario.getEntidad().getEmail(), "Categoria Actualizada", "Se ha actualizado una categoría: "  + dto.getEntidad().getNombreCategoria());
        RequestDispatcher vista = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
        vista.forward(request, response);
    }

    private void almacenarCateroria(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void mostrarCategoria(HttpServletRequest request, HttpServletResponse response) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private PieDataset getGraficaProductos() {
        DefaultPieDataset pie3d = new DefaultPieDataset();
        GraficaDAO gDAO = new GraficaDAO();
        List datos = gDAO.readAll();
        for (int i = 0; i < datos.size(); i++) {
            GraficaDTO dto = (GraficaDTO) datos.get(i);
            pie3d.setValue(dto.getNombre(), dto.getCantidad());
        }
        return pie3d;
    }

    private void generarReporte(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            CategoriaDAO dao = new CategoriaDAO();
            ServletOutputStream sos = response.getOutputStream();
            File reporte = new File(getServletConfig().getServletContext().getRealPath("/reportes/reporteGrafica.jasper"));
            byte[] bytes = JasperRunManager.runReportToPdf(reporte.getPath(), null, Conexion.obtenerConexion());
            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            sos.write(bytes, 0, bytes.length);
            sos.flush();
            sos.close();
        } catch (JRException ex) {
            Logger.getLogger(CategoriaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
