
<!DOCTYPE html>
<html>
<head>
	<title>Page 1</title>
    <link href="app.css" rel="stylesheet" type="text/css">
    <link href="../jquery-ui-1.11.4.custom/jquery-ui.min.css" rel="stylesheet" type="text/css">
    <script src="../jquery-ui-1.11.4.custom/external/jquery/jquery.js"></script>
    <script src="../jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
</head>
<body>
    <div class="ui-widget pageWidget">
        <h1 class="ui-widget-header">Page 1</h1>
        <div class="ui-widget-content">
            <p>This is a page containing protected content.</p>
            <p>You must be logged in to view this page.</p>
            <p><a href='page2.php'>Go to page 2.</a></p>
            <p><a href='logout.php'>Logout</a></p>
        </div>
    </div>
</body>
</html>