/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mynewstopwatch;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author dalemusser
 */
public class AnalogStopWatch {
    
    private double tickTimeInSeconds = 0.01;  // change this to change resolution
    private double angleDeltaPerSeconds = 6.0;
    
    private double secondsElapsed = 0;
    
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    private StackPane rootContainer;
    private ImageView dialImageView;
    private ImageView handImageView;
    private Image dialImage;
    private Image handImage;
    private String dialImageName = "clockface.png";
    private String handImageName = "hand.png";
    

    public AnalogStopWatch() {
        setupUI();
        setupTimer();
    }
    
    private void setupUI() {
        rootContainer = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();    
        dialImage = new Image(getClass().getResourceAsStream(dialImageName));
        handImage = new Image(getClass().getResourceAsStream(handImageName));
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
        rootContainer.getChildren().addAll(dialImageView, handImageView);
    }
    
    public void setupTimer() {
        boolean running = false;
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                running = true;
                timeline.stop();
            }
        }

        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE); 
        
        if (running) {
            timeline.play();
        }
    }
    
    private void update() {
        secondsElapsed += tickTimeInSeconds;
        double rotation = secondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
    }
    
    // return type is Parent so that any type of JavaFX container
    // an be returned.  StackPane (and GridPane and AnchorPane and ...)
    // all inherit from Parent
    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) return dialImage.getWidth();
        else return 0.0;
    }
    
    public Double getHeight() {
        if (dialImage != null) return dialImage.getHeight();
        else return 0.0;       
    }
    
    public void start() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset() {
        stop();
        secondsElapsed = 0;
        handImageView.setRotate(0);
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }
    
    public boolean isRunning() {
        if (timeline != null) {
            if (timeline.getStatus() == Status.RUNNING) {
                return true;
            }
        } 
        return false;
    }
    
}
