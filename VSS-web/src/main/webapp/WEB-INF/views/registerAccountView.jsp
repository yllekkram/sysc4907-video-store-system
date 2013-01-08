<%-- 
    Document   : account
    Created on : 10-Dec-2012, 6:20:19 PM
    Author     : Samual
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>Video Store  - Account Manager | team33 </title>
</head>
<body>
 
<h2>Account Manager</h2>
 
<form:form method="post" action="registerAccountView/add.htm" commandName="account">
 
    <table>
        <tr>
            <td><form:label path="name">Name</form:label></td>
            <td><form:input path="name" /></td> 
        </tr>
        <tr>
            <td><form:label path="password">Password</form:label></td>
            <td><form:input path="password" /></td>
        </tr>
        <tr>
            <td colspan="2">
                <input type="submit" value="Submit"/>
            </td>
        </tr>
    </table>  
</form:form>
 
     
<h3>Accounts</h3>
<c:if  test="${!empty accountList}">
<table class="data">
<tr>
    <th>Account (e-Mail)</th>
    <th>Password</th>
    <th>&nbsp;</th>
</tr>
</table>
</c:if>
 
</body>
</html>
