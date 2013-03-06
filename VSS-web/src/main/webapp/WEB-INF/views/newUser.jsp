<%-- 
    Document   : newUser
    Created on : Mar 6, 2013, 1:06:17 AM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User</title>
    </head>
    <body>
        <h1>New User</h1>
        <form:form method="post" action="add" commandName="user">
            <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" /></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email Address</form:label></td>
                    <td><form:input path="email" /></td>
                </tr>
                <tr>
                    <td><form:label path="passwordHash">Password Hash</form:label></td>
                    <td><form:input path="passwordHash" /></td>
                </tr>
                <tr>
                    <td><form:label path="activated">Activated?</form:label></td>
                    <td><form:checkbox path="activated" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit" />
                    </td>
                </tr>
            </table>
        </form:form>
    </body>
</html>
