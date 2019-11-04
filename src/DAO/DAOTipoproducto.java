/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author Carlos
 */
import java.util.List;
import java.util.Iterator;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.HibernateException;

import hibernate.Tipoproducto;
import java.util.ArrayList;

public class DAOTipoproducto {

    public int crearTipoProducto(Tipoproducto TipoProducto) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        int id=-1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            id = (Integer)session.save(TipoProducto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
         System.out.println("INSERTADO");

        return id;
    }

    public void deleteTipoProducto(Integer TipoProductoID) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Tipoproducto dao
                    = (Tipoproducto) session.get(Tipoproducto.class, TipoProductoID);
            session.delete(dao);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateTipoProducto(Integer TipoProductoID, String nombreTipo, String descripcion) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Tipoproducto tipoProducto
                    = (Tipoproducto) session.get(Tipoproducto.class, TipoProductoID);
            tipoProducto.setNombreTipo(nombreTipo);
            tipoProducto.setDescripcion(descripcion);

            session.update(tipoProducto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ArrayList<Tipoproducto> listTipoProducto() {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        ArrayList<Tipoproducto> Tipoproductoss = new ArrayList<Tipoproducto>();
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Tipoproducto").list();
            for (Iterator iterator
                    = employees.iterator(); iterator.hasNext();) {
                Tipoproducto dao = (Tipoproducto) iterator.next();
                Tipoproductoss.add(dao);
                System.out.print("Nombre:" + dao.getNombreTipo());
                System.out.print("  Desscripcion: " + dao.getDescripcion());
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return Tipoproductoss;
    }
    
    public Tipoproducto buscarTipo(Integer idTipo){
        Tipoproducto t = null;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            t = (Tipoproducto) session.get(Tipoproducto.class, idTipo);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return t;
    }

}
