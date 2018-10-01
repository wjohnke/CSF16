/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pointincircle;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author wjohnke
 */
public class PointInCircle {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String input="";
        String [] inArray, sectionArray;
        Double centerX, centerY, radius, pointX, pointY;
        
        try{
            FileReader in =new FileReader(args[0]);
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                inArray=input.split(";");
                
                sectionArray=inArray[0].split( " " );
                
                
                centerX=(Double.valueOf(sectionArray[1].substring(1, sectionArray[1].length()-1) ) );
                centerY=(Double.valueOf(sectionArray[2].substring(0, sectionArray[2].length()-1)));
                
                sectionArray=inArray[1].split(" ");
                radius=Double.valueOf(sectionArray[2]);
                
                sectionArray=inArray[2].split(" ");
                
                pointX=(Double.valueOf(sectionArray[2].substring(1, sectionArray[2].length()-1) ) );
                pointY=(Double.valueOf(sectionArray[3].substring(0, sectionArray[3].length()-1)));
                
                System.out.println(calculatePoint(centerX, centerY, radius, pointX, pointY));
            }
            
            buff.close();
            
            
        }catch(Exception ex){}
        
        
    }
    
    public static boolean calculatePoint(Double centerX, Double centerY, Double radius, Double pointX, Double pointY){
        return ( (((pointX)<=centerX+radius ) && (pointX>=centerX-radius) ) && ( (pointY<=centerY+radius) && (pointY>=centerY-radius) )  );
        
        
    }
    
}
