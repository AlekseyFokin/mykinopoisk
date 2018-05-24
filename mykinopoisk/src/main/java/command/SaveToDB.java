/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import DAO.DAOActor;
import DAO.DAOMovie;
import entities.Movie;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Фокин
 */
public class SaveToDB  implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
         DAOActor  DAOA=new DAOActor();
         DAOMovie DAOM = new DAOMovie();
         HttpSession session = request.getSession(true);        
         Set<Movie> listMovies=(HashSet<Movie>) session.getAttribute("listMovies");
               
               listMovies.stream().forEach(movie->{
                                                        //      HashSet actors= (HashSet) movie.getActors();
                                                      //        Iterator it=actors.iterator();
                                                      //        while (it.hasNext())
                                                     //                                          {
                                                     //                                              Actor a=(Actor) it.next();
                                                     //                                              if (DAOA.getActorByID(a.getId())==null)
                                                     //                                              { DAOA.addActor(a);}
                                                     //                                           }
                                                              DAOM.addMovie(movie);
                                                           });
        
      return "./mainpage.jsp";      
    }
    
}
