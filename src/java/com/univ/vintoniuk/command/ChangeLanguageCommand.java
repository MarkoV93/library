/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko  Command for change language and awerloading page
 */
public class ChangeLanguageCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        HttpSession hs = request.getSession(true);
        Locale newLocale;  
        String path = null;
        Locale locale = Locale.getDefault();
        if (locale.getLanguage().equals("en")) {//invert locale
            newLocale = new Locale("ua", "UA");
            Locale.setDefault(newLocale);
            hs.setAttribute("locale", newLocale);

        } else {//invert locale
             newLocale = new Locale("en", "US");
            Locale.setDefault(newLocale);
            hs.setAttribute("locale", newLocale);
        }
        request.setAttributesMap((Map<String, Object>) hs.getAttribute("atrributes"));//replaced attributes of request from the previous request
        return (String)hs.getAttribute("path");//return privious path
    }

}
