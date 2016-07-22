/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.BookDao;
import dao.DaoFactory;
import dao.ReserveDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Reserve;

/**
 *
 * @author Marko
 */
public class MyOldReservesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
              DaoFactory factory=DaoFactory.getInstance();
          ReserveDao reserves=factory.getReserveDao();
           if(request.getParameter("oldReserveId")!=null){
           BookDao books=factory.getBookDao();
           String id=request.getParameter("oldReserveId");
           Reserve reserve=reserves.getByCreteria(id);       
                HttpSession hs = request.getSession(true);
                Book book = books.getByCreteria(reserve.getBookTitle());
                hs.setAttribute("titleBookforReserve", reserve.getBookTitle());
                request.setAttribute("book", book);
                return "/CreateReserve.jsp";
        }
           HttpSession hs = request.getSession(true);         
        List<Reserve> listReserves=reserves.getAllOldResByLogin((String)hs.getAttribute("login"));
        request.setAttribute("reserves", listReserves);
          return "/MyOldReserves.jsp";
    }
    }
    

