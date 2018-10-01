<!DOCTYPE html>
<?php
	
	$id='';
	$message='';
	
	$action=empty($_GET['action']) ? '': $_GET['action'];
	
	switch(action){
		case 'set':
			$message=cookie_set();
			break;
		case 'get':
			$message=cookie_get();
			break;
		case 'clear':
			$message=cookie_clear();
			break;
		default:
			$message="No action was specified<br>\n";
			$message .="The actions are set, get, and clear.<br>\n";
			$message .="The set action requires a value to be specified";
			break;
	}
	
	
	function cookie_set() {
		$cookie_value = empty($_GET['value']) ? '' : $_GET['value'];
		
		if ($cookie_value) {
			setcookie('userid', $cookie_value);
			$message = "The userid cookie was set to '$cookie_value'.";
		} 
		else {
			$message = "A request was made to set the userid cookie, but no value was provided.";
		}
		
		return $message;
	}
	
	function cookie_get() {
		$cookie_value = empty($_COOKIE['userid']) ? '' : $_COOKIE['userid'];
		
		if ($cookie_value) {
			$message = "A userid cookie was found and it has a value of '$cookie_value'.";
		} 
		else {
			$message = "A userid cookie was not found.";
		}
		
		return $message;
	}
	
	function cookie_clear() {
		setcookie('userid', '', 1);
		$message = "The userid was cleared by setting it to expire.";
		
		return $message;
	}
	
	
?>
<html>
<head>
	<title>Combo Cookie Application</title>
</head>
<body>
	<p><?php print $message; ?></p>
</body>
</html>
