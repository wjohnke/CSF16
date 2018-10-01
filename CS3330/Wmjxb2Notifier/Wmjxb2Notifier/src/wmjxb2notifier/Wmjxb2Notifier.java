
package wmjxb2notifier;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Will Johnke
 * September 12th, 2016
 * Notifier JavaFx App
 */
public class Wmjxb2Notifier extends Application {
    
    public String appName="Notifier";
    public int width=400;
    public int height=250;
            
    @Override
    public void start(Stage primaryStage) {
        
        GridPane root = new GridPane();
        TextField text=new TextField();
        Button notifyBtn= new Button("Notify");
        Button clearBtn=new Button("Clear");
        
        notifyBtn.setOnAction(event ->{
            text.setText("You have been notified!");
        });
        
        clearBtn.setOnAction(event ->{
            text.setText("");
        });
        
        root.setHgap(10);
        root.setVgap(10);
        root.setAlignment(Pos.CENTER);
        
        root.add(text, 0,0);
        root.add(notifyBtn, 1, 0);
        root.add(clearBtn, 1, 1);
        
        Scene scene = new Scene(root, width, height);
        
        primaryStage.setTitle(appName);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
