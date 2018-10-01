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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 *
 * @author wjohnke
 */
public class WordAnalyzer implements Analyzer {

    private String word="";
    private final String appID="ae46dbce";
    private final String apiKey="ddcb4bbf63cfb0ee2d424e78d813013f";
    private final String language="en";
    private final String baseURL="https://od-api.oxforddictionaries.com/api/v1/entries/";
    private String urlString="";
    private String message="";
    
    
    public WordAnalyzer(String word){
        this.word=word.toLowerCase();
    }
    
    @Override
    public void analyze(String word){
        
    }
    
    
    public String getMessage(){
        return message;
    }
    
    
    
    @Override
    public void getDefinition() {
        if(word==null || word.equals("")){
            message="Please enter a valid word first";
            return;
        };
        String jsonString="";
        
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
    
    private void parseJson(String jsonString) throws Exception{
        JSONObject jsonObj;
        
        try{
            jsonObj=(JSONObject)JSONValue.parse(jsonString);
        }catch(Exception ex){
            throw ex;
        }
        
        JSONArray results=(JSONArray)jsonObj.get("results");
        JSONObject lexicalObj=(JSONObject)results.get(0);
        JSONArray lexicalEntries=(JSONArray)lexicalObj.get("lexicalEntries");
        JSONObject entries=(JSONObject)lexicalEntries.get(0);
        JSONArray entriesArray=(JSONArray)entries.get("entries");
        
        JSONObject senseObj=(JSONObject)entriesArray.get(0);
        JSONArray senseArray=(JSONArray)senseObj.get("senses");
        JSONObject defObj=(JSONObject)senseArray.get(0);
        JSONArray defArray=(JSONArray)defObj.get("definitions");
        String definitions=defArray.toString();
        message+=word.toUpperCase() +": ";
        message+=definitions;
    }
    
    
    
    
}
