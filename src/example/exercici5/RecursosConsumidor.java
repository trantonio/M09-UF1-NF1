/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici5;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gmartinez
 */
public class RecursosConsumidor extends Thread {
    int id;                 //Ara mateix no té cap utilitat aquest atribut.
    Recursos recursos;

    
    
    public RecursosConsumidor(int id, Recursos recursos) {
        this.id = id;
        this.recursos = recursos;
    }
    
    
    public void run(){
        int numRecursAdquirit;
        
        numRecursAdquirit = recursos.adquirirRecurs();
        if (numRecursAdquirit != -1) {
            System.out.println(Thread.currentThread().getName() + " a adquirit el recurs nº " + numRecursAdquirit);
            //Si camuflem la següent linia, el semàfor no canvia i el recurs no queda alliberat.
            recursos.lliberarRecurs(numRecursAdquirit);
            System.out.println(Thread.currentThread().getName() + " a alliberat el recurs nº " + numRecursAdquirit);
        } else {
            System.out.println(Thread.currentThread().getName() + " no s'ha adquirit cap recurs");
        }
        
    }
    
}
