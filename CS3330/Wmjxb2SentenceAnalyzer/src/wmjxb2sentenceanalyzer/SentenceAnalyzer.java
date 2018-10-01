/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2sentenceanalyzer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.*;


/**
 *
 * @author wjohnke
 */
public class SentenceAnalyzer implements Analyzer {
    
    private String sentence="";
    private final String appID="ae46dbce";
    private final String apiKey="ddcb4bbf63cfb0ee2d424e78d813013f";
    private final String language="en";
    private final String baseURL="https://od-api.oxforddictionaries.com/api/v1/entries/";
    private String urlString="";
    private String [] words;
    private String message="";
    
    
    public SentenceAnalyzer(String sentence){
        this.sentence=sentence.toLowerCase();
        
        try{
            words=sentence.split("[ ]+");
        }catch(Exception ex){
            message="Error parsing string, please enter valid sentence";
        }
        
        
        
    }
    
    @Override
    public void getDefinition(){
        if(sentence==null || sentence.equals("") || words==null){
            message="Please enter a valid sentence first";
            return;
        };
        String jsonString="";
        
        for (String word : words) {
            urlString = baseURL+language+"/"+word;
            try{
                URL url=new URL(urlString);

                HttpsURLConnection urlConnection=(HttpsURLConnection) url.openConnection();
                urlConnection.setRequestProperty("Accept", "application/json" );
                urlConnection.setRequestProperty("app_id", appID);
                urlConnection.setRequestProperty("app_key", apiKey);
                
                BufferedReader reader=new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                String line=null;
                while((line=reader.readLine())!=null){
                    jsonString+=(line+"\n");
                }
                
            
                
                
            }catch(MalformedURLException muex){
                muex.printStackTrace(System.out);
                message="URL Error";
            }catch(IOException ioex){
                ioex.printStackTrace(System.out);
                message="URL Error";
            }
            
            try{
                parseJson(jsonString);
            }catch(Exception ex){
                ex.printStackTrace(System.out);
                message="Error parsing JSON";
            }
            
        }
    }
    
    public void analyze(String sentence){
        
    }
    
    private void parseJson(String jsonString) throws Exception{
        JSONObject jsonObj;
        
        try{
            jsonObj=(JSONObject)JSONValue.parse(jsonString);
        }catch(Exception ex){
            throw ex;
        }
        
        JSONArray definitions=(JSONArray)jsonObj.get("results");
        System.out.println(definitions);
    }
    
    public String getMessage(){
        return message;
    }
    
}
