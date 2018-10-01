<<html>
    <head>
        <meta charset="utf-8">
        <title>Welcome!</title>
		<script src="jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <link type="text/css" href="jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet">
        <script src="jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
        
        <link type="text/css" href="style.css" rel="stylesheet">
        
        <script>
            function relax(){
				document.location.href="relax.php";
			}
			function news() {
				document.location.href="news.php";
			}
        </script>
		
        
    </head>
    
    <body>
		<?php
			if(!session_start()) {
				header("Location: index.php");
				exit;
			}
	
			$loggedIn = empty($_SESSION['loggedin']) ? false : $_SESSION['loggedin'];
			if (!$loggedIn) {
				header("Location: login.php");
				exit;
			}
		?>
	
	
		<h1 style="text-align:center">Which do you feel like today?</h1>
		<a style="text-size:8px" href="logout.php">Logout</a>
        <div id="body">
        <div style="text-align:center; margin:100px auto">
            <button type="button" id="welcomeBtn" onclick="relax()">Relaxation</button>
            <button type="button" id="welcomeBtn" onclick="news()">News</button>
        </div>
        </div>
        
    
    </body>
</html>