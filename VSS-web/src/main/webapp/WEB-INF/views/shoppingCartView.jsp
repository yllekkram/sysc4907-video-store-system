<%-- 
    Document   : shoppingCartView
    Created on : Feb 21, 2013, 2:56:20 PM
    Author     : Caleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <h1>Shopping Cart</h1>
        <c:if test="${not empty VideoList}">
            <form action="/VSS-web/orderVideoView">
                <input type="submit" value="Checkout"/>
            </form>
        </c:if>
        <table border="1">
            <tr>
                <th>Video</th>
                <th>Status</th>
            </tr>
            <c:forEach items="${VideoList}" var="videoItem">
                <tr>
                    <td><a href="/VSS-web/viewVideo.htm/${videoItem.id}"><c:out value="${videoItem.title}"/></a></td>
                    <td>
                        <form action="/VSS-web/shoppingCartView.htm" method="post">
                            <input type="hidden" name="id" value="${videoItem.id}"/>
                            <input type="submit" value="delete"/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
