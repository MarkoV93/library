<%-- 
    Document   : BooksByAuthor
    Created on : 19 лип. 2016, 17:17:25
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">
    <html>
        <head>

            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <title>JSP Page</title>
        </head>
        <body>
            <!--button for change language-->
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <h1>${author}  </h1>

            <ul>
                <!--books table with the relevant author-->
                <div class="col-sm-11 col-md-11 col-lg-11">
                    <table border="2" class="table table-hover">
                        <tr > <b>
                            <td> <fmt:message key="title"/> </td> 
                            <td> <fmt:message key="author"/></td> 
                            <td> <fmt:message key="genre"/> </td> 
                            <td>  <fmt:message key="qty"/> </td>
                            <td></td>
                        </b>
                        </tr>
                        <c:forEach var="book" items="${requestScope.books}">
                            <tr>
                                <td>     ${book.title} </td> 
                                <td>     ${book.author}</td> 
                                <td>     ${book.genre.genre} </td> 
                                <td>     ${book.qty} </td>
                            <form action="reserve" method="post">
                                <td><button class="btn btn-primary" value="${book.title}" name="title" type="submit" class="btn btn-default"><fmt:message key="reserve"/></button></p></td>
                            </form>
                            </tr>
                        </c:forEach>
                              <!--usermenu buttons-->
                        <form action="login" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="backToUserPage"/></button>
                        </form>
                        <form action="myOldReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="myOldReserves"/></button>
                        </form>
                        <form action="finding" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="findAndReserve"/></button>
                        </form>
                        <form action="myReserves" method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="myRreserves"/></button>
                        </form>
                        <form action="logOut"  method="post">
                            <button type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="logOut"/></button>
                        </form>

                </div>
            </ul>
        </body>
    </html>
</fmt:bundle>