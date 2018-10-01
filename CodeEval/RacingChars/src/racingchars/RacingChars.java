/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package racingchars;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

/**
 *
 * @author wjohnke
 */
public class RacingChars {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        if(args.length!=1) {
            System.out.println("Incorrect Number of Command Line Arguments");
            return;
        }
        
        String input, output;
        String [] inArray;
        Integer currPosition=-1, choice, dif;
        char [] temp;
        
        try{
            FileReader in= new FileReader(args[0]);
            
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                
                temp=input.toCharArray();
                
                choice=findPath(temp, currPosition);
                
                if(currPosition==-1) dif=choice;
                else dif=currPosition-choice;
        
                switch(dif){
                    case -1:
                        temp[choice]='\\';
                        break;
                    case 1:
                        temp[choice]='/';
                        break;
                    default:
                        temp[choice]='|';
                        break;
                }
                currPosition=choice;
                output="";
                for(int i=0; i<temp.length;i++){
                    output+=temp[i];
                }
                
                System.out.println(output);
            }
            
            
            
        }catch(Exception ex){
            System.out.println("Error, Exception occurred");
        }
        
        
    }
    
    public static Integer findPath(char [] input, Integer currPosition){
        String output="";
        Integer gate=null, checkPoint=null, choice, dif;
        
        
        
        for(int i=0; i<input.length; i++){
                //Find checkpoint and gate locations
                if(input[i]=='C') checkPoint=i;
                else if(input[i]=='_') gate=i;
                
                if(gate!=null && checkPoint!=null) break; //Finish line as fast as possible
            }
            
        choice=makeChoice(currPosition, gate, checkPoint);
       
        return choice;
    }
    
    public static Integer makeChoice(Integer currPosition, Integer gate, Integer checkPoint){
        Integer choice=null, dif;
        
        if(currPosition==-1){
            /*
            First line, choices are not limited by position
            */
            if(checkPoint!=null) choice=checkPoint;
            else if(gate!=null) choice = gate;
            else {
                choice=0;
                System.out.println("Error making choice");
            }
            
        }
        else{
            /*
            Make choice based on current position
            First check if checkpoint exists, & always choose that
            path if it is possible, otherwise choose gate
            */
            if(checkPoint!=null){
                
                dif=currPosition-checkPoint;
                
                if(!((dif)<-1 || (dif)>1) ){
                    choice=checkPoint;
                }
            }
            else if(gate!=null){
                dif=currPosition-gate;
                
                if(!((dif)<-1 || (dif)>1) ){
                    choice=gate;
                }
                
            }
            else {
                choice=0;
                System.out.println("Error making choice");
            }
        }
        
        
        
        return choice;
    }
    
}
