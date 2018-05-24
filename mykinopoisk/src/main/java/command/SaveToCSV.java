/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package command;

import entities.Movie;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Фокин
 */
public class SaveToCSV implements ActionCommand {

       @Override
    public String execute(HttpServletRequest request,HttpServletResponse response) {
        
        BufferedInputStream input = null;
        BufferedOutputStream output = null;
        try {
               File f = new File("myRequest.csv");
               if (f.exists())  f.delete();
               f = new File("myRequest.csv");
               
               response.setContentType("application/octet-stream");
               response.setHeader("Content-Disposition","attachment;filename="+f.getPath());
               
               final OutputStream os = new FileOutputStream(f); // класс записи байтов в файл
               
               HttpSession session = request.getSession(true);        
               Set<Movie> listMovies=(HashSet<Movie>) session.getAttribute("listMovies");
               
               listMovies.stream().forEach(movie->{
                   try {
                        os.write( movie.toCSV().getBytes() );
                       } catch (UnsupportedEncodingException ex) {System.out.println("Кодировка файла не поддерживается");  } 
                         catch (IOException ex) {System.out.println("Ошибка чтения или записи");}
                                                  });
                os.flush();
                os.close();
                System.out.println("Файл записан");
        
                //  отправляю в респонс
               input = new BufferedInputStream(new FileInputStream(f), 10240);
               output = new BufferedOutputStream(response.getOutputStream(), 10240);
               byte[] buffer = new byte[10240];
               int length;
               while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
               }
               input.close();
               output.close();
         } catch (FileNotFoundException ex) {System.out.println("Файл не найден");}
           catch (UnsupportedEncodingException ex) {System.out.println("Кодировка файла не поддерживается");}
           catch (IOException ex) {System.out.println("Ошибка чтения или записи в поток ответа");} 
          finally{
                  if (output!=null) {try {output.close();} catch (IOException ex) {System.out.println("Поток out не найден"); }}
                  if (input!=null) {try {input.close();} catch (IOException ex) {System.out.println("Поток in не найден"); }}
                 }
     return "./mainpage.jsp";       
    }
    
}
