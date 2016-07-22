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
import model.Reserve;

/**
 *
 * @author Marko
 */
public class WievOldReservesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
          DaoFactory factory = DaoFactory.getInstance();
        ReserveDao reserves = factory.getReserveDao();
                        List<Reserve> listReserves = reserves.getAllWievedRes();
                request.setAttribute("reserves", listReserves);
       return "/WievOldReserves.jsp";
    }
   
}
