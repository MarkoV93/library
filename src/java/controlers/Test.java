/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlers;

import dao.BookDao;
import dao.DaoFactory;
import dao.ReserveDao;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Reserve;

/**
 *
 * @author Marko
 */
public class Test extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
  String userLogin=request.getParameter("t1");
       String Booktitle=request.getParameter("t2");
       // String answer=request.getParameter("t3");
       String answer="viewing";
         
          DaoFactory factory=DaoFactory.getInstance();
         ReserveDao reservers =factory.getReserveDao();
     
         Reserve r=new Reserve();
         r.setUserLogin(userLogin);
       r.setBookTitle(Booktitle);
      
        r.setAnswer(answer);
     
          reservers.create(r);
           RequestDispatcher rd=request.getRequestDispatcher("/index.html");
          rd.include(request, response);
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 


}
