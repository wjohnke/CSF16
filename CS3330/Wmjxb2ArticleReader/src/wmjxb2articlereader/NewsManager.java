/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2articlereader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.simple.*;
/**
 *
 * @author wjohnke
 */
public class NewsManager{
    
        private final String baseUrl="https://api.nytimes.com/svc/mostpopular/v2/mostviewed/";
        private final String apiKey="d6d16033c9b74d2eb4e5ed8811d7cda4";
        private String urlString="";
        private ArrayList<NYTNewsStory> newsStories;
        URL url;
        
        public NewsManager(){
            newsStories=new ArrayList<>();
        }
        
        
        public void load(String category) throws Exception{
            
            
            
            urlString=baseUrl+ category + "/1.json?api-key=" +apiKey;
            //Convert urlString into a URL object
            try{
                url=new URL(urlString);
            }
            catch (MalformedURLException muex){
                throw muex;
            }
            
            
            String jsonString="";
            
            try{
                //Open inputstream on the url (what the url returns back)
                BufferedReader buffReader=new BufferedReader(new InputStreamReader(url.openStream()) );
                String inputLine="";
                while((inputLine=(buffReader.readLine()))!=null){
                    jsonString+=inputLine;
                }
                buffReader.close();                
            }catch(IOException ioex){
                throw ioex;
            }
            
            try{
                parseJSON(jsonString);
            }
            catch(Exception ex){
                throw ex;
            }
        }
    
    private void parseJSON(String jsonString) throws Exception{
        if(jsonString==null || jsonString.equals("")) return;
        
        newsStories.clear();
        
        JSONObject jsonObj;
        
        try{
            jsonObj=(JSONObject)JSONValue.parse(jsonString);
        }catch(Exception ex){
            throw ex;
        }
        
        String status="";
        try{
            status=(String)jsonObj.get("status");
        }catch(Exception ex){
            throw ex;
        }
        
        if(status==null || !(status.equals("OK"))){
            throw new Exception("API Status not OK");
        }
        
        
        
         
        
    }    
    
}
