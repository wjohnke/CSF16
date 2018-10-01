<!DOCTYPE html>


<html>
<head>
    <meta charset="utf-8">
    <title>Final Project</title>
	

    <style>
        .createForm{
            margin: 10px auto;
            padding:10px;
            border: 2px solid black;
            border-radius: 10%;
        }

    </style>
        
</head>
    
<body>
    <h1 style="text-align:center">
        Login
    </h1>
		<?php
			if($error) {
				print "$error";
			}
		?>
    <div class="createForm">
        <form action="login.php" method="POST">
            
        <input type="hidden" id="action" value="login">

        <div class="data">
            <label for="username">First name:</label>
            <input type="text" id="username" name="username" autofocus require>
        </div>

        <div class="data">
            <label for="password">Last name:</label>
            <input type="text" id="password" name="password"  autofocus require>
        </div>

        <div class="data">
            <input type="submit" value="Submit">
        </div>

        </form>
    </div>
        
    
</body>
</html>