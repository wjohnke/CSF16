/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beccasucks;

/**
 *
 * @author wjohnke
 */
public class BeccaSucks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        while(true){
            try{
                System.out.println("Does Becca suck?");
                Thread.sleep(1000);
                System.out.println("Yes, Becca still sucks.");
                Thread.sleep(1000);
                
            }
            catch(Exception ex){}
        }
        
    }
    
}
