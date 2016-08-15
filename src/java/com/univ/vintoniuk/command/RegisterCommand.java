/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.UserDao;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.univ.vintoniuk.model.User;
import java.util.Locale;
import java.util.ResourceBundle;
/**
 *
 * Class for Registration new user
 *
 * @Return "/Login.jsp" and "/RegistrationJSP.jsp"
 */
public class RegisterCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {

        String firstNameUser = request.getParameter("firstName");
        String lastNameUser = request.getParameter("lastName");
        String loginUser = request.getParameter("login");
        String passwordUser = request.getParameter("password");
        DaoFactory factory = this.getFactory();
        UserDao users = factory.getUserDao();
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        if (request.getParameter("Registration") != null && users.getByCreteria(loginUser) == null && firstNameUser != null && loginUser != null && passwordUser != null && lastNameUser != null) {
            //If user press button "Registration" on Registration.jsp and there is no one user with the same login
            if (!loginUser.equals("") && !passwordUser.equals("") && !firstNameUser.equals("") && !lastNameUser.equals("")) {//if oll field is not empty
                User u = new User();
                u.setFirstName(firstNameUser);
                u.setLastName(lastNameUser);
                u.setLogin(loginUser);
                u.setPassword(passwordUser);
                u.setIsAdmin(false);
                users.create(u);
                return "/Login.jsp";//if user created, return to handler Login.jsp
            } else {            //if one of the field is empty then display a message and overload page
                String message = labels.getString("fillRegisterField");
                request.setAttribute("message", message);
            }
        }
        return "/RegistrationJSP.jsp";
    }
}
