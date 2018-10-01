/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cs4750hw2;

/**
 *
 * @author wjohnke
 */
public class State {
    public Integer [] grid; 
    public Explored explore;
    public State parent;
    
    public State(Integer [] grid){
        explore=Explored.UNEXPLORED;
        this.grid=grid;
    }
}
