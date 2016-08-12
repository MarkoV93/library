<%-- 
    Document   : AddBook
    Created on : 18 Р»РёРї. 2016, 19:35:47
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">

    <html>

        <head>
            <!--<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />-->
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>JSP Page</title>
        </head>

        <body>

            <form action="changeLanguage" method="post" style="display: inline-block">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>

            <h1><fmt:message key="addBook"/></h1>
            <form action="login" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit"class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="backToUserPage"/></button>
            </form>

            <form action="wievReserves" method="post"class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="wievReserves"/></button>
            </form>
            <form action="addBook" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="addBook"/></button>
            </form>
            <form action="wievOldReserves" method="post"class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="wievOldReserves"/></button>
            </form>
            <form action="returnBook" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="returnBook"/></button>
            </form>
            <form action="logOut"  method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="logOut"/></button>
            </form>				



            <br> ${message}
            <br>
            <div>
                <form action="addBook" method="post" class="col-sm-offset-4 col-md-offset-4 col-lg-offset-4 col-sm-4 col-md-4 col-lg-4">
                    <fmt:message key="title"/><input type="text" class="form-control" name="title"/>
                    <br>
                    <fmt:message key="author"/><input type="text" class="form-control" name="author"/>
                    <br>
                    <fmt:message key="qty"/><input type="text" class="form-control" name="qty"/>
                    <br>
                    <fmt:message key="genre"/><select name="genre" class="form-control" size="1">
                        <option selected value="select"><fmt:message key="selectGenreOfBook"/>
                            <c:forEach var="genre" items="${requestScope.genres}">
                            <option value=${genre.genre}>${genre.genre}
                            </c:forEach>
                    </select>
                    <br>
                    <button type="submit" class="btn btn-primary btn-lg btn-block" name="add" value="add"><fmt:message key="add"/></button>
                    <br>
                </form>

            </div>				
            <br>
            <div class="col-sm-11 col-md-11 col-lg-11">
                <table border="2"  class="table  table-hover  ">
                    <tr> <b>
                        <td>   <fmt:message key="title"/> </td> 
                        <td>    <fmt:message key="author"/></td> 
                        <td>    <fmt:message key="genre"/> </td> 
                        <td>    <fmt:message key="qty"/> </td>
                    </b>
                    </tr>
                    <c:forEach var="book" items="${requestScope.books}">
                        <tr>
                            <td>     ${book.title} </td> 
                            <td>     ${book.author}</td> 
                            <td>     ${book.genre.genre} </td> 
                            <td>     ${book.qty} </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>			

        </div>
    </body>

</html>
</fmt:bundle>