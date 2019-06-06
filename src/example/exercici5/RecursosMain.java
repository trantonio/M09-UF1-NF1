/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici5;

/**
 *
 * @author gmartinez
 */
public class RecursosMain {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Recursos recursos = new Recursos(2);
        RecursosConsumidor consumidor1 = new RecursosConsumidor(1, recursos);
        RecursosConsumidor consumidor2 = new RecursosConsumidor(2, recursos);
        RecursosConsumidor consumidor3 = new RecursosConsumidor(3, recursos);
        consumidor1.setName("consumidor1");
        consumidor2.setName("consumidor2");
        consumidor3.setName("consumidor3");
        
        
        consumidor1.start();
        consumidor2.start();
        consumidor3.start();
        
        
        
    }

    
    
}
