<?php
// Based on: Dolphin CSS Menu
// Source: http://13styles.com/menus_detail.php?slug=dolphin

require_once('Template.php');

class MenuComponent {
	protected $menuItems = array();
	protected $currentPage = '';
	//constructor
	public function __construct($menuItems, $currentPage) {
		$this->menuItems = $menuItems;
		$this->currentPage = $currentPage;
	}
	//create instance of template class, store html and css files in result array
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