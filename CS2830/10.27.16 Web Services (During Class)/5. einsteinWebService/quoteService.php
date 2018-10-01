<?php
/*
	[Einstein Quote API]
	
	To...
		Get a quote:	quoteService.php?action=getQuote
		With index:		quoteService.php?action=getQuote&num=[0 to max]
		
		# of quotes:	quoteService.php?action=countQuotes
		
		Random quote:	quoteService.php?action=getRandom
		
		
Reference: http://php.net/manual/en/function.array-rand.php
*/



// Quote Data
$einsteinQuotes = array();
$einsteinQuotes[] = 'A person who never made a mistake <i>never tried anything new</i>.';
$einsteinQuotes[] = 'I know quite certainly that I myself have no special talent; curiosity, obsession and dogged endurance, combined with self-criticism have brought me to my ideas.';
$einsteinQuotes[] = 'Great spirits have always <strong>encountered violent opposition</strong> from mediocre minds.';
$einsteinQuotes[] = 'If my theory of relativity is proven successful, Germany will claim me as a German and France will declare that I am a citizen of the world. Should my theory prove untrue, France will say I am a German and Germany will declare that I am a Jew.';
$einsteinQuotes[] = 'Who the hell is Einstein?';

// Get action
$action = empty($_GET['action']) ? '' : $_GET['action'];

$info = '';

// Evaluate action
switch($action) {
	case 'getQuote':
		$quoteNumber = empty($_GET['num']) ? 0 : $_GET['num'];
		$info = $einsteinQuotes[$quoteNumber];
		break;
	case 'countQuotes':
		$info = count($einsteinQuotes);
		break;
	case 'getRandom':
		$info = $einsteinQuotes[array_rand($einsteinQuotes)];
		break;
	default: 
		$info = "Something isn't working right, the options are 'getQuote', 'countQuotes', and 'getRandom'"; 
		break; 
}

// Print requested information
print $info;
?>