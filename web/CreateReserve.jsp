<%-- 
    Document   : CreateReserve
    Created on : 17.07.2016, 15:41:37
    Author     : arsen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="myt" uri="WEB-INF/messageAboutReserve.tld"%>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">

    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <title>Creating Reserve</title>
        </head>


        <body>
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <myt:MyRes login="${login}"/>
            <h1><fmt:message key="reserve"/></h1>
          <form action="login" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                    <button type="submit"class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="backToUserPage"/></button>
                </form>
           
            
                <form action="myOldReserves" method="post"  class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                    <button type="submit"class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="myOldReserves"/></button>
                </form>
          
           
                <form action="finding" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                    <button type="submit"class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="findAndReserve"/></button>
                </form>
            
        
        
            <form action="myReserves" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit"class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="myRreserves"/></button>
            </form>
       
       
            <form action="logOut" method="post" class="col-sm-2 col-md-2 col-lg-2" style="float:left; padding:0;">
                <button type="submit" class="btn btn-primary col-sm-12 col-md-12 col-lg-12"><fmt:message key="logOut"/></button>
            </form>
            <br>
            <br>
            <fieldset class="col-sm-offset-1 col-md-offset-1 col-lg-offset-1">
                            ${message}
                            <form action="reserve" method="post" class="radio">
                                <input type="radio" name="act" value="wiev for give to hend"><fmt:message key="getByHend"/><br>
                                <input type="radio" name="act" value="wiev for give to reading room"><fmt:message key="getInReadingRoom"/><br>
                                <br>
                                <button value="Reserve" name="Reserve" type="submit" class="btn btn-primary col-sm-2 col-md-2 col-lg-2"><fmt:message key="reserve"/></button>
                            </form>

                        </fieldset>

                <ul>

                    <table border="2" class="table table-hover">
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
                     


                </ul>
         
        </body>
    </html>
</fmt:bundle>
