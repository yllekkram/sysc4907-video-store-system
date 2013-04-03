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
        <c:if test="${not empty Frame}">
            <form action="/VSS-web/orderVideoView">
                <input type="submit" value="Checkout"/>
            </form>
        </c:if>
        <table border="1">
            <tr>
                <th>Video</th>
                <th>Price</th>
                <th>Status</th>
                <th></th>
            </tr>
            <c:forEach items="${Frame}" var="videoItem">
                <tr>
                    <td><a href="/VSS-web/browseView/${videoItem.getKey().getVideoId()}.htm"><c:out value="${videoItem.getVideoInfo().getTitle()}"/></a></td>
                    <td>
                        <c:choose>
                            <c:when test = "${videoItem.getKey().getIsRented() eq true}">
                                ${videoItem.getVideoInfo().getRentalPrice()}
                            </c:when>
                            <c:otherwise>
                                ${videoItem.getVideoInfo().getPurchasePrice()}
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test = "${videoItem.getKey().getIsRented() eq true}">
                                Rented
                            </c:when>
                            <c:otherwise>
                                Purchase
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test = "${videoItem.getKey().getIsRented() eq true}">
                                <form action="/VSS-web/shoppingCartView/rental/delete/${videoItem.getKey().getVideoId()}.htm" method="post">
                                    <input type="hidden" name="id" />
                                    <input type="submit" value="delete"/>
                                </form>
                            </c:when>
                            <c:otherwise>
                                <form action="/VSS-web/shoppingCartView/purchase/delete/${videoItem.getKey().getVideoId()}.htm" method="post">
                                    <input type="hidden" name="id" />
                                    <input type="submit" value="delete"/>
                                </form>
                            </c:otherwise>
                        </c:choose>

                    </td>
                </tr>
            </c:forEach>
            <tr>
                <td>Total Cost</td>
                <td>${TotalCost}</td>
            </tr>
        </table>

    </body>
</html>
