/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.univ.vintoniuk.command;

import com.univ.vintoniuk.requestWrapper.IRequestWrapper;
import com.univ.vintoniuk.dao.DAOLibraryException;
import com.univ.vintoniuk.dao.DaoFactory;
import com.univ.vintoniuk.dao.ReserveDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.univ.vintoniuk.model.Reserve;

/**
 *
 * @author Marko
 * Class for watching personal user reserves, and cenceling  reserves for wieving  
 * @Return "/MyReserves.jsp"
 */
public class MyReserveCommand extends Command {

    @Override
    public String execute(IRequestWrapper request) throws DAOLibraryException {
        DaoFactory factory = this.getFactory();
        ReserveDao reserves = factory.getReserveDao();
        HttpSession hs = request.getSession(true);
        if (request.getParameter("censelId") != null) {//if user press button "cencel Reserve" 
            ResourceBundle labels = ResourceBundle.getBundle("com.univ.vintoniuk.properties.text", (Locale) hs.getAttribute("locale"));
            String id = request.getParameter("censelId");
            if (reserves.getByCreteria(id).getAnswer().equals("wiev for give to reading room") || reserves.getByCreteria(id).getAnswer().equals("wiev for give to hend")) {
                //if reserve did not wiev then reserve cencel
                request.setAttribute("message", labels.getString("reserveCenseled"));
                reserves.updateByCreteria("refused", id);
            } else { //if reserve waived , display message about impossibility of action
                request.setAttribute("message", labels.getString("reserweWasViewed"));
            }
        }

        List<Reserve> listReserves = reserves.getReservesByLogin((String) hs.getAttribute("login"));
        request.setAttribute("reserves", listReserves);
        return "/MyReserves.jsp";
    }
}
