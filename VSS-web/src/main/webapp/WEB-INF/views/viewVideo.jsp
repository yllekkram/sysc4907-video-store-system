<%-- 
    Document   : viewVideo
    Created on : Jan 26, 2013, 2:38:15 PM
    Author     : Caleb
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="banner_style.css">
        <title>JSP Page</title>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link href="http://vjs.zencdn.net/c/video-js.css" rel="stylesheet">
        <script src="http://vjs.zencdn.net/c/video.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/banner.jspf" %>
        <br>
            
        <video id="my_video_1" class="video-js vjs-default-skin" controls
               preload="auto" width="640" height="264" poster="my_video_poster.png"
               data-setup="{}">
            <source src="${vidLocation_mp4}" type='video/mp4'>
            <source src="${vidLocation_webm}" type='video/webm'>
        </video>
        <form:form id="videoForm" action="viewVideo.htm" method="post" modelAttribute="savedTime">
            <input id="input" type="submit" value="Submit" onclick=""/>
        </form:form>
        <script>
            var myPlayer = _V_("my_video_1");
            window.onbeforeunload = function(e){
                console.log("Current time variable : ${currentTime.integer}");
                ${savedTime.integer} = (myPlayer.currentTime() + 15);
                console.log("Current time variable : ${currentTime.integer}");
                console.log("Saved Time : ${savedTime.integer}");
                console.log("Player Time is " + (myPlayer.currentTime() + 15));
                document.getElementById("videoForm").submit();
                var message = "Hello World";
                if (e){
                    e.returnValue = message;
                }
                return message;
            };
        </script>
    </body>
</html>
