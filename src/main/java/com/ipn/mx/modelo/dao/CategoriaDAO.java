/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;


import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.entidades.Categoria;
import com.ipn.mx.modelo.entidades.CategoriaCountProductos;
import com.ipn.mx.utilerias.HibernateUtil;
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
public class CategoriaDAO {

    public void create(CategoriaDTO dto) {
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

    public void update(CategoriaDTO dto) {
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

    public void delete(CategoriaDTO dto) {
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

    public CategoriaDTO read(CategoriaDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            //select * from usuario where idUsuario = ?
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdCategoria()));
            //dto.setEntidad(dto.getEntidad());
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return dto;
    }

    public List readAll() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("from Categoria c order by c.idCategoria");
            for (Categoria u : (List<Categoria>) q.list()) {
                CategoriaDTO dto = new CategoriaDTO();
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

    public List countByCategoria() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("from Categoria c ");
            for (Categoria u : (List<Categoria>) q.list()) {
                
                lista.add(new CategoriaCountProductos(u.getNombreCategoria(), u.getListaProductos().size()));
            }
            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }
   

    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        CategoriaDTO dto = new CategoriaDTO();
       
        
        
        //System.out.println(dao.readAll());
        System.out.println(dao.countByCategoria());
        
    }

}
