<?php
	header('Content-type: image/jpeg');
	$handle=fopen("0.jpg", 'rb');
	$bufferLen=8192;
	
	while($buffer=fread($handle, $bufferLen) ){
		print $buffer;
	}
	fclose($handle);

?>