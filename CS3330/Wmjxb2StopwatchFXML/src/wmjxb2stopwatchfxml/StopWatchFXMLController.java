/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2stopwatchfxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;


/**
 * FXML Controller class
 *
 * @author wjohnke
 */
public class StopWatchFXMLController implements Initializable {

    /*
    FXML Variables for layout
    */
    @FXML private Button startButton;
    @FXML private Button stopButton;
    @FXML private Button resetButton;
    @FXML private Label digital;
    
    @FXML private StopWatch myStopWatch; 
    @FXML private GridPane root;
    
    @FXML private ImageView clockView;
    @FXML private ImageView handView;
    @FXML private Image clockImage;
    @FXML private Image handImage;
    
    /*
    Variables for function of timer/stopwatch
    */
    private Timeline timeline=null;
    private KeyFrame keyframe;
    private double tickTimeInSeconds=1;
    private int secondsElapsed=0;
    private int minutesElapsed=0;
    private final double angleChange=6.0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {   
    }
    
    @FXML
    public void start(ActionEvent event){
        if(timeline!=null) timeline.play();
        else{
            setUpTimer();
            timeline.play();
        }
    }
    @FXML
    public void stop(ActionEvent event){
        if(timeline!=null){
            if (timeline.getStatus()==Status.RUNNING)
                timeline.stop();
        }
    }
    @FXML
    public void reset(ActionEvent event){
        secondsElapsed=0;
        minutesElapsed=0;
        if(timeline!=null){
            if(timeline.getStatus()==Status.RUNNING)
                timeline.stop();
        }
        handView.setRotate(secondsElapsed*angleChange);
        digital.setText("00:00");
    }

    
    private void setUpTimer(){
        /*
        Workaround for if timer has already been created and needs to 
        stop, so that multiple instances of timer are not created
        */
        if(timeline!=null){
            if(timeline.getStatus()==Status.RUNNING){
                timeline.stop();
            }
        }
        keyframe=new KeyFrame(Duration.millis(tickTimeInSeconds*1000), (ActionEvent event) ->{
            updateTimer();
        });
        timeline=new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE); //Run timeline indefinitely, until user stops
    }
    private void updateTimer(){
        /*Function to update picture, rotate certain # of degrees*/
        secondsElapsed+=tickTimeInSeconds;
        handView.setRotate(secondsElapsed*angleChange);
        
        if(secondsElapsed%60==0) minutesElapsed++;
        digital.setText(String.format("%02d:%02d", (int)minutesElapsed, (int)secondsElapsed%60));
    }
    
    //Setter method to set Ticktime value
    public void setTickTime(double value){
        if(value>0 && value<100) tickTimeInSeconds=value;
        else System.out.println("Value out of range");
        setUpTimer();
    }
    
    
}
