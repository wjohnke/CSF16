/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wmjxb2_project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 *
 * @author wjohnke
 */
public class Wmjxb2_project {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        if(args.length!=1){
            System.out.println("Invalid command line arguments");
            return;
        }
        
        int moment;
        
        try{
            System.out.println("Enter amount of time desired between movements (in milliseconds) or 0 ->");
            moment=System.in.read();
            if(moment<0) throw new IOException();
        }catch(IOException ioex){
            System.out.println("Value invalid");
            return;
        }
        
        int[] data=new int[15];
        /*
        Data Array:
            0 -> Size of room
            1 -> Starting X Coordinate of Robot
            2 -> Starting Y Coordinate of Robot
            3 -> Exit X Coordinate of Robot
            4 -> Exit Y Coordinate of Robot
            ------
            5/10 -> Starting X Coordinate of Obstacle 1/2
            6/11 -> Starting Y Coordinate of Obstacle 1/2
            7/12 -> Speed of Obstacle 1/2
            8/13 -> X Direction of Obstacle 1/2
            9/14 -> Y Direction of Obstacle 1/2
        */
        Edge finish=new Edge();
        
        try{
            parse_file(args[0], data);
            finish.vertX=data[3];
            finish.vertY=data[4];
        }catch(Exception ex){
            return;
        }
        
        int finishX=data[3], finishY=data[4];
        int limit=data[0];
        Edge [][] graph=new Edge[limit][limit];
        
        
        
        Obstacle robot= new Obstacle(data[1], data[2], 1, 0, 0);
        Obstacle ob1=new Obstacle(data[5], data[6], data[7], data[8], data[9]);
        Obstacle ob2=new Obstacle(data[10], data[11], data[12], data[13], data[14]);
        
        int time=0, xCoord, yCoord;
        while(true){
            try{
                System.out.println("Time: " + time++);
                
                xCoord=robot.coord.vertX;
                yCoord=robot.coord.vertY;
                
                if(((xCoord==ob1.coord.vertX)&&(yCoord==ob1.coord.vertY) ) || 
                        (xCoord==ob2.coord.vertX) && (yCoord==ob2.coord.vertY) ){
                    //Robot and at least one obstacle are in same vertex
                    System.out.println("Obstacle reached robot, end of game");
                    break;                
                }

                if((xCoord==finishX) && (yCoord==finishY)){
                    System.out.println("Robot reached goal! You win");
                    break;
                }
                
                Thread.sleep(moment);
                
                robot.move(robot,ob1, ob2, finish);
                
                ob1.move(ob1, limit);
                ob2.move(ob2, limit);
                
                System.out.printf("Robot at: (%d,%d)\n", xCoord, yCoord);
                System.out.printf("Obstacle 1 at: (%d,%d)\n", ob1.coord.vertX, ob1.coord.vertY);
                System.out.printf("Obstacle 2 at: (%d,%d)\n", ob2.coord.vertX, ob2.coord.vertY);
                
            }catch(Exception ex){}
        }
       
    }
    
    public static void parse_file(String filename, int [] data){
        
        String [] line=new String[9];
        try{
            FileReader file=new FileReader(filename);
            BufferedReader buff = new BufferedReader(file);
            
            try{
                for(int i=0; i<9; i++){
                    line[i]=buff.readLine();
                    if(line[i]==null){
                        throw new IOException();
                    }
                }
            }
            catch(IOException ioex){
                System.out.println("IO Exception");
                ioex.printStackTrace(System.out);
                return;
            }
            finally{
                buff.close();
                file.close();
            }
            if(line==null) throw new IOException();
        }
        catch(FileNotFoundException fnfex){
            System.out.println("File not found");
            fnfex.printStackTrace(System.out);
            return;
        }
        catch(IOException ioex){
            System.out.println("IO Exception");
            ioex.printStackTrace(System.out);
            return;
        }
        
        /*Parse actual line
          store data in array
        */
        try{
            //Size of room
            data[0]=Integer.parseInt(line[0]);
            if(data[0]<1) throw new Exception();
            
            for(int i=0; i<4; i++){
                parseLine(line[i+1], data, 1+(2*i));
            }
            parseLine(line[5], data, 8);
            parseLine(line[6], data, 10);
            parseLine(line[7], data, 12);
            parseLine(line[8], data, 13);
            
            
            for(int i=1; i<data.length; i++){
                switch (i) {
                    case 8: case 9: case 13: case 14:
                        if(data[i]>1 || data[i]<-1) throw new Exception();
                        break;
                    case 7: case 12:
                        if(data[i]<0) throw new Exception();
                        break;
                    default:
                        if(data[i]>data[0] || data[i]<1) throw new Exception();
                        break;
                }
            }
            
            
            
        }catch(Exception ex){
            System.out.println("Parsing error, invalid file format");
            ex.printStackTrace(System.out);
        }
    }
    
    public static void parseLine(String line, int data[], int index) throws Exception{
        
        String value="";
        char [] temp=line.toCharArray();
        int i=0;
        
        switch (index) {
            case 1:
            case 3:
            case 5:
            case 8:
            case 10:
            case 13:
                /*  
                    Parse Coordinates
                */
                
                //X Coordinate Parsing
                if((temp==null) || (temp[i]!='(') ) throw new Exception();
                i++;
                while(temp[i]!=',' && temp[i]!=')' && temp[i]!='\n'){
                    value=value.concat(String.valueOf(temp[i]));
                    i++;
                }
                data[index++]=Integer.parseInt(value,10); //Will check if value is parsable
                //Parse Matching Y Coordinate
                value="";
                if(temp[i]!=',') throw new Exception();
                i++;
                while(temp[i]!=',' && temp[i]!=')' && temp[i]!='\n'){
                    value=value.concat(String.valueOf(temp[i]));
                    i++;
                }
                
                data[index]=Integer.parseInt(value,10);
                break;
                
            case 7:
            case 12:
                //Parse Speed
                data[index]=Integer.parseInt(line);
                if(data[index]<0) throw new Exception();
                break;
                
            default:
                //Passed incorrect index
                throw new Exception("Passed incorrect index");
        }  
    }
    
}

