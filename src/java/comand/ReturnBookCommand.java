/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.DaoFactory;
import dao.ReserveDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reserve;

/**
 *
 * @author Marko
 */
public class ReturnBookCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        DaoFactory factory = DaoFactory.getInstance();
        ReserveDao reserves = factory.getReserveDao();
        if (request.getParameter("returnId") != null) {
            String id = request.getParameter("returnId");
            reserves.updateByCreteria("returned", id);
            request.setAttribute("message", "book was returnd");
        }
        List<Reserve> listReserves = reserves.getAllReservsForReturn();
        request.setAttribute("reserves", listReserves);

        return "/ReturnBook.jsp";
    }

}
