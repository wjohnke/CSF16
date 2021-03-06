<?php
	/* Created by Mr. Wergeles on November 9, 2016 */ 
	
	// Here we are using sessions to propagate the login
	// http://us3.php.net/manual/en/intro.session.php

    // HTTPS redirect
    if ($_SERVER['HTTPS'] !== 'on') {
		$redirectURL = 'https://' . $_SERVER['HTTP_HOST'] . $_SERVER['REQUEST_URI'];
		header("Location: $redirectURL");
		exit;
	}
	
	// http://us3.php.net/manual/en/function.session-start.php
	if(!session_start()) {
		// If the session couldn't start, present an error
		header("Location: error.php");
		exit;
	}
	
	
	// Check to see if the user has already logged in
	$loggedIn = empty($_SESSION['loggedin']) ? false : $_SESSION['loggedin'];
	
	if ($loggedIn) {
		header("Location: page1.php");
		exit;
	}
	
	
	$action = empty($_POST['action']) ? '' : $_POST['action'];
	
	if ($action == 'do_login') {
		handle_login();
	} else {
		login_form();
	}
	
	function handle_login() {
		$username = empty($_POST['username']) ? '' : $_POST['username'];
		$password = empty($_POST['password']) ? '' : $_POST['password'];
	
		/*
		if ($username == "test" && $password == "pass") {
			// Instead of setting a cookie, we'll set a key/value pair in $_SESSION
			$_SESSION['loggedin'] = $username;
			header("Location: page1.php");
			exit;
		} */
		
		
		require_once "../db.conf";
		
		$mysqli = new mysqli($dbhost, $dbuser, $dbpass, $dbname); 
		
		if($mysqli -> connect_error) {
			$error = "Error: " . $mysqli->connect_errno . ' ' . $mysqli->connect_error; 
			require "login_form.php"; 
			exit; 
		}
		
		$username = $mysqli->real_escape_string($username); 
		
		$password = $mysqli->real_escape_string($password); 
		
		$query = "select id from users where username = '$username' and password = '$password' "; 
		
		print $query; 
		exit; 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		   
        // Else, there was no result
//         else {
//           $error = 'Login Error: Please contact the system administrator.';
//           require "login_form.php";
//           exit;
//         }
	}
	
	function login_form() {
		$username = "";
		$error = "";
		require "login_form.php";
        exit;
	}
	
?>