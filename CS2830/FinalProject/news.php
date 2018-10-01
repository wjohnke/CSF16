<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>News of the Day</title>
	       
		<script src="jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
        <link type="text/css" href="jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet">
        <script src="jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
		<style>
			#pictureContainer{
				font-family: Arial;
				width:1000px;
				height:auto;
				background-color:lightblue;
				margin:20px auto;
				border:2px solid blue;
				border-radius:3px;
				box-shadow: -15px 10px 10px #888888;
				padding:10px;
			}
			#eachStory{
				font-size:13px
			}
		
		</style>
        <script>
            $(document).ready(function() {
				var data='';
				
                $("#dataList").html("Loading pictures and content...");
                
                var url = "https://api.nytimes.com/svc/topstories/v2/home.json";
                url += '?' + $.param({
                  'api-key': "d6d16033c9b74d2eb4e5ed8811d7cda4"
                });
                $.ajax({
                  url: url,
                  method: 'GET',
                }).done(function(result) {
					var i, counter=0;
					for(i in result.results){
						counter+=1;
						data+="<li>"+"Article "+(counter)+": "+result.results[i].abstract;
						data+="<br>URL: "+result.results[i].url+"<br><br></li>";
					}
					$("#dataList").html(data);
					
                }).fail(function(err) {
                  throw err;
                });
                
				
                
            } );
        
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
	
		<h1 style="text-align:center">Top NewYorkTimes Newsstories from Today</h1>
		<a style="text-size:8px" href="logout.php">Logout</a>
		<div id="pictureContainer">
			<div id="eachStory">
              <ul style="border-bottom-width:20px" id="dataList"></ul>
			<div>
        </div>
		
		
		
	
	</body>
</html>