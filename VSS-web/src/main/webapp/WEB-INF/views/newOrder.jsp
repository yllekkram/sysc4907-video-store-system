<%-- 
    Document   : newOrder
    Created on : Mar 4, 2013, 10:58:15 PM
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New Order</title>
    </head>
    <body>
        <form:form method="post" action="create.htm">
            <table>
                <tr>
                    <td><form:label path="videotitle">Video Title</form:label></td>
                    <td><form:input path="videotitle"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <form:radiobutton path="orderType" value="purchase" />Purchase <br/>
                        <form:radiobutton path="orderType" value="rental" /> Rental
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Confirm" />
                    </td>
                </tr>
            </table>
            <input type="hidden" name="videoid" value="${param.videoid}" />
        </form:form>
    </body>
</html>
