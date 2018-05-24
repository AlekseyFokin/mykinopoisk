/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Actor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Фокин
 */
public class DAOActor {
    
       private static SessionFactory sessionFactory;

public  DAOActor ()
{
    sessionFactory=HibernateUtil.getSessionFactory();  
}

 public  Actor getActorByID(Integer ID)
{
     Session ses=null;
      Actor actor=null;
        try{
     //   ses= sessionFactory.openSession();
        ses=sessionFactory.openSession();
        ses.beginTransaction();
        actor=( Actor) ses.createQuery("select a from Actor a where a.Id= :aID").setParameter("aID",  ID).uniqueResult();
        ses.getTransaction().commit();
        }
        catch (HibernateException ex){ses.getTransaction().rollback();ex.printStackTrace();}
        finally{ if (  ses!=null){ ses.close(); }}
        return actor;}
 
  public void addActor(Actor actor) {
        Session ses=null;
        try{
        ses= sessionFactory.openSession();
        ses.beginTransaction();
        ses.save(actor);
        ses.getTransaction().commit();
       }
        catch (HibernateException ex){ses.getTransaction().rollback();ex.printStackTrace();}
         finally{ if (  ses!=null){ ses.close(); }}
      }
    
}
