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
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author wjohnke
 */
public class MessageFXMLController implements Initializable {

    @FXML private TextArea text;
    @FXML private Button btn;
    @FXML public Label label;
    
    private Stage stage;
    public String message="";
    public Scene startScene;
    public FXMLDocumentController startController;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void start(Stage stage){
        this.stage=stage;
        text.setText(message);
        
    }
    
    public void tryNewWord(ActionEvent event){
        stage.setScene(startScene);
    }
    
}
