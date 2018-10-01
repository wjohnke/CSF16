/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2sentenceanalyzer;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 *
 * @author wjohnke
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    @FXML private TextArea newText;
    
    @FXML private Button btn;
    
    private Stage stage;
    private Scene startScene;
    private Scene infoScene;
    private MessageFXMLController controller;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }   
    
    @FXML public void analyze(ActionEvent event){
        WordAnalyzer sent = new WordAnalyzer("abomasum");
        
        sent.getDefinition();
        if (infoScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageFXML.fxml"));
                Parent infoRoot = loader.load();
                controller = loader.getController();
                controller.startScene=startScene;
                controller.startController = this;
                infoScene = new Scene(infoRoot);
            } catch (Exception ex) {
                
            }
        }
        
        
        stage.setScene(infoScene);
        controller.message = sent.getMessage();
        controller.label.setText("Definition: ");
        controller.start(stage);
    }
    
    @FXML public void start(Stage stage){
        this.stage=stage;
        startScene=stage.getScene();
    }
    
    @FXML public void about(ActionEvent event){
        if (infoScene == null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MessageFXML.fxml"));
                Parent infoRoot = loader.load();
                controller = loader.getController();
                controller.startScene=startScene;
                controller.startController = this;
                infoScene = new Scene(infoRoot);
            } catch (Exception ex) {
                
            }
        }
       
        
        stage.setScene(infoScene);
        controller.label.setText("About:");
        controller.message="Will Johnke\nFinal Project CS3330\nID:14253530\n\nThis program analyzes a word and/or a sentence" +
                "\nand returns back definitions";
        controller.start(stage);
        
    }
    
    @FXML public void ownText(ActionEvent event){
        
    }
    
}
