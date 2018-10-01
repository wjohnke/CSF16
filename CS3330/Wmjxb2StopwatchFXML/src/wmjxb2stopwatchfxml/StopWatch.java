/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2stopwatchfxml;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.util.Duration;

/**
 *
 * @author Will Johnke
 * September 19th, 2016
 * StopWatch Application
 * Creates an analog and a digital stopwatch, controlled
 * by user inputs and buttons
 */
public class StopWatch {
    //UI Variables
    private ImageView handImageView;
    private ImageView dialImageView;
    private final String handName="hand.png";
    private final String dialName="clockface.png";
    private Image handImage;
    private Image dialImage;
    private StackPane analog;
    private HBox hboxDigital, hboxAnalog;
    private GridPane root;
    private Label digital; //Using label to display digital clock
    //Timeline Variables
    private Timeline timeline;
    private KeyFrame keyframe;
    //Timer Variables to control how timer functions
    private double tickTimeInSeconds=1;
    private double secondsElapsed=0;
    private int minutesElapsed=0;
    private final double angleChange=6.0;
    
    //Default Constructor
    public StopWatch(){
        setLayout();
        setUpTimer();
    }
    
    private void setLayout(){
        /*Sets layout, putting each clock
        in own HBox, for layout
        Analog->Stackpane->HBox->root Gridpane
        Digital->HBox->root Gridpane
        */
        handImageView=new ImageView();
        dialImageView=new ImageView();
        handImage=new Image(getClass().getResourceAsStream(handName));
        dialImage=new Image(getClass().getResourceAsStream(dialName));
        digital=new Label("0:0.0");
        
        analog=new StackPane();
        root=new GridPane();
        hboxDigital=new HBox();
        hboxAnalog=new HBox();
        
        handImageView.setImage(handImage);
        dialImageView.setImage(dialImage);
        digital.setLayoutX(0);
        digital.setLayoutY(500);
        digital.setFont(Font.font("Cambria", 32));
        analog.getChildren().addAll(handImageView, dialImageView);
        hboxAnalog.getChildren().add(analog);
        hboxDigital.getChildren().add(digital);
        
        root.getChildren().addAll(hboxAnalog, hboxDigital);
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
        handImageView.setRotate(secondsElapsed*angleChange);
        
        if(secondsElapsed%60==0) minutesElapsed++;
        digital.setText(String.valueOf(minutesElapsed) + ":" + String.valueOf((secondsElapsed%60)));
        
    }
    /*
    Control for user input/buttons
    */
    public void stop(){
        if(timeline!=null){
            if (timeline.getStatus()==Status.RUNNING)
                timeline.stop();
        }
    }
    public void reset(){
        secondsElapsed=0;
        minutesElapsed=0;
        if(timeline!=null){
            if(timeline.getStatus()==Status.RUNNING)
                timeline.stop();
        }
        handImageView.setRotate(secondsElapsed*angleChange);
        digital.setText(String.valueOf(secondsElapsed));
    }
    public void start(){
        if(timeline!=null) timeline.play();
    }
    
    
    
    //Setter method to set Ticktime value
    public void setTickTime(double value){
        if(value>0 && value<100) tickTimeInSeconds=value;
        else System.out.println("Value out of range");
        setUpTimer();
        start();
    }
    
    /*
    Public getter methods
    */
    
    public Parent getRoot(){
        return root;
    }
    public double getWidth(){
        return dialImage.getWidth();
    }
    public double getHeight(){
        return dialImage.getHeight();
    }
    
    
    
}
