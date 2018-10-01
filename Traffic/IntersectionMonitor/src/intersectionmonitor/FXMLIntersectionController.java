/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionmonitor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

/**
 *
 * @author wjohnke
 */
public class FXMLIntersectionController implements Initializable {
    
    
    @FXML BorderPane root;
    @FXML Pane pane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Updater update=null;
        
        try{
            Intersection inter=new Intersection();
            pane=inter.pane;
            root.getChildren().add(pane);
            update=new Updater(pane, inter.carArray);
            new Thread(update).start();
        }catch(Exception ex ){
             
            ex.printStackTrace(System.out);
        }
        finally{
            if(update!=null) update.shutDown();
        }
    }
    
    
    
}
