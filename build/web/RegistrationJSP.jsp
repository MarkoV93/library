<%-- 
    Document   : RegistrationJSP
    Created on : 14 квіт. 2016, 19:19:17
    Author     : Marko
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="registration" method="post">
            FirstName:<input type="text" name="firstName"/>
            <br>
             LastName:<input type="text" name="lastName"/>
            <br>
            Login:<input type="text" name="login"/>
            <br>
             Password:<input type="password" name="password"/>
            <br>
            <input type="submit" name="OK" value="sent"/>
        </form>
    </body>
</html>
