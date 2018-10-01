<!DOCTYPE html>

<?php
setcookie("test_cookie", "test", time() + 10);
?>

<html>
<head>
	<title>Check Cookies Enabled</title>
</head>
<body>

	<?php
		if(count($_COOKIE) > 0) {
			echo "Cookies are enabled.";
		} 
		else {
			echo "Cookies are disabled.";
		}
	?>

</body>
</html>
