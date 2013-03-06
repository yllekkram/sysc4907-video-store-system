<%-- 
    Document   : editUser
    Created on : Mar 6, 2013, 8:30:02 AM
    Author     : Mark
--%>

<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit User - ${user.name}</title>
    </head>
    <body>
        <h1>Editing ${user.name}</h1>
        <form:form method="post" action="update" commandName="user">
            <table>
                <tr>
                    <td><form:label path="name">Name</form:label></td>
                    <td><form:input path="name" value="${user.name}" /></td>
                </tr>
                <tr>
                    <td><form:label path="email">Email Address</form:label></td>
                    <td><form:input path="email" value="${user.email}" /></td>
                </tr>
                <tr>
                    <td><form:label path="passwordHash">Password Hash</form:label></td>
                    <td><form:input path="passwordHash" value="${user.passwordHash}" /></td>
                </tr>
                <tr>
                    <td><form:label path="activated">Activated?</form:label></td>
                    <td><form:checkbox path="activated" value="${user.activated}" /></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit" />
                    </td>
                </tr>
            </table>
            <input type="hidden" name="id" value="${user.id}" />
        </form:form>
    </body>
</html>
