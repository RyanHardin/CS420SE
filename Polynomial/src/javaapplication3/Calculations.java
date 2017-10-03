/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication3;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Taylor
 */
public class Calculations {
    private List<Integer> coefficients;
    private List<String> signs;
    public Calculations(List<Integer> coefficients, List<String> signs){
        this.coefficients = coefficients;
        this.signs = signs;
        Descartes(signs);
    }
    //determine number of real zeroes
    public void Descartes(List<String> signs){
        int count = 0;
        String temp = "";
        //Positive root case
        for(int i = 0; i<signs.size(); i++){
            if(i > 0 && !temp.equals(signs.get(i))){
                count++;
            }
            temp = signs.get(i);
        }
    }
}
