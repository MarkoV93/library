/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marko
 */
public class ChangeLanguageCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession hs = request.getSession();
        String req = (String) hs.getAttribute("req");
        if (req == null) {
            req = "/login";
        }
        Command handler = CommandFactory.getInstance().getCommand(req);
        String path = null;
        Locale locale = (Locale) hs.getAttribute("locale");

        if (locale == null) {
           Locale newLocale = new Locale("ua", "UA");
                hs.setAttribute("locale", newLocale);
        } else {
            if (locale.getLanguage().equals("en")) {
                Locale newLocale = new Locale("ua", "UA");
                hs.setAttribute("locale", newLocale);

            } else {
                Locale newLocale = new Locale("en", "US");
                hs.setAttribute("locale", newLocale);
            }
        }
        path = handler.execute(request, response);
        return path;
    }

}
