/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.DaoFactory;
import dao.UserDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Marko
 */
public class RegisterCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       String firstNameUser=request.getParameter("firstName");
       String lastNameUser=request.getParameter("lastName");
        String loginUser=request.getParameter("login");
          String passwordUser=request.getParameter("password");
          
          DaoFactory factory=DaoFactory.getInstance();
          UserDao users =factory.getUserDao();
          if(users.getByCreteria(loginUser)==null&&firstNameUser!=null){
                User u=new User();
          u.setFirstName(firstNameUser);
          u.setLastName(lastNameUser);
          u.setLogin(loginUser);
          u.setPassword(passwordUser);
          u.setIsAdmin(false);
          users.create(u);
          return "/index.html";
          } else {
              return "/RegistrationJSP.jsp";
          }    
    }
    }
    

