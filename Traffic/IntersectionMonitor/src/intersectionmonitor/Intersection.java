/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionmonitor;

import java.util.ArrayList;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author wjohnke
 */
public class Intersection {
    /*
    Initalizes UI Variables
    Sets up intersection with rectangles stored in GUI Pane
    And car info stored in carArray for reference later
    */
    
    
    public Pane pane;
    
    ArrayList<Car> carArray;
    private Color fill;
    private Integer pos;
    private final Double sceneWidth=300.0, sceneHeight=300.0;
    
    
    
    public Intersection(){
        setUpCars();
    }
    
    private void setUpCars(){
        
        /*
        Initialize variables
        */
        pane=new Pane();
        pane.setPrefSize(sceneWidth, sceneHeight);
        pane.setLayoutX(0);
        pane.setLayoutY(0);
        carArray=new ArrayList();
        pos=0;
        
        try{
            for(int i=0; i<5; i++){
                
                Car car=new Car(10.0, 20.0, 4.0, Direction.EAST);
                fill=Color.hsb((car.getHeight()*(i*60))%360, 1.0, 1.0);
                
                Rectangle rec=new Rectangle(car.getWidth(), car.getHeight(), fill );
                rec.relocate(0, (i*70)%sceneHeight);
                rec.setRotate(90);
                
                carArray.add(car);
                
                pane.getChildren().add(i,rec);
            }
            
        }catch(Exception ex){
            ex.printStackTrace(System.out);
            System.exit(0);
        }
        
        
        
    }
    
    
    
    
    
    
    
    
    
}
