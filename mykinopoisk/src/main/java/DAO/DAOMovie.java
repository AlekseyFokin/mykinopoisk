/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import entities.Movie;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Фокин
 */
public class DAOMovie {
    private static SessionFactory sessionFactory;

public DAOMovie ()
{
    sessionFactory=HibernateUtil.getSessionFactory();  
}

  public void addMovie(Movie movie) {
        Session ses=null;
        if (isMovieInDBbyId(movie.getId()))
        {             try{
                                ses=sessionFactory.openSession();
                                ses.beginTransaction();
                                ses.save(movie);
                                ses.getTransaction().commit();
                              }
                        catch (HibernateException ex){ses.getTransaction().rollback();ex.printStackTrace();}
                        finally{ if (  ses!=null){ ses.close(); }}
        }
      }
  
  public boolean isMovieInDBbyId(int Id){
   Session ses=null;
   boolean result=true;
        try{
        ses=sessionFactory.openSession();
        ses.beginTransaction();
        Movie tempMovie= (Movie) ses.get(Movie.class, Id);
        if (tempMovie!=null) {result=false; }
        ses.getTransaction().commit();
       }
        catch (HibernateException ex){ses.getTransaction().rollback();ex.printStackTrace();}
        finally{ if (  ses!=null){ ses.close(); }}
        return result;
  }
    
}
