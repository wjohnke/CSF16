/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import java.util.Random;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.QuadCurve;
import javafx.scene.shape.Rectangle;



public class Wmjxb2LionVisualizer implements Visualizer {
    //UI Shapes for visualizer
    Rectangle rect, grass;
    Rectangle [] rectArray, grassArray;
    Circle center, eye[];
    Double circumference;
    
    //Data fields for scaling of visualizer bands
    private final String name="Wmjxb2 Lion Visualizer";
    private Integer numBands;
    private AnchorPane vizPane;
    
    private Double bandWidth=0.0, sceneWidth=0.0;
    private Double bandHeight=0.0, sceneHeight=0.0;
    private final Double minBandHeight=20.0;
    private Double bandRotation=0.0;
    private final Double bandHeightPercentage=0.65; //To scale for practical use
    private final Double circleRadius=125.0;
    private Double xCoord=0.0, yCoord=0.0;
    private Double angleDeg=0.0;
    private Double cx=0.0, cy=0.0;
    
    
    //Style fields
    private final Double startHue = 240.0;
    private final Double lionHue=45.0, eyeHue=180.0, grassHue=110.0;
    private String initCSS="";
    private final Double bigEye=20.0, startPupil=10.0, outEye=25.0;

    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();  //End any preexisting wmjxb2 Visualizers before starting new one
        
        initCSS=vizPane.getStyle();
        this.numBands=numBands;
        this.vizPane=vizPane;
        sceneWidth=vizPane.getWidth();
        sceneHeight=vizPane.getHeight();
        
        Rectangle clip=new Rectangle(sceneWidth, sceneHeight);
        clip.setLayoutX(0);
        clip.setLayoutY(0);
        vizPane.setClip(clip);
        
        rectArray=new Rectangle[numBands];
        grassArray=new Rectangle[numBands];
        
        center=new Circle();
        center.setCenterX(sceneWidth/2);
        center.setCenterY(sceneHeight/2);
        center.setRadius(circleRadius);
        center.setFill(Color.hsb(lionHue,1.0,1.0,0.9));
        
        circumference=2*(Math.PI)*center.getRadius();
        bandWidth=(circumference/numBands);
        bandRotation=360.0/(numBands);
        bandHeight=sceneHeight*bandHeightPercentage;
        cx=center.getCenterX();
        cy=center.getCenterY();
        
        
        eye=new Circle[4];
        for(int j=0; j<4; j++){
            eye[j]=new Circle();
            if(j%2==0){
                eye[j].setCenterX(cx-30.0);
                eye[j].setCenterY(cy-30.0);
            }
            else{
               eye[j].setCenterX(cx+30.0);
               eye[j].setCenterY(cy-30.0); 
            }
        }
        eye[0].setRadius(outEye);
        eye[1].setRadius(outEye);
        eye[2].setRadius(startPupil);
        eye[3].setRadius(startPupil);
        eye[0].setFill(Color.hsb(0,1.0,0,1.0));
        eye[1].setFill(Color.hsb(0,1.0,0,1.0));
        eye[2].setFill(Color.hsb(eyeHue,1.0,.5, 1.0));
        eye[3].setFill(Color.hsb(eyeHue,1.0,.5, 1.0));
        
        vizPane.getChildren().add(center);
        
        
        if(numBands>39){
            for(int i=0; i<numBands; i++){

                angleDeg=((bandRotation*i)+90)%360;
                /*Find x& y coordinates using angle degrees,
                *converted to radians
                */
                xCoord=cx+(circleRadius*cos(Math.toRadians(angleDeg)));
                yCoord=cy+(circleRadius*sin(Math.toRadians(angleDeg)));
                rect=new Rectangle();
                rect.setX(sceneWidth-xCoord);
                rect.setY(sceneHeight-yCoord);
                rect.setRotate(angleDeg+90);
                rect.setWidth(bandWidth);
                rect.setHeight(bandHeight);
                rect.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
                vizPane.getChildren().add(rect);

                rectArray[i]=rect;
            }
        }
        else{
            for(int i=0; i<numBands; i++){

                rect=new Rectangle();
                rect.setX(0);
                rect.setY(i*bandHeight);
                rect.setRotate(90);
                rect.setWidth(bandWidth);
                rect.setHeight(bandHeight);
                rect.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
                vizPane.getChildren().add(rect);

                rectArray[i]=rect;
            }
        }
        
        
        for(int j=0; j<4; j++){
                vizPane.getChildren().add(eye[j]);
            }
            
        bandWidth=sceneWidth/numBands;
        for(int i=0; i<numBands; i++){
            grass=new Rectangle();
            grass.setX(bandWidth*i);
            grass.setY(0);

            grass.setWidth(bandWidth);
            grass.setFill(Color.hsb((grassHue), 1.0,1.0,1.0));
            vizPane.getChildren().add(grass);
            grassArray[i]=grass;
        }
    }

    @Override
    public void end() {
        if(rectArray!=null){
            for(Rectangle dRect:rectArray){
                vizPane.getChildren().remove(dRect);
            }
            rectArray=null;
            vizPane.setClip(null);
            vizPane.getChildren().remove(center);
            for(Circle circ:eye){
                vizPane.getChildren().remove(circ);
            }
            for(Rectangle grass:grassArray){
                vizPane.getChildren().remove(grass);
            }
            vizPane.setStyle(initCSS);
            center=null;
        }
            
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if(rectArray==null) return;
        Integer num = min(rectArray.length, magnitudes.length); //Adjust for uneven function calls
        
        if(magnitudes[0]>-20){
            eye[2].setRadius(bigEye);
            eye[3].setRadius(bigEye);
            
        }
        for(int i=0; i<num; i++){
            Double height=((((((magnitudes[i]+60.0)/60.0)*bandHeight)/2))+minBandHeight);
            rectArray[i].setHeight(height);
            grassArray[i].setHeight(height);
            grassArray[i].setFill(Color.hsb(grassHue+(magnitudes[i]*0.5), 1.0, 1.0,1.0));
            rectArray[i].setFill(Color.hsb(lionHue-10.0+(magnitudes[0]*0.4), 1.0, 1.0, 1.0));
            Double hue = (145.0 + (magnitudes[0]+30.0)/6.0);
            vizPane.setStyle("-fx-background-color: hsb(" + hue + ", 20%, 100%)" );
        }
    }
    
    
}
