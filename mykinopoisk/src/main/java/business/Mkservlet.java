/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import DAO.HibernateUtil;
import command.ActionCommand;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/mkservlet")
/**
 *
 * @author Фокин
 */
public class Mkservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {processRequest(request,response);}
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException
    {processRequest(request,response);}
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page=null;
        ActionFactory client = new ActionFactory();
        
         System.out.println("получена команда "+ request.getParameter("command"));
        ActionCommand command=client.defineCommand(request,response);
        page=command.execute(request,response);
        
        if (page!=null)
        {
           if (command.toString().contains("SaveToCSV")) {} //мимо jsp
           else {request.getRequestDispatcher(page).forward(request, response);}
        }
        else{response.sendRedirect("./index.jsp");}
        
        // if (HibernateUtil.getSessionFactory()!=null)
 //  {
     //  HibernateUtil.getSessionFactory().close();
     //  HibernateUtil.destroyServiceRegistry();
  // }
  
  }
    

        
    @Override
    public void destroy() {
}

}
