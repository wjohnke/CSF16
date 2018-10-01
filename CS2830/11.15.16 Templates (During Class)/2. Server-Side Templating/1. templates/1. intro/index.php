<?php
	
	require 'Template.php';
	
	$page=new Template();
	
	$page->name="Mr. Wergeles";
	
	$result=$page->build('page.tmpl');
	
	print $result;
	
	$page2=new Template();
	
	$page2->name="Templates make life easier";
	
	$result2=$page2->build('page.tmpl');
	
	print $result2;
  
?>
