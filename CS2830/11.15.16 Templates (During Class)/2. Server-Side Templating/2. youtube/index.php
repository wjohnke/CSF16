<?php
	$id=empty($_GET['src'])? 'ABC' : $_GET['src'];
	
	$videoData=json_decode(file_get_contents('videoData.json'));
	
	$data=$videoData->$id;
	
	require 'Template.php';
	
	$page=new Template();
	
	$page->data=$data;
	
	print $page->build('videoPage.tmpl');


?>