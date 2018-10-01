/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2_project;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.paint.Paint;

/**
 * FXML Controller class
 *
 * @author wjohnke
 */
public class GridFXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML private GridPane root;
    Rectangle rect;
    Rectangle [][] rectArray;
    
    
    @FXML private void handleOpen(Event event){
        Stage primaryStage=(Stage)root.getScene().getWindow();
        
    }
    
    private void start(int size){
        
        Double width=root.getWidth();
        Double height=root.getHeight();
        Double coordWidth=width/size;
        rectArray=new Rectangle[size][size];
        
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                rect=new Rectangle(width, height);
                rect.setFill(Color.LIGHTGRAY);


                rect.setX(coordWidth*i+0.5);
                rect.setY(coordWidth*j+0.5);
                rectArray[i][j]=rect;
                root.getChildren().add(rect);
            }
        }
        
        
    }
    
    private void update(Obstacle node){
        
    }
    
    
}
