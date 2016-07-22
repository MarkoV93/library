/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comand;

import dao.BookDao;
import dao.DaoFactory;
import dao.GenreDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Book;
import model.Genre;

/**
 *
 * @author Marko
 */
public class AddBookCommand implements Command{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
          DaoFactory factory=DaoFactory.getInstance();
           BookDao books=factory.getBookDao();
        if(request.getParameter("add")!=null){
       String title=request.getParameter("title");
       String author=request.getParameter("author");
        String qty=request.getParameter("qty");
          String genre=request.getParameter("genre");
            
         

          if(books.getByCreteria(title)!=null){
              int newQty=books.getByCreteria(title).getQty()+Integer.parseInt(qty);
            books.updateByCreteria(Integer.toString(newQty), title);
          } else {
          Book book=new Book();
          book.setTitle(title);
          book.setAuthor(author);
          book.setQty(Integer.parseInt(qty));
          book.setGenre(genre);
      
          books.create(book);
          }
          request.setAttribute("message", "Book was added");
        }
           GenreDao genres=factory.getGenreDao();
            List<Genre> listGenre=genres.getAll();
            request.setAttribute("genres", listGenre);
        List<Book> listBooks = books.getAll();
         request.setAttribute("books", listBooks);
          return "AddBook.jsp";
    }
    
}
