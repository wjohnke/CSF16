<?php
set_include_path('templates');
require_once("Template.php");
require_once("MenuComponent.php");

// Set Page Title
$title = 'Users';
// CSS
$css = array();
// HTML
$html = array();


// Build Menu
$menuResult = buildMenu();
$css[] = $menuResult['cssFile'];
$html[] = $menuResult['html'];

// Build Content
$html[] = buildContent();


// Build Page
$page = new Template();

$page->title = $title;
$page->css = $css;
$page->html = $html;

print $page->build('page.tmpl');



function buildMenu() {
	$menuItems = array();
	$menuItems[] = array('label' => 'Home', 'link' => 'index.php');
	$menuItems[] = array('label' => 'Users', 'link' => 'users.php');
	$menuItems[] = array('label' => 'myLink', 'link' => 'index.php');

	$currentPage = 'Users';

	$menu = new MenuComponent($menuItems, $currentPage);
	return $menu->generate();
}

function buildContent() {
	$users = array();
	$users[] = array('school' => 'MU', 'name' => 'John Smith', 'age' => 24, 'gender' => 'male');
	$users[] = array('school' => 'KU', 'name' => 'Sally Smith', 'age' => 25, 'gender' => 'female');
	$users[] = array('school' => 'KSU', 'name' => 'Karen Smith', 'age' => 23, 'gender' => 'female');
	
	
	$content = new Template();
	$content->users = $users;
	return $content->build('usersTable.tmpl');
}
?>