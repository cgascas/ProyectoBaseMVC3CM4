/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.utilerias;

import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author werog
 */
@Data
public class LoginManagerVF {
    
    public final static String LOGIN_NAME_SESSION_ATTRIBUTE = "usuario";
    
    public void login(HttpServletRequest request, HttpServletResponse response, UsuarioDTO usuario){
        HttpSession session = request.getSession(true);
        session.setAttribute(LOGIN_NAME_SESSION_ATTRIBUTE, usuario);
    }
    
    public void logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        session.removeAttribute(LOGIN_NAME_SESSION_ATTRIBUTE);
        if (session != null) {
            session.invalidate();
        }
    }
    
    public boolean getLoginName(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session == null) {
            return false;
        } else {
            return session.getAttribute(LOGIN_NAME_SESSION_ATTRIBUTE) != null;
        }
    }
}
