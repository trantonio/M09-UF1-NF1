/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici3;



/**
 *
 * @author gines
 */
public class ProductorDeLLetres extends Thread {
    private String alfabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private BorsaDeLLetres borsaDeLLetres;

    
    
    public ProductorDeLLetres(BorsaDeLLetres borsaDeLLetres) {
        this.borsaDeLLetres = borsaDeLLetres;
    }
    
    
    @Override
    public void run() {
        char caracter;
        
        
        System.out.println("1111 - ProductorDeLLetres.INICI");
        
        for (int i = 0; i < 20; i++) {
            caracter = alfabet.charAt((int)(Math.random()*25));
            System.out.println("1111.1111[" + i + "]- ProductorDeLLetres: caracter = " + caracter + ", quantEspaiHiHa() = " + borsaDeLLetres.quantEspaiHiHa()); 
            
            if (borsaDeLLetres.quantEspaiHiHa() > 0) {
                borsaDeLLetres.afegir(caracter);
                System.out.println("1111.2222[" + i + "] - ProductorDeLLetres: afegir() = " + caracter + ", quantEspaiHiHa() = " + borsaDeLLetres.quantEspaiHiHa()); 
            }
            
        }
    }
    
}
