/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ipn.mx.modelo.dao;

import java.util.ArrayList;
import java.util.List;
import com.ipn.mx.utilerias.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author darkdestiny
 */
public class GraficaDAO {

    public List readAll() {
        Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction transaction = sesion.getTransaction();
        List lista = new ArrayList();
        try {
            transaction.begin();
            Query q = sesion.createQuery("select c.nombreCategoria as categoria, count(*) as productos from Producto p, Categoria c where p.idCategoria = c.idCategoria group by c.idCategoria");
            lista = q.list();

            

            transaction.commit();
        } catch (HibernateException he) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
        return lista;
    }

    public static void main(String[] args) {
        GraficaDAO dao = new GraficaDAO();

    }
}
