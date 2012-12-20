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
 
<form:form method="post" action="add.html" commandName="account">
 
    <table>
    <tr>
        <td><form:label path="name"><spring:message code="label.name"/></form:label></td>
        <td><form:input path="name" /></td> 
    </tr>
    <tr>
        <td><form:label path="password"><spring:message code="label.password"/></form:label></td>
        <td><form:input path="lastname" /></td>
    </tr>
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.saveaccount"/>"/>
        </td>
    </tr>
</table>  
</form:form>
 
     
<h3>Accounts</h3>
<c:if  test="${!empty accountList}">
<table class="data">
<tr>
    <th>Name</th>
    <th>Email</th>
    <th>Salt</th>
    <th>&nbsp;</th>
</tr>
<c:forEach items="${accountList}" var="account">
    <tr>
        <td>${account.name} </td>
        <td>${account.email}</td>
        <td>${account.salt}</td>
        <td><a href="remove/${account.id}">remove</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
 
</body>
</html>
