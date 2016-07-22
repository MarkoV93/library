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
import model.Book;
import model.Reserve;

/**
 *
 * @author arsen
 */
public class ChangeReserveCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
          String idReserve = request.getParameter("idReserve");
        String act = request.getParameter("act");
        
         DaoFactory factory = DaoFactory.getInstance();
         BookDao books=factory.getBookDao();
           ReserveDao reserves=factory.getReserveDao();
        
          
           
     if(request.getParameter("censelId")!=null){             
          String id=request.getParameter("censelId");
           request.setAttribute("message", "Reserve was censeled");
          reserves.updateByCreteria("refused", id);
      } else if(request.getParameter("applyId")!=null)
      {
           String id=request.getParameter("applylId");
          Reserve reserve=reserves.getByCreteria(id);
       Book book=books.getByCreteria(reserve.getBookTitle());
        if(book.getQty()<1){
                reserves.updateByCreteria("refused",idReserve);    
                    request.setAttribute("message", "Reserve was censeled");
           }else {
         if (reserve.getAnswer().equals("wiev for give to hend")){
              reserves.updateByCreteria("give to hend",id);   
          request.setAttribute("message", "book will be given by hand");
           }else {
               reserves.updateByCreteria("give in reading room",id);
          request.setAttribute("message", "book will be given in reading room");
             
              }
            books.updateByCreteria(Integer.toString(book.getQty()-1), book.getTitle());
      }

            
    }
    List<Reserve> listReserves = reserves.getAll();
                request.setAttribute("reserves", listReserves);
                 return "AdminProFile.jsp";
}
}