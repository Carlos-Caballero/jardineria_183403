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

import hibernate.Riegos;
import java.util.ArrayList;
import java.util.Date;

public class DAORiego {

    public int crearRiego(Riegos riegos) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        int id=-1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            id = (Integer)session.save(riegos);
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

    public void deleteRiego(Integer RiegoID) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Riegos dao
                    = (Riegos) session.get(Riegos.class, RiegoID);
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

    public void updateRiego(Integer RiegoID, Date fecha, int productoID) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Riegos riego
                    = (Riegos) session.get(Riegos.class, RiegoID);
            riego.setProductosId(productoID);
            riego.setFecha(fecha);

            session.update(riego);
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

    public ArrayList<Riegos> listRiegos() {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        ArrayList<Riegos> riegoss = new ArrayList<Riegos>();
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Riegos").list();
            for (Iterator iterator
                    = employees.iterator(); iterator.hasNext();) {
                Riegos dao = (Riegos) iterator.next();
                riegoss.add(dao);
                System.out.print("Fecha:" + dao.getFecha());
                System.out.print("  Producto: " + dao.getProductosId());
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
        return riegoss;
    }

    
     public Riegos buscarRiego(Integer idRiegos){
        Riegos r = null;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            r = (Riegos) session.get(Riegos.class, idRiegos);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return r;
    }
     
    public ArrayList<Riegos> listRiegosByProduct(Integer productoId) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        ArrayList<Riegos> riegoss = new ArrayList<Riegos>();
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Riegos where productosId = "+productoId).list();
            for (Iterator iterator
                    = employees.iterator(); iterator.hasNext();) {
                Riegos dao = (Riegos) iterator.next();
                riegoss.add(dao);
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
        return riegoss;
    }
}
