<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="banner_style.css">
        
        <script type="text/javascript" src="http://code.jquery.com/jquery-1.5.1.min.js"></script>
        <script type="text/javascript">
            function changeMainContentView(view){
                $('#mainContentView').load(view)
            }
        </script>
        
        <title>Video Store Home</title>
    </head>
    <body>
        <div id="headerView">
            <h1>On-Line Video Store !!!!</h1>
            <div id="bannerSearch">
                <input type="text"/>
                <input type="button" value="Search" onclick=""/>
            </div>
            <div id="bannerLogin">
                <input type="button" value="Register" onclick="changeMainContentView('registerAccountView.htm')"/>
                <input id="loginButton" type="button" value="Login" onclick="changeMainContentView('login.htm')" />
            </div>
        </div>
        
        <div id="mainView">
            <div id="mainContentView">
                <h1>DEFAULT START VIEW</h1>
            </div>
        </div>
    </body>
</html>
