/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


/**
 *
 * @author wjohnke
 */
class Program extends Thread{
    
    public GridPane root;
    private int gridSize;
    private Boolean stop=false;
    private Obstacle ob1, ob2, robot;
    private int time=0, moment=1000;
    private int xCoord=0, yCoord=0;
    private Edge finish;
    private Rectangle[][] recArray;
    private ProgramState status;
    
    public Program(int gridSize, Edge finish, Rectangle[][] recArray, GridPane root){
        this.gridSize=gridSize;
        this.finish.vertX=finish.vertX;
        this.finish.vertY=finish.vertY;
        this.recArray=recArray;
        this.root=root;
    }
    
    @Override
    public void run(){
        
        ob1=new Obstacle(0, 4, 2, -1, +1);
        ob2= new Obstacle(2, 5, 1, 1, 0);
        robot = new Obstacle(5,5, 1, 0, 0);
        
        status=ProgramState.RUNNING;
        
        while(true){
            try{
                Thread.sleep(moment);
                if(stop){
                    Thread.currentThread().interrupt();
                    
                }
                
                System.out.println("Time: " + time++);
                
                xCoord=robot.coord.vertX;
                yCoord=robot.coord.vertY;
                
                if(((xCoord==ob1.coord.vertX)&&(yCoord==ob1.coord.vertY) ) || 
                        (xCoord==ob2.coord.vertX) && (yCoord==ob2.coord.vertY) ){
                    //Robot and at least one obstacle are in same vertex
                    System.out.println("Obstacle reached robot, end of game");
                    break;                
                }

                if((xCoord==finish.vertX) && (yCoord==finish.vertY)){
                    System.out.println("Robot reached goal! You win");
                    break;
                }
                
                /*
                Move robot and Obstacles, then update UI
                with new positions
                */
                
                robot.move(robot,ob1, ob2, finish);
                ob1.move(ob1, gridSize);
                ob2.move(ob2, gridSize);
                
                updateUI(recArray, status, ob1.coord);
                updateUI(recArray, status, ob2.coord);
                updateUI(recArray, status, robot.coord);
                
                
                System.out.printf("Robot at: (%d,%d)\n", xCoord, yCoord);
                System.out.printf("Obstacle 1 at: (%d,%d)\n", ob1.coord.vertX, ob1.coord.vertY);
                System.out.printf("Obstacle 2 at: (%d,%d)\n", ob2.coord.vertX, ob2.coord.vertY);
                
                
            }catch(InterruptedException ie){ 
                stop=true;
            }
            catch(Exception ex){ 
                Thread.currentThread().interrupt();
                status=ProgramState.EXCEPTION;
                break; 
            }   
        }
        
    }
    public void end(){
        stop=true;
    }
    
    
    public void updateUI(Rectangle[][] recArray, ProgramState status, Edge index){
        if( ((root!=null) && (status==ProgramState.RUNNING)) ){
            Platform.runLater(() -> {
                    recArray[index.vertX][index.vertY].setFill(Color.WHITE);
            } );
        }
    }
    
    
}
