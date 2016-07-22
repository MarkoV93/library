/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.BookDao;
import dao.DaoFactory;
import dao.GenreDao;
import dao.ReserveDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Genre;
import model.Reserve;

/**
 *
 * @author arsen
 */
public class WievReservesCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
             String applyId = request.getParameter("applylId" );
        String act = request.getParameter("act");
        
         DaoFactory factory = DaoFactory.getInstance();
         BookDao books=factory.getBookDao();
           ReserveDao reserves=factory.getReserveDao();
        
          
           
     if(request.getParameter("censelId")!=null){
//          if(reserves.getByCreteria(id))
             
          String id=request.getParameter("censelId");
           request.setAttribute("message", "Reserve was censeled");
          reserves.updateByCreteria("refused", id);
      } if(applyId!=null){
           String id=request.getParameter("applylId");
          Reserve reserve=reserves.getByCreteria(id);
       Book book=books.getByCreteria(reserve.getBookTitle());
        if(book.getQty()<1){
                reserves.updateByCreteria("refused",id);    
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
    List<Reserve> listReserves = reserves.getAllWievingRes();
                request.setAttribute("reserves", listReserves);
                 return "WievReserves.jsp";
}
//          DaoFactory factory = DaoFactory.getInstance();
//          ReserveDao reserves=factory.getReserveDao();
//          if(request.getParameter("Change reserve")!=null){
//          String idReserve = request.getParameter("idReserve");
//        String act = request.getParameter("act");
//         BookDao books=factory.getBookDao();
//           
//           Reserve reserve=reserves.getByCreteria(idReserve);
//           Book book=books.getByCreteria(reserve.getBookTitle());
//           
//     
//           if(book.getQty()<1){
//               act="refused";
//                reserves.updateByCreteria(act,idReserve);    
//                  
//           }else {
//         reserves.updateByCreteria(act,idReserve);
//           books.updateByCreteria(Integer.toString(book.getQty()-1), book.getTitle());
//            
//           }
//          }
//                        List<Reserve> listReserves = reserves.getAllWievingRes();
//                request.setAttribute("reserves", listReserves);
//        return "WievReserves.jsp";
////        }
//    }
    
}
