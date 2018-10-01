<?php

require_once('Template.php');

class MenuComponent {
	protected $menuItems = array();
	protected $currentPage = '';

	public function __construct($menuItems, $currentPage) {
		$this->menuItems = $menuItems;
		$this->currentPage = $currentPage;
	}
	
	public function generate() {
	
		$tmpl = new Template();
		$tmpl->menuItems = $this->menuItems;
		$tmpl->currentPage = $this->currentPage;
		$menuHTML = $tmpl->build('menu.tmpl');
		
		$cssFile = "menu_style.css";
		
		$result = array();
		$result['cssFile'] = $cssFile;
		$result['html'] = $menuHTML;
		
		return $result;
	}

}
?>