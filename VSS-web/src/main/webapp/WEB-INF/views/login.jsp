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
        <form action="login.htm" value="/login" method="post"/>
        <fieldset>
            <legend>Account Login</legend>
            <table>
                <tr>
                    <td>Username</td>
                    <td>
                        <input type="text" id="username" name="username"
                               placeholder="Username"/></td>
                </tr>
                <tr>
                    <td>Password</td>
                    <td>
                        <input type="password" id="password" name="password"
                               placeholder="Password"/></td>
                </tr>
                <tr><td colspan="2" align="center">
                        <button id="login">Login</button>
                    </td></tr>
            </table>
        </fieldset>
    </form>
</body>
</html>
