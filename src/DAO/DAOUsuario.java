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

import hibernate.Usuarios;
import java.util.ArrayList;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

public class DAOUsuario {
    public boolean validaUsuario(Usuarios usuario){
        boolean valido = false;

        SessionFactory sesion = NewHibernateUtil.getSessionFactory();
        Session session;
        session = sesion.openSession();
        
        Criteria criteria = session.createCriteria(Usuarios.class);
        criteria.add(Restrictions.eq("nombre", usuario.getNombre()));
        criteria.add(Restrictions.eq("contrasenia", usuario.getContrasenia()));

        List<Usuarios> usuarioV = (List<Usuarios>)criteria.list();
        if(usuarioV.isEmpty())valido=false;
        else valido = true;
        
        return valido;
    }

}
