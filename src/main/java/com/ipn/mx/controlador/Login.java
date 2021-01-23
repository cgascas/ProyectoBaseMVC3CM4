/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.controlador;

import com.ipn.mx.modelo.dao.CategoriaDAO;
import com.ipn.mx.modelo.dao.ProductoDAO;
import com.ipn.mx.modelo.dao.UsuarioDAO;
import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.utilerias.LoginManagerVF;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author werog
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean isMultipart;
    private static String filePath = "C:/Users/werog/Downloads/apache-tomcat-9.0.38/webapps/ROOT/img/";
    private int maxFileSize = 200 * 1024;
    private int maxMemSize = 4 * 1024;
    private File file;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String accion = request.getParameter("accion");
        if (accion == null) {
            UsuarioDTO dto = (UsuarioDTO) request.getSession().getAttribute("usuario");
            if (dto != null) {
                RequestDispatcher rd = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
                rd.forward(request, response);
            } else {
                RequestDispatcher rd = request.getRequestDispatcher("ui/login.jsp");
                rd.forward(request, response);
            }

        } else if (accion.equals("entrar")) {
            //String email = request.getParameter("email");

            UsuarioDAO dao = new UsuarioDAO();
            UsuarioDTO dto = new UsuarioDTO();

            String username = request.getParameter("username");
            String password = request.getParameter("password");
            dto.setEntidad(dao.findByUserNameAndPassword(username, password));
            //dto.getEntidad().setEmail(email);
            //dto = dao.readbyemail(dto);
            if (dto.getEntidad() == null) {
                RequestDispatcher rd = request.getRequestDispatcher("ui/register.jsp");
                rd.forward(request, response);
            } else {
//                if (dto.getEntidad().getClaveusuario().equals(password)) {
//                    //request.getSession().setAttribute("usuario", dto);
//                    LoginManagerVF lg = new LoginManagerVF();
//                    lg.login(request, response, dto);
//                    request.setAttribute("usuario", dto);
//                    RequestDispatcher rd = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
//                    rd.forward(request, response);
//                } else {
//                    request.setAttribute("email", email);
//                    RequestDispatcher rd = request.getRequestDispatcher("ui/login.jsp");
//                    rd.forward(request, response);
//                }
                LoginManagerVF lg = new LoginManagerVF();
                lg.login(request, response, dto);
                request.setAttribute("usuario", dto);
                RequestDispatcher rd = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
                rd.forward(request, response);
            }

            //verificar y obtener objero usuario
            //subir a session
        } else if (accion.equals("salir")) {

            //UsuarioDTO dto = (UsuarioDTO) request.getSession().getAttribute("usuario");
            LoginManagerVF lg = new LoginManagerVF();
            if (lg.getLoginName(request, response)) {
                //request.getSession().removeAttribute("usuario");               
                lg.logout(request, response);
            }
            RequestDispatcher rd = request.getRequestDispatcher("ui/login.jsp");
            rd.forward(request, response);
        } else if (accion.equals("perfil")) {
            UsuarioDTO dto = new UsuarioDTO();
            dto = (UsuarioDTO) request.getSession().getAttribute(LoginManagerVF.LOGIN_NAME_SESSION_ATTRIBUTE);
            if (dto.getEntidad().getPath() == null) {
                dto.getEntidad().setPath(filePath + "nophoto.png");
            }
            File file = new File(dto.getEntidad().getPath());
            request.setAttribute("usuario", dto);
            request.setAttribute("imagen", file.getName());
            RequestDispatcher rd = request.getRequestDispatcher("ui/profile.jsp");
            rd.forward(request, response);

        } else if (accion.equals("update")) {
            actualizarUsuario(request, response);
        } else if (accion.equals("guardarFoto")) {
            String email = request.getParameter("email");
            System.out.println("VOY A GUARDAR FOTO PARA " + email);
            guardarFoto(request, response, email);
        } else if (accion.equals("create")) {
            crearUsuario(request, response);
        } else if (accion.equals("registrar")) {
            RequestDispatcher rd = request.getRequestDispatcher("ui/register.jsp");
            rd.forward(request, response);
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

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) {
        try {
            UsuarioDAO dao = new UsuarioDAO();
            UsuarioDTO dto = new UsuarioDTO();
//            dto.getEntidad().setEmail(request.getParameter("email"));
            dto = (UsuarioDTO) request.getSession().getAttribute(LoginManagerVF.LOGIN_NAME_SESSION_ATTRIBUTE);
//            dto = dao.readbyemail(dto);
            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setPaterno(request.getParameter("paterno"));
            dto.getEntidad().setMaterno(request.getParameter("materno"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dao.update(dto);
            request.getSession().setAttribute("usuario", dto);
            RequestDispatcher vista = request.getRequestDispatcher("Login?accion=perfil");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void crearUsuario(HttpServletRequest request, HttpServletResponse response) {

        try {
            UsuarioDAO dao = new UsuarioDAO();
            UsuarioDTO dto = new UsuarioDTO();

            dto.getEntidad().setNombre(request.getParameter("nombre"));
            dto.getEntidad().setPaterno(request.getParameter("paterno"));
            dto.getEntidad().setMaterno(request.getParameter("materno"));
            dto.getEntidad().setEmail(request.getParameter("email"));
            dto.getEntidad().setNombreUsuario(request.getParameter("nombreUsuario"));
            dto.getEntidad().setClaveUsuario(request.getParameter("password"));
            dto.getEntidad().setTipoUsuario("O");

            dao.create(dto);
            request.getSession().setAttribute("usuario", dto);
            request.setAttribute("usuario", dto);
            RequestDispatcher vista = request.getRequestDispatcher("CategoriaServlet?accion=listaDeCategorias");
            vista.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void guardarFoto(HttpServletRequest request, HttpServletResponse response, String email) throws IOException {
        // Check that we have a file upload request
        isMultipart = ServletFileUpload.isMultipartContent(request);

        if (!isMultipart) {

            System.out.println("NO subio foto");
        } else {
            System.out.println("SI SUBIO FOTO");
        }

        DiskFileItemFactory factory = new DiskFileItemFactory();

        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);

        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("C:\\temp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);

        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);

        try {
            // Parse the request to get file items.
            List fileItems = upload.parseRequest(request);

            // Process the uploaded file items
            Iterator i = fileItems.iterator();

            while (i.hasNext()) {
                FileItem fi = (FileItem) i.next();
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();

                    // Write the file
                    if (fileName.lastIndexOf("\\") >= 0) {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
                    } else {
                        file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
                    }
                    fi.write(file);
                    System.out.println("Uploaded Filename: " + file.getAbsolutePath());
                    System.out.println("Voy a editar " + email);
                    UsuarioDAO dao = new UsuarioDAO();
                    UsuarioDTO dto = new UsuarioDTO();
                    dto.getEntidad().setEmail(email);
                    dto = (UsuarioDTO) request.getSession().getAttribute(LoginManagerVF.LOGIN_NAME_SESSION_ATTRIBUTE);
//                    if (file != null) {
//                        dto.getEntidad().setPath(this.file.getPath());
//                    }
                    dao.update(dto);
                    request.getSession().setAttribute("usuario", dto);
                    RequestDispatcher vista = request.getRequestDispatcher("Login?accion=perfil");
                    vista.forward(request, response);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
