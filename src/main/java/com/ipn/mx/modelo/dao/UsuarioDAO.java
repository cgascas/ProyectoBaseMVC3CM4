/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;


import com.ipn.mx.modelo.dto.UsuarioDTO;
import com.ipn.mx.modelo.entidades.Usuario;
import com.ipn.mx.utilerias.HibernateUtil;
import com.ipn.mx.utilerias.Utilerias;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author darkdestiny
 */
public class UsuarioDAO {

    public void create(UsuarioDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.save(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void update(UsuarioDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.update(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public void delete(UsuarioDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            sesion.delete(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    public UsuarioDTO read(UsuarioDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            //select * from usuario where idUsuario = ?
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdUsuario()));
            //dto.setEntidad(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return dto;
    }
    
     public Usuario findByUserName(String usuario) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        Usuario u = null;
        try {
            transaction.begin();
            Query q = session.createQuery("from Usuario as u "
                    + "where u.nombreUsuario =:usuario");
            q.setParameter("usuario", usuario);
            List l = q.list();
            if (l.size() > 0) {
                u = (Usuario) l.get(0);
            } else {
                return null;
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return u;
    }

    public List readAll() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            //select * from Usuario u order by u.idUsuario;
            //              Usuario u
            Query q = sesion.createQuery("from Usuario u where u.nombreUsuario =:usuario");
            for (Usuario u : (List<Usuario>) q.list()) {
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(u);
                lista.add(dto);
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }

    public Usuario findByUserNameAndPassword(String usuario, String clave) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = session.getTransaction();
        Usuario u = null;
        try {
            transaction.begin();
            Query q = session.createQuery("from Usuario as u "
                    + "where u.nombreUsuario =:usuario  and u.claveUsuario =:clave ");
            q.setParameter("usuario", usuario);
            q.setParameter("clave", clave);
            List l = q.list();
            if (l.size() > 0) {
                u = (Usuario) l.get(0);
            } else {
                return null;
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return u;
    }

    public static void main(String[] args) {
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioDTO dto = new UsuarioDTO();
        dto.getEntidad().setIdUsuario(11);
        
        dto.getEntidad().setNombreUsuario("batman");
        dto.getEntidad().setClaveUsuario("batman");
        
        
        //System.out.println(dao.readAll());
        //System.out.println(dao.read(dto));
        System.out.println(dao.findByUserNameAndPassword(dto.getEntidad().getNombreUsuario(), dto.getEntidad().getClaveUsuario()));
        if (dao.findByUserNameAndPassword(dto.getEntidad().getNombreUsuario(), dto.getEntidad().getClaveUsuario()) != null){
            Utilerias util = new Utilerias();
            util.enviarCorreo("asuncionez@gmail.com", "mensaje", "trexto");
            
        }
    }

}
