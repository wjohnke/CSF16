<?php
/*
	We covered 3 ways to clear cookies:
		1. Closing the browser (session cookies get deleted)
		2. Manually clearing the cache (all cookies get deleted)
		3. Setting a cookie's expiration date to some time in the past
*/
	// This will set the cookie's expiration time to 3600 seconds (or 1 hour) in the past, clearing the cookie.
	//setcookie('flavor', '', time() - 3600);
	
	// The following method sets the expiration date for the cookie to January 1st, 1970 at 0:00:01 AM
	setcookie('flavor', '', 1);
	
	// Print confirmation
	print "The cookie was cleared.<br>\n";
?>