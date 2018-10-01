<?php
	
	$error="";
	
	if($_SERVER['HTTPS']!=='on'){
		//Redirects if https is not on
		$error="Changed to https";
		$redirectURL='https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
		header("Location: $redirectURL");
		exit;
	}
	
	session_start();
	
	//Checks if user is logged in already, otherwise will return false
	$loggedIn=empty($_SESSION['loggedIn']) ? false : $_SESSION['loggedin'];
	
	if($loggedIn){
		//If user is already logged in, then proceed past login page
		header("Location: welcome.php");
		exit;
	}
	
	
	$todo=empty($_POST['todo']) ? '' : $_POST['todo'];
	
	if($todo=='login') {
		handle_login();
		
	}
	else{
		login_form();
	}
	
	function handle_login(){
		$username= empty($_POST['username']) ? "" : $_POST['username'];
		$password=empty($_POST['password']) ? "" : $_POST['password'];
		
		
		//Database host, base user/password, database name. Could store in config file
		$mysqli=new mysqli("localhost", "root", "will", "CS2830");
		
		if($mysqli->connect_error) {
			$error="Connection Error: " . $mysqli->connect_errno . ' ' . $mysqli->connect_error;
			require "login_form.php";
			exit;
		}
		
		
		/*Shouldn't be necessary with only valid login credentials
		of test&pass, but escapes any special characters input, just in case
		*/
		$username=$mysqli->real_escape_string($username);
		$password=sha1($mysqli->real_escape_string($password) );
		
		$query= "select id from users where username= '$username' and password= '$password'";
		
		
		$result=$mysqli->query($query);
		
		if($result) {
			$match= $result->num_rows;
			
			$result->close;
			$mysqli->close;
			
			if($match=1){
				//Set session variable loggedin, instead of cookie, equal to username
				$_SESSION['loggedin']=$username;
				header("Location: welcome.php");
				exit;
			}
			else {
				//Set error variable equal to message, to output correct error on redirect
				$error="Error: Incorrect username or password";
				require "login_form.php";
				exit;
			}
			
		}
		else{
			$error="Error: Database did not return any results";
			require "login_form.php";
			exit;
		}		
		
	}
	
	function login_form(){
		$username="";
		$error="Did not login";
		require "login_form.php";
		exit;
	}
	














?>