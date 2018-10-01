/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intersectionmonitor;

/**
 *
 * @author wjohnke
 */
public class Car {
    
    private Double width, height, speed;
    private Direction direction;
    
    public Car(Double width, Double height, Double speed, Direction direction){
        this.width=width;
        this.height=height;
        this.speed=speed;
        this.direction=direction;
    }
    
    public Double getWidth(){
        return width;
    }
    public Double getHeight(){
        return height;
    }
    public Direction getDirection(){
        return direction;
    }
    
}
    
    
    

