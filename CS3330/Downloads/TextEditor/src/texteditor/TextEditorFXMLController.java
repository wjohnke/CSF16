/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package texteditor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author dalemusser
 */
public class TextEditorFXMLController implements Initializable {
    
    @FXML
    private TextArea textArea;
    
    @FXML
    private VBox root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    public void handleOpen(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) root.getScene().getWindow();
        
        fileChooser.getExtensionFilters().add(new ExtensionFilter("TXT files (*.txt)","*.txt"));
        
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));

                String document = "";
                String line = "";
                
                while ( (line = bufferedReader.readLine()) != null) {
                    document += line + "\n";
                }
                
                textArea.setText(document);
                
                bufferedReader.close();
                
            } catch (FileNotFoundException fnfe) {
                
            } catch (IOException ioe) {
                
            }
  
        }
        
        
        
        
    }
    
    @FXML
    public void handleSave(ActionEvent event) {
        System.out.println(textArea.getText());
    }
    
}
