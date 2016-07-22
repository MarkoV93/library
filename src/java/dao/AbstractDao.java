/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Marko
 */
public abstract class AbstractDao<T> {
Connection con;
    DataSource ds;

    public AbstractDao(DataSource ds) {
        this.ds = ds;
    }

    public List<T> getAll() throws Exception {
        con=ds.getConnection();
        List<T> list;
        String sql = getSelectQuery();
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

    public boolean create(T object) throws Exception {
con=ds.getConnection();
        T persistInstance;
        
        // Добавляем запись
        String sql = getCreateQuery();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            prepareStatementForInsert(statement, object);
             return statement.execute();
    } catch(Exception e){
        throw new Exception(e);
        } finally{
            con.close();
        }
    }
    

    public T getByCreteria(String criteria) throws Exception {
        List<T> list;
        con=ds.getConnection();
        String sql = getSelectCreteria();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, criteria);
            ResultSet rs = statement.executeQuery();
            list = parseResultSet(rs);
        } catch (Exception e) {
            throw new Exception(e);
        } finally {
            con.close();
        }
        if (list == null || list.size() == 0) {
            return null;
        }
        if (list.size() > 1) {
            throw new Exception("Received more than one record.");
        } 
        return list.iterator().next();
    }

    public boolean updateByCreteria(String change,String criteriaForFind) throws Exception {
        String sql = getUpdateQuery();
          con=ds.getConnection();
        try (PreparedStatement statement = con.prepareStatement(sql);) {
            statement.setString(1, change);
            statement.setString(2, criteriaForFind);
            int count = statement.executeUpdate();
            if(count ==0){
                return false;
            } else
            if (count != 1) {
                throw new Exception("On update modify more then 1 record: " + count);
             
            }
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
           return true;
    }

    public boolean deleteByCreteria(String  criteria) throws Exception {
        String sql = getDeleteQuery();
          con=ds.getConnection();
        try (PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, criteria);
            int count = statement.executeUpdate();
             if(count ==0){
                return false;
            } else if (count != 1) {
                throw new Exception("On delete modify more then 1 record: " + count);
            }
            statement.close();
        } catch (Exception e) {
            throw new Exception(e);
        } finally{
            con.close();
        }
        return true;
    }

   protected abstract String getSelectQuery() ;

   protected abstract List<T> parseResultSet(ResultSet rs) throws Exception ;

    protected abstract String getUpdateQuery() ;

 

    public abstract String getDeleteQuery() ;



    public abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws Exception;

    public abstract String getCreateQuery();

    protected abstract String getSelectCreteria() ;


      
    
      
    
}
