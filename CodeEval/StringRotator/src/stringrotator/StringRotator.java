/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringrotator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 *
 * @author wjohnke
 */
public class StringRotator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        File file=new File(args[0]);
        if(!file.exists()) return;
        String input="";
        String baseWord="", rotateWord="";
        
        
        try{
            FileReader in = new FileReader(file);
            BufferedReader buff= new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                String [] inArray=input.split(",");
                if(inArray!=null){
                    baseWord=inArray[0];
                    rotateWord=inArray[1];
                }
                
                
                
            }
            
        }
        catch(Exception ex){
            
        }
        
        
    }
    
}
