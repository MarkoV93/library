
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<fmt:setLocale value="${sessionScope.locale}" scope="session" />
<fmt:bundle basename="com.univ.vintoniuk.properties.text">
    <html>
        <head>
            <link rel="stylesheet" href="css/bootstrap.min.css" media="screen" type="text/css" />
            <link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title><fmt:message key="authorization"/></title>
        </head>
        <body>
            <!--button for change language-->
            <form action="changeLanguage" method="post">       
                <button type="submit" class="btn btn-primary"><fmt:message key="changeLanguage"/></button>
            </form>
            <div class="container col-sm-12 col-md-12 col-lg-12">
                <h1><fmt:message key="authorization" /></h1>
                <fieldset>
                    <!--form for login-->
                    <form action="login" method="post" class="col-sm-offset-4 col-md-offset-4 col-lg-offset-4 col-sm-4 col-md-4 col-lg-4">
                        <br>
                        ${message}
                        <br> 
                        <fmt:message key="login"/><input type="text" class="form-control" name="login" />
                        <br>
                        <fmt:message key="password"/><input type="password" class="form-control" name="password"/>
                        <br>
                        <input type="submit" class="btn btn-primary  btn-lg btn-block" name="OK" value=<fmt:message key="enter"/>>
                    </form>

                    <form action="registration" method="post" class="col-sm-offset-4 col-md-offset-4 col-lg-offset-4 col-sm-4 col-md-4 col-lg-4">
                        <input type="submit" class="btn btn-primary  btn-lg btn-block" name="Registration" value=<fmt:message key="registration"/>>
                    </form>
                </fieldset>
            </div>  
        </body>
    </html>

</fmt:bundle>