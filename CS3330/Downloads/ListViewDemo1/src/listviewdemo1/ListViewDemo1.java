/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package listviewdemo1;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.cell.ComboBoxListCell;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author dale
 * 
 * http://docs.oracle.com/javafx/2/ui_controls/list-view.htm
 * 
 */
public class ListViewDemo1 extends Application {
    
    public static final ObservableList listItems = FXCollections.observableArrayList();
    
    @Override
    public void start(Stage primaryStage) {
     primaryStage.setTitle("List View Sample");        
        
        final ListView<String> listView = new ListView<>();
        listView.setPrefSize(200, 250);
        
        listItems.addAll(
             "Adam", "Alex", "Alfred", "Albert",
             "Brenda", "Connie", "Derek", "Donny", 
             "Lynne", "Myrtle", "Rose", "Rudolph", 
             "Tony", "Trudy", "Williams", "Zach"
        );
          
        listView.setItems(listItems);
        
        //http://docs.oracle.com/javase/tutorial/extra/generics/wildcards.html
        
        
        listView.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> ov, String old_val, String new_val) {
                System.out.println(new_val + " " + old_val);        
            }
        });
        
        /*
        listView.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends String> ov, String old_val, String new_val) -> {
            System.out.println(new_val + " " + old_val);
        });
        */
               
        StackPane root = new StackPane();
        root.getChildren().add(listView);
        primaryStage.setScene(new Scene(root, 200, 250));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
