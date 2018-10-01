<?php
	// The predefined $_COOKIE array contains our cookies
	// Reference: http://php.net/manual/en/reserved.variables.cookies.php
	
	// Get the value of a cookie named 'flavor'
	//$flavor = $_COOKIE['flavor'];
	
	// A better way to get the cookie's value
	$flavor = empty($_COOKIE['flavor']) ? "The cookie 'flavor' does not exist." : $_COOKIE['flavor'];
	
	// Print flavor's value
	print "Flavor = $flavor<br>\n";
?>