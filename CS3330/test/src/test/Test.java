/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.ArrayList;

/**
 *
 * @author wjohnke
 */
public class Test {

    public enum Size {
    SMALL, MEDIUM, LARGE
    }
    
    public static void main(String[] args) {
        Size s=Size.MEDIUM;
        System.out.println(s);
        ArrayList<Double> obj=new ArrayList<>();
        obj.add(6.4);
    }
    
}

