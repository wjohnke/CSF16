/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2articlereader;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 * @author wjohnke
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML private Label label;
    @FXML private Button politics;
    @FXML private Button sports;
    private String category="";
    private NewsManager newsManager;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void ready(){
        newsManager=new NewsManager();
        
        
        
        loadNews(category);
    }
    
    
    
    
    private void loadNews(String category){
        if(category.equals("") ){
            
        }
        
        
        try{
            newsManager.load(category); 
        }catch(Exception ex){
            //TODO
            //Display message
        }
        
    }
    
    private void handlePoliticsButton(ActionEvent event){
        this.category="Politics";
        loadNews(category);
    }
    private void handleSportsButton(ActionEvent event){
        this.category="Sports";
        loadNews(category);
    }
    
    
    
}
