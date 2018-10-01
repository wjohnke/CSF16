/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stringpermutation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author wjohnke
 */
public class StringPermutation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String input;
        
        
        try{
            FileReader in =new FileReader(args[0]);
            
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                permute(input);
                
                
            }
            
            
        }catch(Exception ex){
            
        }
        
    }
    
    public static void permute(String input){
        char [] array=input.toCharArray();
        Arrays.sort(array);
        
        
        
    }
}
