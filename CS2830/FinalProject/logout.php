<?php
	if(!session_start()) {
		header("Location: index.php");
		exit;
	}
	
	//Just destroy existing session
	
	$_SESSION=array();
	
	if (ini_get("session.use_cookies")) {
		$params = session_get_cookie_params();
		setcookie(session_name(), '', 1,
			$params["path"], $params["domain"],
			$params["secure"], $params["httponly"]
		);
	}
	
	session_destroy();
	
	header("Location: login.php");
	exit;

?>