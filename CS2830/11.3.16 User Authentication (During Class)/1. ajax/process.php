<?php
$action = empty($_POST['action']) ? false : $_POST['action'];

switch ($action) {
	case 'login':
		$username = empty($_POST['username']) ? '' : $_POST['username'];
		$password = empty($_POST['password']) ? '' : $_POST['password'];
		if ($username=='test' && $password=='pass') {
			setcookie('userid', $username);
			$response = 'Login: Sucess';
		}
		else {
			$response = 'Login: Fail';
		}
		print $response;
		break;
	case 'get':
		$userid = empty($_COOKIE['userid']) ? '' : $_COOKIE['userid'];
		if ($userid=='test') {
			$response = 'This is protected content for test';
		}
		else {
			$response = "Either there is no content for your userid or you are not logged in.";
		}
		print $response;
		break;
	case 'logout':
		setcookie('userid', '', 1);
		print 'Logged out';
		break;
	default: 
		print "Error, default case"; 
		break; 
}
?>
