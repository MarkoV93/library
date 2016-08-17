<%-- 
    Document   : Finding
    Created on : 17.07.2016, 14:29:16
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
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="findAndReserve"/></title>
        </head>
        <body>
            <!--button for change language-->
            <form action="changeLanguage" method="post">       
                <button type="submit"class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <!--tag for showing active user reserves-->
            <myt:MyRes login="${login}"/>
            <h1><fmt:message key="findAndReserve"/></h1>    
            <!--user menu buttons-->
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


            <!--form for finding books-->
            <fieldset style="width: 40%">
                <fmt:message key="findingText"/>
                <div class="col-sm-offset-2 col-md-offset-2 col-lg-offset-2 col-sm-10 col-md-10 col-lg-10">
                    <form action="reserve" method="post">
                        <br>
                        ${message}
                        <br>

                        <fmt:message key="title"/><input type="text" class="form-control"  name="title"/>
                        <br>
                        <button value="Reserve" name="Reserve" type="submit" class="btn btn-primary "><fmt:message key="reserve"/></button>
                        <br>
                    </form>
                    <br>
                    <form action="finding" method="post">
                        <select name="genre"  class="form-control" size="1">
                            <option selected value="celect"> <fmt:message key="selectGenreOfBook"/>
                                <c:forEach var="genre" items="${requestScope.genres}">
                                <option value="${genre.genre}">${genre.genre}
                                </c:forEach>
                        </select><br>
                        <button value="Find by genre" name="Find by genre" type="submit" class="btn btn-primary  btn-lg btn-block"><fmt:message key="findByGenre"/></button>

                        <br>
                        <select name="author"  class="form-control" size="1">
                            <option selected value="celect"><fmt:message key="selectAuthorOfBooks"/>
                                <c:forEach var="author" items="${requestScope.authors}">
                                <option value="${author}">${author}
                                </c:forEach>
                        </select><br>
                        <button value="Find by author" name="Find by author" type="submit" class="btn btn-primary  btn-lg btn-block"><fmt:message key="findByAuthor"/></button>

                    </form>
                </div>
            </ul>

        </fieldset>
    </body>
</html>
</fmt:bundle>
