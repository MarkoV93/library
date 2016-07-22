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
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import model.Book;
import model.Reserve;
import model.User;


public class ReserveDao extends AbstractDao<Reserve>{
  
    public ReserveDao(DataSource ds) {
        super(ds);
    }
    public List<Reserve> getAllWievingRes() throws Exception {
        List<Reserve> list;
        String sql = "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where answer.id<3";

            list = this.getAllForCommand(sql);

        return list;
    }
    private List<Reserve> getAllForCommand(String sql) throws  Exception {
                List<Reserve> list;
          con=ds.getConnection();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
        return list;
    }
    

      public List<Reserve> getAllReservsForReturn() throws Exception {
        List<Reserve> list;
       
        String sql = "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where answer.id<5 and answer.id>2";
      
            list = this.getAllForCommand(sql);

        return list;
    }
     public List<Reserve> getAllWievedRes() throws Exception {
        List<Reserve> list;
  
        String sql = "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where answer.id>4 group by reserve.id";
      
            list = this.getAllForCommand(sql);

        return list;
    }   
          public List<Reserve> getAllOldResByLogin(String login) throws Exception {
        List<Reserve> list;
  
       String sql = "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where reserve.answer>4 and user.login='"+login+"';";
      
            list = this.getAllForCommand(sql);

        return list;
    }   
    
       public List<Reserve> getReservesByLogin(String login) throws Exception {
        List<Reserve> list;
       String sql = "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where reserve.answer<5 and user.login='"+login+"';";      
            list = this.getAllForCommand(sql);
        return list;
    }   
     
    @Override
    protected String getSelectQuery() {
        return "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where answer.id<5";
    }

    @Override
    protected List<Reserve> parseResultSet(ResultSet rs) throws Exception {
        List<Reserve> result = new LinkedList<>();
        try {
            while (rs.next()) {
                Reserve r = new Reserve();
                r.setId(rs.getInt(1));
                r.setDate(rs.getString(5));
                r.setUserLogin(rs.getString(9));
                r.setBookTitle(rs.getString(13));
               r.setAnswer(rs.getString(18));
            
                result.add(r);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return result;
    }

    @Override
    protected String getUpdateQuery() {
        Locale local = new Locale("ru","RU");
      DateFormat df = DateFormat.getDateTimeInstance (DateFormat.DEFAULT,DateFormat.DEFAULT,local); 
Date  currentDate = new Date(); 
       return "UPDATE reserve  SET answer =(select id from answer where answer=?), date='"+df.format(currentDate)+"' WHERE id = ?;";
    }

    @Override
    public String getDeleteQuery() {
          return "DELETE FROM reserve WHERE id= ?;";
    }

    @Override
    public void prepareStatementForInsert(PreparedStatement statement, Reserve r) throws Exception {
        try {

            statement.setString(1, r.getUserLogin());
            statement.setString(2, r.getBookTitle());
            statement.setString(3, r.getAnswer());


        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    @Override
    public String getCreateQuery() {
      return "INSERT INTO reserve ( user_id,book_id,answer) VALUES((select id from user where login =?),(select id from book where title = ?),(select id from answer where answer=?))";
    }

    @Override
    protected String getSelectCreteria() {
       return "select * from reserve inner join user on reserve.user_id=user.id inner join book on reserve.book_id=book.id inner join answer on reserve.answer=answer.id where reserve.id=?";
    }
}
