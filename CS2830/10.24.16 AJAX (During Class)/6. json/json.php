<?php
    //creating json array, with 3 keys and values w/ their key
	$me=array("name"=>"Mr. Wergeles", "pet"=>"Panda", "CS2830"=>"Ok");
    //sets up as printable json object for website
	print json_encode($me);
?>