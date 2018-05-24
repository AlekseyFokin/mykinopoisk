/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import command.ActionCommand;
import command.EmptyCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Фокин
 */
public class ActionFactory {
    public ActionCommand defineCommand(HttpServletRequest request,HttpServletResponse response)
    {
       ActionCommand current=new EmptyCommand();    
       String action= request.getParameter("command");
       if (action==null || action.isEmpty()) {return current;}
       else {
           try{ CommandEnum  currentEnum=CommandEnum.valueOf(action);
                  current=currentEnum.getCurrentCommand();
                  System.out.println("Получена команда"+action);
           }
           catch (IllegalArgumentException e){request.setAttribute("wrongAction", "command not found!");}
       }
       return current;
       
    }
    
    
}
