<%-- 
    Document   : CreateReserve
    Created on : 17.07.2016, 15:41:37
    Author     : arsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
         <fmt:bundle basename="properties.text">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating Reserve</title>
    </head>
    
   
    <body>
  <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
        <h1><fmt:message key="reserve"/></h1>
                   <div style="display: inline-block;">
             <form action="login" method="post">
            <button type="submit"><fmt:message key="backToUserPage"/></button>
            </form>
            </div>
            <div style="display: inline-block;">
             <form action="myOldReserves" method="post">
             <button type="submit"><fmt:message key="myOldReserves"/></button>
            </form>
            </div>
            <div style="display: inline-block;">
              <form action="myReserves" method="post">
             <button type="submit"><fmt:message key="myRreserves"/></button>
            </form>
            </div>
             <div style="display: inline-block;">
             <form action="finding" method="post">
                 <button type="submit"><fmt:message key="findAndReserve"/></button>
            </form>
            </div>
            <div style="display: inline-block;">
        <form action="logOut" method="post">
            <button type="submit"><fmt:message key="logOut"/></button>
            </form>
                </div>
            
        <ul>
            
      <table border="1">
  <tr > <b>
            <td> <fmt:message key="title"/> </td> 
            <td> <fmt:message key="author"/></td> 
            <td> <fmt:message key="genre"/> </td> 
            <td>  <fmt:message key="qty"/> </td>
       </b>
            </tr>
            <tr>
            <td>     ${book.title} </td> 
            <td>     ${book.author}</td> 
            <td>     ${book.genre} </td> 
            <td>     ${book.qty} </td>
            </tr>
        <br>
        <fieldset>
             <fmt:message key="bookReserved"/>
      <form action="reserve" method="post">
            <input type="radio" name="act" value="wiev for give to hend"><fmt:message key="getByHend"/><br>
            <input type="radio" name="act" value="wiev for give to reading room"><fmt:message key="getInReadingRoom"/><br>
            <br>
         <button value="Reserve" name="Reserve" type="submit"><fmt:message key="reserve"/></button>
      </form>

             </fieldset>
                                

     
    </ul>
            
    </body>
</html>
         </fmt:bundle>
