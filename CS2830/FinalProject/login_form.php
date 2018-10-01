<!DOCTYPE html>


<html>
<head>
    <meta charset="utf-8">
    <title>Final Project</title>
	
	<script src="jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
	<link type="text/css" href="jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet">
	<script src="jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
	<link type="text/css" href="style.css" rel="stylesheet">

    <style>
        .createForm{
            margin: 20px auto;
            padding:10px;
            border: 2px solid black;
            border-radius: 10px;
			width:300px;
        }

    </style>
	
	<script>
		$(function(){
            $("input[type=submit]").button();
        });
	</script>
        
</head>
    
<body>
    <h1 style="text-align:center">
        Login
    </h1>
		<?php
			if($error) {
				print "$error";
			}
			if($query) {
				print "$query";
			}
		?>
    <div class="createForm">
        <form action="login.php" method="POST">
            
        <input type="hidden" name="todo" value="login">

        <div class="data">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" autofocus require>
        </div>

        <div class="data">
            <label for="password">Password:</label>
            <input type="text" id="password" name="password"  autofocus require>
        </div>

        <div class="data">
            <input type="submit" value="Submit">
        </div>

        </form>
    </div>
        
    
</body>
</html>