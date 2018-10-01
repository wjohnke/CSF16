/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decodenum;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author wjohnke
 */
public class DecodeNum {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String input, tempString="";
        char [] temp;
        Integer total=0, num;
        boolean valid=true;
        
        try{
            FileReader in =new FileReader(args[0]);
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                total=0;
                if (!(input.isEmpty()) && !(input.equals("0")) ) total++;
                temp=input.toCharArray();
                
                for(int i=0; i<temp.length-1; i++){
                    if(temp[i]<=0 || temp[i]==' ') total--;
                    tempString+=temp[i];
                    tempString+=temp[i+1];
                    num=Integer.parseInt(tempString);
                    if(num<=26 && num>0) total++;
                    tempString="";
                }
                
                System.out.println(total);
                
            }
            buff.close();
            
        }catch(Exception ex){}
    }
    
}
