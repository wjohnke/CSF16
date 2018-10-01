/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadedunzipper;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author dale
 * http://docs.oracle.com/javase/7/docs/api/java/io/FileFilter.html
 */
public class UserInterfaceController implements Initializable {

    private String appName = "Threaded Unzipper";
    private Stage stage;
    
    @FXML
    private TextField sourceDirectoryTextField;
    
    @FXML
    private TextField destinationDirectoryTextField;
    
    @FXML
    private ProgressBar progressBar;
    
    @FXML
    private TextArea statusTextArea;
    
    @FXML
    private Button handleUnzipButton;
    
    private File sourceDirectory;
    private File destinationDirectory;
    private Unzip unzip;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    
    public void ready(Stage stage) {
        this.stage = stage;
        stage.setTitle(appName);
        
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                if (unzip != null) {
                    unzip.end();
                }
            }
        });
    }
    
    @FXML
    private void handleSelectSourceDirectory(Event event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            sourceDirectory = directory;
            sourceDirectoryTextField.setText(directory.getPath());
        }
    }
    
    @FXML
    private void handleSelectDestinationDirectory(Event event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory = directoryChooser.showDialog(stage);
        if (directory != null) {
            destinationDirectory = directory;
            destinationDirectoryTextField.setText(directory.getPath());
        }        
    }
    
    // Note: the Unzip button toggles between Unzip and End
    
    @FXML
    private void handleUnzip(Event event) {
        if (unzip != null) {
            // if unzipping then end it
            // the Unzip button indicates End while unzipping
            unzip.end();
            return;
        }
        
        progressBar.setProgress(0);
        statusTextArea.clear();
        
        if (sourceDirectory == null) {
            statusTextArea.appendText("The source directory is not set.\n");
            return;
        }
        if (destinationDirectory == null) {
            statusTextArea.appendText("The destination directory is not set.\n");
            return;
        }
        
        // when an unzip is in progress the Unzip button has 
        // text changed to "Stop"
        handleUnzipButton.setText("Stop");
        
        unzip = new Unzip(sourceDirectory, destinationDirectory);
        
        unzip.setOnNotification(new Notification() {
            @Override
            public void handle(double percentComplete, UnzipState state, String message) {
                progressBar.setProgress(percentComplete);
                statusTextArea.appendText(message + "\n");
                if (state != UnzipState.RUNNING) {
                    // if not unzipping (INTERRUPTED, EXCEPTION, ENDED) then 
                    // set unzip to null and make text on Unzip button "Unzip"
                    unzip = null;
                    handleUnzipButton.setText("Unzip");
                }
            }
        });
        
        statusTextArea.appendText("Number of files to unzip: " + unzip.getNumFiles() + "\n");

        unzip.start();
    }
}
