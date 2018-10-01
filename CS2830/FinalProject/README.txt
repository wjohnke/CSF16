Will Johnke
ID: 14253530
CS 2830 Final Project

Link to final website:

https://ec2-184-72-121-194.compute-1.amazonaws.com/Final%20Project/

I used HTML and CSS in laying out all the options and buttons.

I used javascript when changing the location of the header, when a button was clicked.
It was also used with any jquery.

I used a good amount of jquery. A little amount for styling here and there, and most
for quick access to things like AJAX, the document/the element I wanted inside the document,
and pictures.

I used a lot of PHP, particularly for logging in and out, checking to make sure the only
valid user was "test" with an encrypted password of "pass," by running a query to the
database, and returning back if a match was found. Then I used PHP to transfer back and forth
between pages, like logging out or when the user wanted to go to a certain section.

I used AJAX in my request to the New York Times API, which I employed using JQuery.
The NYT sent back a JSON of article headers and urls, which I wrapped into a unordered
list and output. The div displayed that the data was loading while this was happening.

I used GET in my request to the NYT and their API, because the data I was getting back
was not sensitive, and could be sent through the URL.
I used POST for logging in, to hide any sensitive username/password data from outside sources.

Errors were displayed when logging in didn't work/was due to an incorrect username or password.
Also when the connection was not over HTTPS.

Photos and videos were displayed on the "relax.php" page, in order to provide a lighter,
more relaxing side to the website, to give users a place to unwind and enjoy relaxing
music and pictures of scenery.

The News page displayed news and urls to access full articles, for users who wanted quick
access to any recent top stories.