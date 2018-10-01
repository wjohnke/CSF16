/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package codeeval;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author wjohnke
 */
public class CardValidity {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String temp="";
        String [] tempArray;
        int [] data= new int[16];
        
        try{
            File file= new File(args[0].trim());
            FileReader in = new FileReader(file);
            BufferedReader buff= new BufferedReader(in);
            while((temp=buff.readLine())!=null){
                tempArray=temp.split(" ");
                
                int j=-1;
                for(int i=0; i<16; i++){
                    if(i%4==0) j++;
                    data[i]=Character.getNumericValue(tempArray[j].charAt(i%4));
                }
                String result=detectCard(data) ? "Real" : "Fake";
                
                System.out.println(result);
                
            }
            
            buff.close();
            
            
        }catch(Exception ex){
            System.out.println("Exception reading file");
        }
        
        
        
    }
    
    public static boolean detectCard(int [] data){
        int sum=0;
        for(int i=0; i<16; i++){
            sum+=i%2==0 ? 2*(data[i]) : data[i];
        }
        
        return sum%10==0;
        
    }
    
}
