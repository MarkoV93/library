/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command for loging out from accaunt and return puth on "Login.jsp"
 * @author Marko
 */
public class LogOutCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        HttpSession hs = request.getSession(true);
        ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", Locale.getDefault());
        request.setAttribute("message", labels.getString("logOutFrom"));//Send message for user about logging out from accaunt
         hs.setAttribute("req", "/login");
        hs.removeAttribute("login");//deleted attribute "login" from  HttpSession
        return "/Login.jsp";
    }

}
