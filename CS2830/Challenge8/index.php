<!DOCTYPE html>



<html>

<!--
	Will Johnke
	ID: 14253530
	October 25th, 2016
-->

	<head>
		<meta charset="UTF-8">
		<title>Challenge 8</title>
	
	
	</head>
	<body>
	<?php
		if(is_dir(images)){
			$imgDir=opendir(images);
		}
		else{
			echo "<br>Directory not found";
		}
		
		while(($file=readdir($imgDir))!==false){
			$imgEx=pathinfo($file, PATHINFO_EXTENSION);
			if((strcmp($imgEx,"gif")==0) 
				or (strcmp($imgEx,"jpeg")==0) 
				or (strcmp($imgEx,"jpeg")==0) 
				or (strcmp($imgEx,"png")==0)){
					echo '<img src="images/' . $file . '" alt="random" width=800px height=500px><br>';
				}
		}
		closedir(imgDir);
	?>
	
	</body>
</html>