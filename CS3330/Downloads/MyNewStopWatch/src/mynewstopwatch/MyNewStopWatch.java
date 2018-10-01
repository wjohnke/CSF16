/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author dalemusser
 */
public class MyNewStopWatch extends Application {
    
    private String appName = "StopWatch";
    
    @Override
    public void start(Stage primaryStage) {

        AnalogStopWatch analogStopWatch = new AnalogStopWatch();
        
        Scene scene = new Scene(analogStopWatch.getRootContainer(), 
                                analogStopWatch.getWidth(), 
                                analogStopWatch.getHeight());
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
        
        //analogStopWatch.setTickTimeInSeconds(1.0);
        
        analogStopWatch.start();
       
        analogStopWatch.setTickTimeInSeconds(1.0);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
