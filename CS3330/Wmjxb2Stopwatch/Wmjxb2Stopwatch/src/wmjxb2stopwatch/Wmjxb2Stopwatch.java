
package wmjxb2stopwatch;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Will Johnke
 * wmjxb2
 * ID: 14253530
 * 9/14/2016
 * Stopwatch application
 */
public class Wmjxb2Stopwatch extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button startBtn=new Button("Start");
        Button stopBtn=new Button("Stop");
        Button resetBtn=new Button("Reset");

        StopWatch analog=new StopWatch();
        HBox btnVbox=new HBox();
        GridPane root=new GridPane();
        
        /*Clip space so rotating doesn't run into other boxes*/
        GridPane analogWatch=(GridPane)analog.getRoot();
        Rectangle clip=new Rectangle(analog.getWidth(), analog.getHeight());
        analogWatch.setClip(clip);
        
        startBtn.setOnAction( event->{
            analog.start();
        });
        stopBtn.setOnAction( event->{
            analog.stop();
        });
        resetBtn.setOnAction( event->{
            analog.reset();
        });
      
        root.setHgap(1.0);
        root.setVgap(1.0);
        root.setAlignment(Pos.CENTER);
        
        btnVbox.getChildren().addAll(startBtn, stopBtn, resetBtn);
        
        root.getChildren().addAll(analogWatch);
        root.add(btnVbox, 0, 3);
        
        Scene scene = new Scene(root, analog.getWidth()+100, analog.getHeight()+50);
        
        primaryStage.setTitle("Stopwatch");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        analog.start();
        analog.setTickTime(1);
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
