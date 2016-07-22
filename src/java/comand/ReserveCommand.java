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
import dao.UserDao;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;
import model.Genre;
import model.Reserve;

/**
 *
 * @author arsen
 */
public class ReserveCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
         HttpSession hs = request.getSession(true);
        String bookTitle=(String) hs.getAttribute("titleBookforReserve");
     
  DaoFactory factory = DaoFactory.getInstance();
     BookDao books=factory.getBookDao();
         String userLogin=(String) hs.getAttribute("login");
        String action = request.getParameter("act");
         String titleBook = request.getParameter("title");
         if (books.getByCreteria(titleBook) != null) {
                
                Book book = books.getByCreteria(titleBook);
                hs.setAttribute("titleBookforReserve", titleBook);
                request.setAttribute("book", book);
                return "/CreateReserve.jsp";
            } else if (books.getByCreteria(titleBook) == null){
                 ResourceBundle labels = ResourceBundle.getBundle("properties.text", (Locale)hs.getAttribute("locale"));  
                   request.setAttribute("message", labels.getString("thereIsNoOneBook"));

                   GenreDao genres=factory.getGenreDao();
              List<Genre> listGenres=genres.getAll();
              request.setAttribute("genres", listGenres);
               List<String> listAuthors=books.getAuthorsOfBooks();
              request.setAttribute("authors", listAuthors);
              if(bookTitle == null){
                return "/Finding.jsp";
              } else if(bookTitle != null && action==null){
                   Book book = books.getByCreteria(bookTitle);
                   request.setAttribute("book", book);
                   return "/CreateReserve.jsp";
              }
            
         else{
        Reserve reserve=new Reserve();
        reserve.setBookTitle(bookTitle);
        reserve.setAnswer(action);
        reserve.setUserLogin(userLogin);

        ReserveDao reserves = factory.getReserveDao();
        reserves.create(reserve);
        BookDao bookDao=factory.getBookDao();
        Book book = books.getByCreteria(bookTitle);
                   request.setAttribute("book", book);
               
                   request.setAttribute("message", labels.getString("bookReserved"));
                   return "/CreateReserve.jsp";
    }
    }
         return null;
    }
}

