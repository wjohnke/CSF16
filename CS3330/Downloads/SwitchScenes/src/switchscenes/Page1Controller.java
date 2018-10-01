/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package switchscenes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class Page1Controller implements Initializable {
    private Stage stage;
    private Scene page1Scene;
    private Scene page2Scene;
    private Page2Controller page2Controller;
    
    @FXML
    private Label label;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage) {
        this.stage = stage;
        page1Scene = stage.getScene();
        System.out.println("I've started!");
        
    }
    
    public void doThisThing(String info) {
        label.setText(info);
    }
    
    
    @FXML
    private void goToPage2(ActionEvent event) {
        System.out.println("Going to page 2.");
        if (page2Scene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Page2.fxml"));
                Parent page2Root = loader.load();
                page2Controller = loader.getController();
                page2Controller.page1Scene = page1Scene;
                page2Controller.page1Controller = this;
                page2Scene = new Scene(page2Root);
            } catch (Exception ex) {
                
            }
        }
        
        
        stage.setScene(page2Scene);
        page2Controller.info = "Hello World!";
        page2Controller.start(stage);
            
            
         
        
        
    }
    
}
