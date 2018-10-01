<?php
// Require the MenuComponent class
require_once("MenuComponent.php");

// Create an array of menu items and their links
$menuItems = array();
$menuItems[] = array('label' => 'Home', 'link' => 'home.php');
$menuItems[] = array('label' => 'People', 'link' => 'people.php');
$menuItems[] = array('label' => 'Activities', 'link' => 'activities.php');
$menuItems[] = array('label' => 'Contacts', 'link' => 'contacts.php');
$menuItems[] = array('label' => 'Help', 'link' => 'help.php');
$menuItems[] = array('label' => 'Login', 'link' => 'login.php');

$currentPage='Contacts';

$menu= new MenuComponent($menuItems, $currentPage);

$result = $menu->generate();

$menuCSSFile=$result['cssFile'];
$menuHTML=$result['html'];



?>
<!DOCTYPE html>
<html>
<head>
<title>Menu Component Test</title>
<link rel="stylesheet" href="<?php print $menuCSSFile; ?>" type="text/css">
</head>
<body>
<?php print $menuHTML; ?>
</body>
</html>