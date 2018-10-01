

<html id="doc">
    <head>
        <meta charset="utf-8">
        <title>Relax</title>
		<script src="jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <link type="text/css" href="jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet">
        <script src="jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
        
        <link type="text/css" href="style.css" rel="stylesheet">
        
        <style>
            background-color: blue;
            
        </style>
        
        <script>
            function enter(){
                document.location.href="login_form.php";
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
	
	
		<h1 style="text-align: center">Relax</h1>
		
		<a style="text-size:8px" href="logout.php">Logout</a>
        <div id="video" style="box-shadow:-10px -10px 10px #888888">
			<div style="margin:10px auto">
				<iframe width="854" height="480" 
					src="https://www.youtube.com/embed/vGbRxR03jV8?autoplay=1" 
					frameborder="0" allowfullscreen>
				</iframe>
			</div>
				
            
        </div>
		
		<div id="photoViewer" class="group">
			<div class="photo">
				<img src="get0.php" alt="image0">
			</div>
			<div class="photo">
				<img src="get1.php" alt="Image1">
			</div>
			<div class="photo">
				<img src="get2.php" alt="Image2">
			</div>
		</div>
        
    
    </body>
</html>