/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fizzbuzz;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author wjohnke
 */
public class FizzBuzz {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String line="", input="";
        String [] inArray; 
        String output="", text1="", text2="", longSub="";
        try{
            
        
        
            FileReader file=new FileReader(args[0]);
            BufferedReader buff=new BufferedReader(file);
            
            while((line=buff.readLine())!=null){
                input=line;
                
                if (input.isEmpty()) continue;
                
                inArray=input.split(";");
                text1=inArray[0];
                text2=inArray[1];
                
                lowestCD(text1, text2);
                
                
            }
        
        
        }
        catch(IOException | NumberFormatException ex){
            
        }
        
    }
    
    public static void lowestCD(String text1, String text2){
        
        
        
        
    }
    
}
