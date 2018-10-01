<!DOCTYPE html>
<html>
<head>
	<title>Content Navigator</title>
	<link rel="stylesheet" href="app.css" type="text/css">
	<script src="//code.jquery.com/jquery-1.11.3.min.js"></script>
	<script>
		$(function() {
			$.get("appData.php", function(data) {
				$("#wrapper h1").html(data.appTitle);

				// Method 1: using another scope
				
				/*
					 http://stackoverflow.com/questions/750486/javascript-closure-inside-loops-simple-practical-example
					 http://stackoverflow.com/questions/2568966/how-do-i-pass-the-value-not-the-reference-of-a-js-variable-to-a-function
				*/
				
				for (var i in data.posts) {
					var $li = $("<li>");

					
					(function(i) {
						$li
							.html(data.posts[i].title)
							.click(function() {

								$.get("appData.php", {"content": data.posts[i].id}, function(data) {

										$("#display").empty();
										postContent(data);

								});

							});
					}(i));
					$("#wrapper ul").append($li);
				}

				postContent(data.posts[0]);

			});

			function postContent(data) {
				var $h2 = $("<h2>");
				var $div = $("<div>");
				var $p = $("<p>");

				$h2.html(data.title);
				$div.addClass("imgPlaceholder " + data.color);
				$p.html(data.content);
				$("#display")
					.append($h2)
					.append($div)
					.append($p);
			}
		});
	</script>
</head>
<body>
	<div id="wrapper" class="group">
		<h1></h1>
		<hr>
		<ul id="menu"></ul>
		<div id="display"></div>
	</div>
</body>
</html>