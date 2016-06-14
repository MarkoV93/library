
package controlers;

import dao.DaoFactory;
import dao.UserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.User;

/**
 *
 * @author Marko
 */
public class LoginControler extends HttpServlet {

   
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String loginUser=request.getParameter("login");
          String passwordUser=request.getParameter("password");         
       HttpSession hs=request.getSession(true);
       DaoFactory factory=DaoFactory.getInstance();
       UserDao users =factory.getUserDao();
        List<User> listUsers=users.getAll();
       if(hs.getAttribute("firstName")!=null) {
             RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/ProFile.jsp");         
             request.setAttribute("users", listUsers);
             rd.include(request, response);
       } else {
           User u=new User();
          u.setLogin(loginUser);
          u.setPassword(passwordUser);
          if (users.verifyUser(u)){
             RequestDispatcher rd=request.getRequestDispatcher("/WEB-INF/ProFile.jsp");
             request.setAttribute("users", listUsers);
              request.setAttribute("u", u.getFirstName());
              System.out.println(u.getFirstName());
            rd.include(request, response);
          } else {
              RequestDispatcher rd=request.getRequestDispatcher("/RegistrationJSP.jsp");
               rd.include(request, response);
          }
    
       }
       
    }
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
  
 

}
