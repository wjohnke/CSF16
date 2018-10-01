/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

/**
 *
 * @author wjohnke
 */
public class Translation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Integer [] numSet;
        String input;
        String [] inArray;
        
        
        try{
            FileReader in=new FileReader(args[0]);
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                
                inArray=input.split(",");
                numSet=new Integer[inArray.length];
                
                for(int i=0; i<inArray.length; i++){
                    numSet[i]=Integer.parseInt(inArray[i]);
                }
                
                System.out.println(countNumberSumZero(numSet));
                
            }
            
            
            
        }catch(Exception ex){}
        
    }
    
    public static Integer countNumberSumZero(Integer [] numSet){
        Integer counter=0, sum=0;
        Integer [] set = new Integer[(int)Math.pow(numSet.length, 4.0)];
        
        fillSet(set, numSet, 0);
        
        int solSize=0;
        
        for (Integer i : numSet) {
            for (int j=0; j<4; j++) {
                sum+=numSet[];
                
            }
            sum=0;
        }
        
        return solSize;
    }
    
    public void filLSet(Integer [] set, Integer [] numSet, int size){
        if(size!=3){
            
            
            
        }
        
        
        
    }
}
