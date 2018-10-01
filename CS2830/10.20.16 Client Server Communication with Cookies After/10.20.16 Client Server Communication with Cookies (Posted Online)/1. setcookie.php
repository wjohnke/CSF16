<!DOCTYPE html>
<?php
/* What is a cookie? 
		* A cookie is often used to identify a user
		* A cookie is a small file taht the server embeds on the user's computer
		* Each time the same computer requests a page with a browser, 
			it will send the cookie too. 
*/
	
	
	/* References: 
			1) http://php.net/manual/en/function.setcookie.php
			2) http://www.w3schools.com/php/php_cookies.asp
	*/
	

/* Note: A cookie is created with the setcookie() function.

			Syntax
				setcookie(name, value, expire, path, domain, secure, httponly);

			Only the name parameter is required. All other parameters are optional.
*/

	$cookie_name = "flavor";
	$cookie_value = "Oatmeal Cream Pie";

	// Set/Update a cookie named flavor
	// Since no expiration time is set, this cookie will expire when the browser is closed.
	setcookie($cookie_name, $cookie_value);
	
	setcookie('nick', 'loves cookies');
	
	/* The following sets the 'flavor' cookie to expire in one hour 
			(60 seconds/minute * 60 minutes/hour) */
	setcookie($cookie_name, $cookie_value, time() + 3600);
	
	
	$cookie_value = "White macadamia nut"; 
	
	
	// We sped the process up to 15 seconds to watch it work
	setcookie($cookie_name, $cookie_value, time() + 5);	

?>

<!-- NOTE: the setcookie() function must (should) appear BEFORE the <html> tag --> 



<html>
	<head>
		<title> setting cookies </title>
	</head>
	<body>
	
	<?php
		// We did this to check whether the cookie was successfully set or not
		
		if(!isset($_COOKIE[$cookie_name])) {
			echo "Cookie name '" . $cookie_name . "' is not set!";
		}
		else {
			// Print confirmation
			echo "Cookie '" . $cookie_name . "' is set! <br>"; 
			echo "Value is: " . $_COOKIE[$cookie_name]; 
		}
	?>
	
	</body>
</html>