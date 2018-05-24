/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entities.Actor;
import entities.Movie;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


/**
 *
 * @author Фокин
 */
public class SearchMovieByTitle implements ActionCommand{

    @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
      
        String MovieTitle=request.getParameter("movieTitle");
      
        System.out.println("ищу по подстроке:  " +MovieTitle);
        Document doc = null;
try {
    System.out.println("Поиск "+"https://www.kinopoisk.ru/index.php?kp_query="+URLEncoder.encode(MovieTitle,"cp1251"));
doc = Jsoup.connect("https://www.kinopoisk.ru/index.php?kp_query="+URLEncoder.encode(MovieTitle,"UTF8")).get();
        } catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//search_results search_results_last

//Element a0 = doc.before("<div class='search_results search_results_last'>");
//Elements a0= doc.getAllElements();
//Elements a01=a0.before("<div class='search_results search_results_last'>");
//Element r=doc.before("<div class=\"search_results search_results_last\">");
//doc.

// System.out.println("!!!!!!"+r.text());

Elements a = doc.getElementsByAttributeValue("class","info");
//Iterator it1=a.eachText().iterator();
Iterator it1=a.iterator();
Set <Movie> arrOfMovie =new HashSet();

while (it1.hasNext()){
                                                   Element el=(Element) it1.next();
                                                   int id_movie= Integer.parseInt(el.getElementsByClass("js-serp-metrika").attr("data-id"));        System.out.println("id= "+ id_movie); //data-id
                                                    //System.out.println( el.getElementsByClass("js-serp-metrika").text()); //название
                                                   Elements el1=el.getElementsByClass("js-serp-metrika");
                                                   if (el1.size()>2) //фильм
                                                   {
                                                   String movieName=el1.get(0).text();
                                                   System.out.println("Название "+movieName);
                                                   Elements el3=el.getElementsByClass("gray");
                                                   String lengthMovie=el3.get(0).text().substring(el3.get(0).text().indexOf(",")+1);
                                                   System.out.println("Продолжительность "+lengthMovie);
                                                   String countryMovie=el3.get(1).text().substring(0, el3.get(1).text().indexOf(" "));
                                                   System.out.println("Производство "+countryMovie);
                                                   Elements el4=el.getElementsByClass("year");
                                                   String yearMovie="";
                                                   if((el4!=null)&&(el4.size()>0)) {yearMovie=el4.get(0).text();} 
                                                   System.out.println("Год "+yearMovie);        
                                                   
                                                   Set<Actor> arrayOfActors=new HashSet();
                                                   
                                                   Movie m=new Movie(id_movie, movieName,"https://st.kp.yandex.net/images/sm_film/"+id_movie+".jpg",arrayOfActors,yearMovie,countryMovie,lengthMovie);
                                                    
                                                   int ActorId=Integer.parseInt(el1.get(2).attr("data-url").substring(el1.get(2).attr("data-url").indexOf("/", 1)+1,el1.get(2).attr("data-url").length()-1 ));
                                                   Actor a1=new Actor(ActorId,el1.get(2).text(), "https://st.kp.yandex.net/images/actor/"+ActorId+".jpg");
                                                   System.out.println("Актер1 "+a1.getFio()+" id= "+a1.getPic());
                                                   arrayOfActors.add(a1);
                                                   ActorId=Integer.parseInt(el1.get(3).attr("data-url").substring(el1.get(3).attr("data-url").indexOf("/", 1)+1,el1.get(3).attr("data-url").length()-1 ));
                                                   Actor a2=new Actor(ActorId,el1.get(3).text(),"https://st.kp.yandex.net/images/actor/"+ActorId+".jpg");
                                                   System.out.println("Актер2 "+a2.getFio()+" id= "+a2.getPic());
                                                   arrayOfActors.add(a2);
                                                   
                                                   
                                                   arrOfMovie.add(m);
                                                   System.out.println();                                                   
                                                  }
                                                   else {  break;}
                                                //   catch (Exception e) {System.out.println("Закончились фильмы");}
                                                }
//request.setAttribute("listMovies", arrOfMovie);
HttpSession session = request.getSession(true);
session.setAttribute("listMovies", arrOfMovie);
 session.setAttribute("movieTitle",MovieTitle);
       
        
//System.out.println("title: " +title );
//System.out.println("headers: " + response.getHeaders());
//System.out.println("body:" + response.readEntity(String.class));
        
        return "./mainpage.jsp";
    }
    
}
