<%-- 
    Document   : login.jsp
    Created on : 31-Dec-2012, 1:43:22 PM
    Author     : Samual Martelli
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to our On-Line Video Store!!!</h1>
        <table>
            <tr><td>
            <h2> Enter your login information below</h2></td>
            </tr>
            <tr>
            <td><input type="text" id="username" name="username"
                        placeholder="Username"/></td></tr>
            <tr><td><input type="password" id="password" name="password"
                       placeholder="Password"/></td></tr>
            <tr><td colspan="2" align="center">
            <button id="login">Login</button>
        </td></tr>
        </table>
    </body>
</html>
