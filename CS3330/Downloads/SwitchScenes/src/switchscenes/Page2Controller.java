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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class Page2Controller implements Initializable {
    
    @FXML
    private Label infoLabel;
    
    private Stage stage;
    
    public String info = "";
    public Scene page1Scene;
    public Page1Controller page1Controller;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    public void start(Stage stage) {
        this.stage = stage;
        infoLabel.setText(info);
        System.out.println("I've started!");
        
    }
    
    @FXML
    private void goBackToPage1(ActionEvent event) {
        stage.setScene(page1Scene);
        page1Controller.doThisThing("This is the info I send you!");
    }
    
}
