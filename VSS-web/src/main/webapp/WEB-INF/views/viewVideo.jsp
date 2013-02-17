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
        <title>JSP Page</title>
        <script src="http://code.jquery.com/jquery-latest.js"></script>
        <link href="http://vjs.zencdn.net/c/video-js.css" rel="stylesheet">
        <script src="http://vjs.zencdn.net/c/video.js"></script>
    </head>
    <body>
        <%@include file="/WEB-INF/jspf/banner.jspf" %>
            
        <video id="my_video_1" class="video-js vjs-default-skin" controls
               preload="auto" width="640" height="264"
               data-setup="{}">
            <!--source src="${vidLocation_mp4}" type='video/mp4'>
            <source src="${vidLocation_webm}" type='video/webm'-->
            <source src ="${vidLocation_ogg}" type='video/ogg'/>
        </video>
        <script>
            var myPlayer = _V_("my_video_1");         
            var playFunct = function(){
                myPlayer.currentTime(${currentTime});
                myPlayer.play();
            };
            myPlayer.addEvent("loadeddata", playFunct);
            
            window.onbeforeunload = function(e){
                console.log("Player Time is " + (myPlayer.currentTime()));
                $.post("viewVideo.htm", {savedTime:myPlayer.currentTime()});
                return null;
            };
        </script>
    </body>
</html>
