/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Book;
import model.Genre;


public class GenreDao extends AbstractDao<Genre>{


       public GenreDao(DataSource ds){
      super(ds);
}
//        public void add(Genre g){
//         String genre=g.getGenre();
//           try {
//            PreparedStatement st = con.prepareStatement("select * from Genre where genre=?");
//            st.setString(1, genre);
//            ResultSet rs=st.executeQuery();
//            if(rs.next()){
//            return;
//            
//                
//            }else {
//               st=con.prepareStatement("insert into genre(genre) values (?)");
//               st.setString(1, genre);
//               st.execute();         
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getMessage());
//        }
//   
//     }
//        public void remove(String genre){
//          try {
//            PreparedStatement st = con.prepareStatement("DELETE FROM genre WHERE genre = '?';");
//                st.setString(1, genre);
//           } catch (SQLException ex) {
//            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println(ex.getMessage());
//        }
//        }

    @Override
    protected String getSelectQuery() {
       return "select * from genre";
    }

    @Override
    protected List<Genre> parseResultSet(ResultSet rs) throws Exception {
         List<Genre> result = new LinkedList<Genre>();
        try {
            while (rs.next()) {
               Genre b = new Genre();
                b.setGenre(rs.getString(2));
                result.add(b);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected String getUpdateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getDeleteQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Genre object) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getCreateQuery() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected String getSelectCreteria() {
          return "select* from book  inner join genre  on book.genre_id=genre.id where book.title= ?";
    }
}
