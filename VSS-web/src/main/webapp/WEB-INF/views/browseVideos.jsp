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
        <title>Browse Videos</title>
        <link rel="stylesheet" type="text/css" href="banner_style.css">
        
        <style>
            th.videoListHeader {
                text-align:left;
            }
        </style>
    </head>
    <body>
        <%@include file="../jspf/banner.jspf" %>
        <%@include file="../jspf/video_list.jspf" %>
    </body>
</html>
