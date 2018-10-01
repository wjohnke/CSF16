<?php
	// Require the Template class
	require_once('Template.php');
	
	// Create some associative arrays and store them in a $users array
	$users = array();
	$users[] = array('school' => 'MU', 'name' => 'John Smith', 'age' => 24, 'gender' => 'male');
	$users[] = array('school' => 'KU', 'name' => 'Sally Smith', 'age' => 25, 'gender' => 'female');
	$users[] = array('school' => 'KSU', 'name' => 'Karen Smith', 'age' => 23, 'gender' => 'female');
	
	$t1=new Template();
	
	$t1->users=$users;
	
	$tableHTML=$t1->build('usersTable.tmpl');
	
	print $tableHTML;
	
?>
