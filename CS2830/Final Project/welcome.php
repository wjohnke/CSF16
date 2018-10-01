<!DOCTYPE html>

<html>
	<head>
		<meta charset="UTF-8">
		<title>Scenic Photos</title>
	       
        <script src="jquery-ui-1.11.4.custom/jquery-ui.min.js"></script>
        <link type="text/css" href="style.css" rel="stylesheet">
        <script>
            function load(){
                $("#pictureContainer").html("Loading pictures and content...");
                
                var url = "https://api.nytimes.com/svc/topstories/v2/home.json";
                url += '?' + $.param({
                  'api-key': "d6d16033c9b74d2eb4e5ed8811d7cda4"
                });
                $.ajax({
                  url: url,
                  method: 'GET',
                }).done(function(result) {
                  $("#pictureContainer").html(result);
                }).fail(function(err) {
                  throw err;
                });
                
                
            }
        
        </script>
	
	
	</head>
	
	<body>
	
		<h1 style="text-align:center">Top NewYorkTimes Newsstories from Today</h1>
		<div class="pictureContainer">
            <div onload="load()">
                
            </div>
            
            
        </div>
		
		
		
	
	</body>
</html>