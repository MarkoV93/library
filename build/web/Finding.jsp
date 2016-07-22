<%-- 
    Document   : Finding
    Created on : 17.07.2016, 14:29:16
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
        <title><fmt:message key="findAndReserve"/></title>
    </head>
    <body>
         <form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
        <h1><fmt:message key="findAndReserve"/></h1>    
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
        <form action="logOut" method="post">
            <button type="submit"><fmt:message key="logOut"/></button>
            </form>
                </div>
     
              <fieldset>
              <fmt:message key="findingText"/>
              <form action="reserve" method="post">
            <br>
            ${message}
            <br>
            
            <fmt:message key="title"/><input type="text" name="title"/>
            <br>
            <button value="Reserve" name="Reserve" type="submit"><fmt:message key="reserve"/></button>
            <br>
              </form>
             <form action="finding" method="post">
             <fmt:message key="genre"/><select name="genre" size="1">
                <option selected value="celect"> <fmt:message key="selectGenreOfBook"/>
                    <c:forEach var="genre" items="${requestScope.genres}">
                    <option value=${genre.genre}>${genre.genre}
                    </c:forEach>
            </select><br>
         <button value="Find by genre" name="Find by genre" type="submit"><fmt:message key="findByGenre"/></button>
         
             <br>
              <fmt:message key="author"/><select name="author" size="1">
                <option selected value="celect"><fmt:message key="selectGenreOfBooks"/>
                    <c:forEach var="author" items="${requestScope.authors}">
                    <option value=${author}>${author}
                    </c:forEach>
            </select><br>
          <button value="Find by author" name="Find by author" type="submit"><fmt:message key="findByAuthor"/></button>
            
        </form>
              </fieldset>
     
         

             </body>
</html>
         </fmt:bundle>
