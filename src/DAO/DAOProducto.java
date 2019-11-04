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

import hibernate.Productos;
import hibernate.Riegos;
import java.util.ArrayList;
import java.util.Date;

public class DAOProducto {

    public int crearProducto(Productos producto) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        int id=-1;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            id = (Integer)session.save(producto);
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

    public void deleteProducto(Integer ProductoID) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Productos dao
                    = (Productos) session.get(Productos.class, ProductoID);
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

    public void updateProducto(Integer ProductoID, String nombre, Integer precio, Integer tipoProducto, Integer estadoProducto, Date fechaIngreso) {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Productos producto
                    = (Productos) session.get(Productos.class, ProductoID);
            producto.setNombre(nombre);
            producto.setPrecio(precio);
            producto.setTipoProducto(tipoProducto);
            producto.setEstadoProducto(estadoProducto);
            producto.setFechaIngreso(fechaIngreso);

            session.update(producto);
            tx.commit();
            System.out.println("ACTUALIZADO");
            System.out.println(producto);
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public ArrayList<Productos> listProductos() {
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        ArrayList<Productos> productoss = new ArrayList<Productos>();
        try {
            tx = session.beginTransaction();
            List employees = session.createQuery("FROM Productos").list();
            for (Iterator iterator
                    = employees.iterator(); iterator.hasNext();) {
                Productos dao = (Productos) iterator.next();
                dao.SetRiegos();
                productoss.add(dao);
                System.out.print("Nombre:" + dao.getNombre());
                System.out.print("  Precio: " + dao.getPrecio());
                System.out.println("  estadoProducto: " + dao.getEstadoProducto());
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
        return productoss;
    }
    
    public Productos buscarProducto(Integer idProducto){
        Productos p = null;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            p = (Productos) session.get(Productos.class, idProducto);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return p;
    }
    
    public void agregarRiego(Integer idProducto,Integer idRiego){
        Productos p = null;
        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            p = (Productos) session.get(Productos.class, idProducto);
            Riegos r = (Riegos) session.get(Riegos.class,idRiego);
            p.setRiego(r);
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

}
