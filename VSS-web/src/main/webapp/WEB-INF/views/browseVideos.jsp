<%-- 
    Document   : browseVideos
    Created on : Jan 25, 2013, 11:55:53 AM
    Author     : Mark
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Videos</title>
        
        <style>
            th.videoListHeader {
                text-align:left;
            }
        </style>
    </head>
    <body>
        <h1>Browse Videos</h1>
        <table>
            <tr>
                <th class="videoListHeader">Name</th>
                <th class="videoListHeader">Description</th>
                <th class="videoListHeader">Rating</th>
                <th class="videoListHeader">Genre</th>
            </tr>
            <c:forEach var="videoInfo" items="${videoInfoList}">
                <tr>
                    <td><c:out value="${videoInfo.getName()}"/></td>
                    <td><c:out value="${videoInfo.getDescription()}"/></td>
                    <td><c:out value="${videoInfo.getScreenRating()}"/></td>
                    <td><c:out value="${videoInfo.getGenre()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
