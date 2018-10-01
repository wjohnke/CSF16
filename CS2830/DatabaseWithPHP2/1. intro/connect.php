<?php
	/* Created by Mr. Wergeles on November 9, 2016 */ 
	
	
	// $dbhost = 'localhost'; 
// 	$dbuser = 'root'; 
// 	$dbpass = 'nick'; 
// 	$dbname = 'CS2830'; 


	require_once "../db.conf"; 
	
	$mysqli = new mysqli($dbhost, $dbuser, $dbpass, $dbname); 
	
	if($mysqli -> connect_error) {
		die('Connection Error (' . $mysqli->connect_errno . ') ' . $mysqli->connect_error); 
	}
   	else {
   		//echo 'Connection successful!'; 
   		print "\nConnected! Status: " . $mysqli->host_info . "\n"; 
   	}
   	
   	
   	$query = "select * from foodstock"; 
   	
   	$result = $mysqli->query($query); 
   	
   	echo "<br>\n"; 
   	
   	print_r($result); 
   	
   	print "<br><br>\n\n"; 
   	
   	// fetch_assoc() gives us each record as an associative array
   	//$row = $result -> fetch_assoc(); 
   	
   	//print_r($row);
   	
   	print "<br><br>\n\n"; 
   	
   	while( $row = $result->fetch_assoc() ){
   		//print_r($row); 
   		
   		print "I have " . $row['quantity'] . ' ' . $row['name'] . ' for $' . $row['cost']; 
   		print "<br>\n"; 
   	}
   	
   	$result -> close(); 
   	
   	$mysqli -> close(); 


?>