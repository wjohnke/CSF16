/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 *
 * @author wjohnke
 */
public class Checkers extends Application{
    
    
    public Double sceneDim=750.0;
    public int gridSize=20;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                
        Edge[][] graph=new Edge[gridSize][gridSize];
        Rectangle [][] recArray=new Rectangle[gridSize][gridSize];
        GridPane grid=new GridPane();
        
        /*
            UI Display Code
            Sets up the checkerboard style grid
        */
        for(int row=0; row<20; row++){
            for(int column=0; column<20; column++){
                Rectangle rec = new Rectangle();
                rec.setHeight(sceneDim/gridSize);
                rec.setWidth(sceneDim/gridSize);
                Color fill=((column+row)%2==0) ? Color.BLACK : Color.RED;
                rec.setFill(fill);
                recArray[row][column]=rec;
                GridPane.setRowIndex(recArray[row][column], row);
                GridPane.setColumnIndex(recArray[row][column], column);
                grid.getChildren().add(recArray[row][column]);
            }
        }
        
        Edge finish=new Edge();
        finish.vertX=7;
        finish.vertY=9;
        
        /*
        Run program in background, update UI when available
        */
        
        new Thread(new Program(gridSize, finish, recArray, grid)).start();
        
        Scene scene = new Scene(grid, sceneDim, sceneDim);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
