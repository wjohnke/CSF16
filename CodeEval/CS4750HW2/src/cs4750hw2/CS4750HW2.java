
package cs4750hw2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 *
 * @author wjohnke
 */
public class CS4750HW2 {

    public static void main(String[] args) {
        
        long startTime=System.currentTimeMillis();
        
        Integer  grid [] = new Integer[11];
        
        try{
            grid=parseFile(args, grid);
        }catch(Exception ex){}
        
        State beginState=new State(grid);
        
        if(iterativeDeepeningTSearch(beginState)) System.out.println("Tree successfully searched using Iterative Deepening Search.");
        else System.out.println("Tree unable to be searched.");
        
        if(depthFirstSearch(beginState)) System.out.println("Graph successfully searched using Depth First Graph Serach");
        
        System.out.println(System.currentTimeMillis()-startTime);
        
    }
    
    public static Integer [] parseFile(String [] args, Integer [] grid){
        String input;
        char [] temp;
        int position=0;
        
        try{
            FileReader in =new FileReader(args[0]);
            BufferedReader buff=new BufferedReader(in);
            
            while((input=buff.readLine())!=null){
                temp=input.toCharArray();
                for(int i=0; i<3; i++){
                    if(temp[i]!='.'){
                        grid[position++]=Character.getNumericValue(temp[i]);
                    }
                    else{
                        grid[9]=position; //0 represents empty spot
                        grid[position++]=0;
                    }
                }    
            }
            grid[10]=0;
            buff.close();
            if (grid==null) throw new Exception();
            
        }catch(Exception ex){}
        
        return grid;
    }
    
    public static boolean iterativeDeepeningTSearch(State beginState){
        Queue<State> fringe = new LinkedList<>();
        Integer [] currNode;
        State currState;
        int depth=0;
        int numNodesExpanded=0;
        try{
            fringe.add(beginState);
        }catch(Exception ex){}
        
        
        if(fringe.isEmpty()) return false;
        while(!fringe.isEmpty()){
            currState=(State) fringe.remove();
            if(goalTest(currState.grid)){
                while(currState.parent!=null){
                    for(int i=0; i<9; i++){
                        System.out.print(currState.grid[i]);
                        System.out.print(currState.grid[++i]);
                        System.out.print(currState.grid[++i]);
                        System.out.println("");
                    }
                    System.out.println("___");
                    currState=currState.parent;
                }
                System.out.println(numNodesExpanded + " nodes expanded");
                return true;
            }
            numNodesExpanded+=expandNode(fringe, currState, depth++);
            if(numNodesExpanded>100000){ System.out.println("Nodes exceeded 100,000"); return false;}
        }
        return false;
    }
    
    public static boolean goalTest(Integer [] grid){
        for(int i=0; i<8; i++){
            if(grid[i]!=i+1) return false;
        }
        return true;
        
        
    }
    
    public static int expandNode(Queue<State> fringe, State currState, int depth){
        /*
        Function expands current state with all possible moves based on position
        of empty tile. Possibilities range from four different directions to shift
        */
        currState.grid[10]=depth;
        Integer [] gridCopy=currState.grid.clone();
        State newState;
        int nodesExpanded=0;
        
        switch(gridCopy[9]){
            case 0:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 1:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 2:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 3:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 4:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 5:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 6:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 7:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            case 8:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.add(newState);
                nodesExpanded++;
                break;
            default:
                System.exit(0);
                break;
        }
        return nodesExpanded;
    }
    
    public static Integer [] downShift(Integer [] gridCopy, int emptyNode){
        int offset=3;
        Integer [] tempGrid;
        tempGrid=gridCopy.clone();
        emptyNode=tempGrid[9];
        tempGrid[emptyNode]=gridCopy[emptyNode+offset];
        tempGrid[emptyNode+offset]=0;
        tempGrid[9]=emptyNode+offset;
        return tempGrid;
    }
    public static Integer [] leftShift(Integer [] gridCopy, int emptyNode){
        int offset=-1;
        Integer [] tempGrid;
        tempGrid=gridCopy.clone();
        emptyNode=tempGrid[9];
        tempGrid[emptyNode]=gridCopy[emptyNode+offset];
        tempGrid[emptyNode+offset]=0;
        tempGrid[9]=emptyNode+offset;
        return tempGrid;
        
    }
    public static Integer [] rightShift(Integer [] gridCopy, int emptyNode){
        int offset=1;
        Integer [] tempGrid;
        tempGrid=gridCopy.clone();
        emptyNode=tempGrid[9];
        tempGrid[emptyNode]=gridCopy[emptyNode+offset];
        tempGrid[emptyNode+offset]=0;
        tempGrid[9]=emptyNode+offset;
        return tempGrid;
        
    }
    public static Integer [] upShift(Integer [] gridCopy, int emptyNode){
        int offset=-3;
        Integer [] tempGrid;
        tempGrid=gridCopy.clone();
        emptyNode=tempGrid[9];
        tempGrid[emptyNode]=gridCopy[emptyNode+offset];
        tempGrid[emptyNode+offset]=0;
        tempGrid[9]=emptyNode+offset;
        return tempGrid;
    }

    private static boolean depthFirstSearch(State beginState) {
        Stack<State> fringe=new Stack<>();
        State currState;
        int depth=0;
        int numNodesExpanded=0;
        try{
            fringe.push(beginState);
        }catch(Exception ex){}
        
        while(!fringe.isEmpty()){
            currState=(State)fringe.pop();
            
            if(goalTest(currState.grid)){
                //TODO, Solution successfully found
                return true;
            }
            if(currState.explore==Explored.UNEXPLORED){
                numNodesExpanded+=expandNodeDFS(fringe, currState, depth++);
            }
            if(numNodesExpanded>100000){ System.out.println("Nodes exceeded 100,000"); return false;}
        }
        return false;
    }
    
    
    public static int expandNodeDFS(Stack<State> fringe, State currState, int depth){
        /*
        Function expands current state with all possible moves based on position
        of empty tile. Possibilities range from four different directions to shift
        */
        currState.grid[10]=depth;
        Integer [] gridCopy=currState.grid.clone();
        State newState;
        int nodesExpanded=0;
        currState.explore=Explored.VISITED;
        
        switch(gridCopy[9]){
            case 0:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 1:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 2:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 3:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 4:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 5:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(downShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 6:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 7:
                newState=new State(rightShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            case 8:
                newState=new State(leftShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                gridCopy=currState.grid.clone();
                newState=new State(upShift(gridCopy, gridCopy[9]));
                newState.parent=currState;
                fringe.push(newState);
                nodesExpanded++;
                break;
            default:
                System.exit(0);
                break;
        }
        return nodesExpanded;
    }
}
