
package controlers;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Marko
 */
public class NewServlet extends HttpServlet {

  
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      String firstNameUser=request.getParameter("firstName");
       String lastNameUser=request.getParameter("lastName");
        String loginUser=request.getParameter("login");
          String passwordUser=request.getParameter("password");
          DaoFactory factory=DaoFactory.getInstance();
          UserDao users =factory.getUserDao();
          User u=new User();
          u.setFirstName(firstNameUser);
          u.setLastName(lastNameUser);
          u.setLogin(loginUser);
          u.setPassword(passwordUser);
          u.setIsAdmin(false);
          RequestDispatcher rd=request.getRequestDispatcher("/index.html");
          users.verifyAndInsert(u);
          rd.include(request, response);
          
          
         
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
