/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionmonitor;

import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author wjohnke
 */
public class Updater extends Thread{
    Pane pane;
    
    private Timeline timeline=null;
    private KeyFrame keyFrame;
    public volatile boolean isRunning=false;
    private ArrayList<Car> carArray;
    
    public Updater(Pane pane, ArrayList<Car> carArray){
        this.pane=pane;
        this.carArray=carArray;
    }
    
    
    @Override
    public void run(){
        isRunning=true;
        if(!Thread.interrupted()){
            try{
                if(!isRunning){
                    timeline.stop();
                    throw new Exception();
                }
                checkUpdate();
            }
            catch(Exception e){
                e.printStackTrace(System.out);
            }
        }
    }
    
    public void shutDown(){
        if(isRunning) isRunning=false;
        
        
        
    }
    
    public void checkUpdate() throws InterruptedException{
        /*
        Should only be called once
        */
        
        if(pane==null){
            isRunning=false;
            return;
        }
        
        if(timeline==null){
                if(keyFrame==null){
                    keyFrame=new KeyFrame(Duration.millis(1000), (ActionEvent event)-> {
                        if(!isRunning){
                            Thread.currentThread().interrupt();
                        }
                        updateCars(pane);
                        pane.requestLayout();
                    } );
                }

                timeline=new Timeline(keyFrame);
                timeline.setCycleCount(Animation.INDEFINITE);
            }

        timeline.play();
    }
    
    
    public void updateCars(Pane root){
        
        if(Thread.interrupted()){
            isRunning=false;
            return;
        }
        
        System.out.println("Firing event");
        
        
        Double posChange=4.0;
        Double currentX, currentY;
        Rectangle currRec;
        
        for(int car=0;car<carArray.size(); car++){
            
            currRec=(Rectangle)root.getChildren().remove(car);
            currentX=currRec.getLayoutX();
            currentY=currRec.getLayoutY();
            
            System.out.println(currRec.getLayoutX()+"     "+currRec.getLayoutY());
            
            
            
            if(carArray.get(car).getDirection().equals(Direction.EAST) || carArray.get(car).getDirection().equals(Direction.WEST)) {
                currRec.relocate(currentX+posChange, currentY);
            }
            else{
                currRec.relocate(currentX,(currentY+posChange));
                //recArray.get(car).setLayoutY(currentY+posChange);
            }
            
            root.getChildren().add(currRec);
            System.out.println(currRec.getLayoutX()+"     "+currRec.getLayoutY());
            
            
            root.requestLayout();
            
        }
        
        
    }
    
}
