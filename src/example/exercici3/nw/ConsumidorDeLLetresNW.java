/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example.exercici3.nw;

/**
 *
 * @author gines
 */
public class ConsumidorDeLLetresNW extends Thread {
    private BorsaDeLLetresNW borsaDeLLetres;
    
    
    public ConsumidorDeLLetresNW(BorsaDeLLetresNW borsaDeLLetres) {
        this.borsaDeLLetres = borsaDeLLetres;
    }
    
    
    @Override
    public void run() {
    	System.out.println("2222 - ConsumidorDeLLetres.INICI");
    	
        for (int i = 0; i < 5; i++) {
            System.out.println("   2222.1111[" + i + "] - ConsumidorDeLLetres: hiHaCaracters() = " + borsaDeLLetres.hiHaCaracters());
            
            //if (borsaDeLLetres.hiHaCaracters() == true) {
                System.out.println("   2222.2222[" + i + "] - ConsumidorDeLLetres: treure() = " + borsaDeLLetres.treure() + ", quantEspaiHiHa() = " + borsaDeLLetres.quantEspaiHiHa());
            //}
            
        }
        
        System.out.println("2222 - ConsumidorDeLLetres.FI");
    }
    
}
