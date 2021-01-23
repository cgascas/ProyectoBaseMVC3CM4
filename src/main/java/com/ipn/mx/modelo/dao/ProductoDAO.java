/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import com.ipn.mx.modelo.dto.CategoriaDTO;
import com.ipn.mx.modelo.dto.ProductoDTO;
import com.ipn.mx.modelo.entidades.Producto;
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
public class ProductoDAO {

    public void create(ProductoDTO dto) {
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

    public void update(ProductoDTO dto) {
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

    public void delete(ProductoDTO dto) {
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

    public ProductoDTO read(ProductoDTO dto) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        try {
            transaction.begin();
            dto.setEntidad(sesion.get(dto.getEntidad().getClass(), dto.getEntidad().getIdProducto()));
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
            Query q = sesion.createQuery("from Producto p order by p.idProducto");
            for (Producto u : (List<Producto>) q.list()) {
                ProductoDTO dto = new ProductoDTO();
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

   

    public static void main(String[] args) {
        ProductoDAO dao = new ProductoDAO();
        ProductoDTO dto = new ProductoDTO();
        dto.getEntidad().setIdProducto(1);
        
        
        //System.out.println(dao.readAll());
        System.out.println(dao.read(dto));
        
    }

    public List readAllByCategoria(CategoriaDTO dtoc) {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("from Producto p where p.idCategoria.idCategoria=:idCategoria");
            q.setParameter("idCategoria", dtoc.getEntidad().getIdCategoria());
            for (Producto u : (List<Producto>) q.list()) {
                ProductoDTO dto = new ProductoDTO();
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

}
