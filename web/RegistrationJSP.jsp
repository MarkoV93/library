

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
 <fmt:setLocale value="${sessionScope.locale}" scope="session" />
         <fmt:bundle basename="properties.text">
<html>
    <head>
       
        	<link rel="stylesheet" href="css/style.css" media="screen" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="registration"/></title>
    </head>
    <body>
<form action="changeLanguage" method="post">       
      <button type="submit"><fmt:message key="changeLanguage"/></button>
            </form>
            <div id="login-form">
      <h1><fmt:message key="registration"/></h1>
        <fieldset>
            <form action="registration" method="post">
            <fmt:message key="firstName"/><input type="text" name="firstName"/>
            <br>
             <fmt:message key="lastName"/><input type="text" name="lastName"/>
            <br>
            <fmt:message key="login"/><input type="text" name="login"/>
            <br>
             <fmt:message key="password"/><input type="password" name="password"/>
            <br>
           <input type="submit" name="Registration" value=<fmt:message key="registration"/>>
        </form>
        <form  action="login" method="post">       
      <button type="submit"><fmt:message key="login"/></button>
            </form>
        </fieldset>
    </div> 
    </body>
</html>
         </fmt:bundle>