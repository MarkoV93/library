/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.DaoFactory;
import dao.ReserveDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Reserve;

/**
 *
 * @author Marko
 */
public class MyReserveCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         DaoFactory factory=DaoFactory.getInstance();
          ReserveDao reserves=factory.getReserveDao();
           HttpSession hs = request.getSession(true);
      if(request.getParameter("censelId")!=null){
//          if(reserves.getByCreteria(id))
            ResourceBundle labels = ResourceBundle.getBundle("properties.text", (Locale)hs.getAttribute("locale"));   
          String id=request.getParameter("censelId");
          if(reserves.getByCreteria(id).getDate()==null){
           request.setAttribute("message", labels.getString("reserveCenseled"));
          reserves.updateByCreteria("refused", id);
      } else {
               request.setAttribute("message", labels.getString("reserweWasViewed"));
              }
      }
             
        List<Reserve> listReserves=reserves.getReservesByLogin((String)hs.getAttribute("login"));
        request.setAttribute("reserves", listReserves);
          return "/MyReserves.jsp";
    }
}
