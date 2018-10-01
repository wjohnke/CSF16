<!DOCTYPE html>
<html>
<head>
	<title>Create User Account</title>
	<link href="app.css" rel="stylesheet" type="text/css">
    <link href="../jquery-ui-1.11.4.custom/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <script src="../jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
    <script src="../jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
    <script>
        $(function(){
            $("input[type=submit]").button();
			
			$("#password, #confirmPass").keyup(function(){
				var password=$("#password").val();
				var confirmPass=$("#confirmPass").val();
				
				if(password.localeCompare(confirmPass) != 0){
					//$('#outputDiv').html("Passwords don't match");
					document.getElementById("confirmPass").setCustomValidity("Passwords don't match");
					//if the message is sent to blank, it will allow form to submit, otherwise won't
				}
				else{
					//$('#outputDiv').html("Passwords match");
					document.getElementById("confirmPass").setCustomValidity("");
				}
				
			} );
			
			
        });
		/*
		function checkPasswords(){
			var result=false;
			
			var password=document.getElementById("password").value;
			var confirmPass=document.getElementById("confirmPass");
			
			if(password.localeCompare(confirmPass.value)!= 0){
				//alert("Passwords don't match");
				confirmPass.setCustomValidity("Passwords don't match");
				result = false;
			}
			else{
				result=true;
			}
			
			return result;
		}
		*/
		
		
		
    </script>
</head>
<body>
    <div id="loginWidget" class="ui-widget">
        <h1 class="ui-widget-header">Create your Account</h1>
        
        <?php
            if ($error) {
                print "<div class=\"ui-state-error\">$error</div>\n";
            }
        ?>
													<!--Function will return false, form won't submit-->
        <form action="createUser.php" method="POST"> <!-- onsubmit="return checkPasswords();"> -->
            
            <input type="hidden" name="action" value="do_create">
            
            <div class="stack">
                <label for="firstName">First name:</label>
                <input type="text" id="firstName" name="firstName" class="ui-widget-content ui-corner-all" autofocus require>
            </div>
			
			<div class="stack">
                <label for="lastName">Last name:</label>
                <input type="text" id="lastName" name="lastName" class="ui-widget-content ui-corner-all" autofocus require>
            </div>
			
			<div class="stack">
                <label for="userName">User name:</label>
                <input type="text" id="userName" name="userName" class="ui-widget-content ui-corner-all" autofocus require>
            </div>
            
            <div class="stack">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" class="ui-widget-content ui-corner-all" require>
            </div>
			
			<div class="stack">
                <label for="confirmPass">Confirm Password:</label>
                <input type="password" id="confirmPass" name="confirmPass" class="ui-widget-content ui-corner-all" require>
            </div>
			
			<div class="stack">
                <label for="birthday">Birthday:</label>
                <input type="date" id="birthday" name="birtday" class="ui-widget-content ui-corner-all" require>
            </div>
			
			<div class="stack">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" class="ui-widget-content ui-corner-all" require>
            </div>
            
            <div class="stack">
                <input type="submit" value="Submit">
            </div>
        </form>
		
		<br>
		
		<div id="outputDiv">
		
		</div>
		
		
    </div>
</body>
</html>