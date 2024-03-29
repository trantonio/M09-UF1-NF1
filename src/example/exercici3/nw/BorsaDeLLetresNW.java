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
public class BorsaDeLLetresNW {
    private char[] llistaDeLLetres = {'0','0','0','0','0','0','0','0','0','0'};
    int posicio = -1;		//En posicio tenim el índex on hi ha l'últim element ficat.

    
    
    public BorsaDeLLetresNW() {
    }
    
    
    public synchronized int quantEspaiHiHa() {
        int posTmp;
        
       	posTmp = 9 - posicio;		//Posicions van de [0,9].
        
        return (posTmp);
    }
    
    
    public synchronized boolean hiHaCaracters() {
        if ((0 <= posicio) && (posicio <= 9)){
        	return true;
        } else {
        	return false;
        }
    }
    
    
    public synchronized void afegir(char caracter) {
        int posTmp;
        
        if ((-1 <= posicio) && (posicio <= 8)){		//De  [-1,8] --> [0,9].
            posicio++;
        	
            //System.out.println("BorsaDeLLetresNW.afegir(): NOTIFY(), posicio = " + posicio + ", caracter = " + caracter); 
            llistaDeLLetres[posicio] = caracter;
            notify();
        }
        else {
            try {
                posTmp = posicio + 1;
                System.out.println("BorsaDeLLetres.afegir(): WAIT(),  voliem ficar element en posicio = " + posTmp);
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    
    public synchronized char treure() {
        char caracter = '0';
        
        if ((0 <= posicio) && (posicio <= 9)){		//Entre [0,9] hi ha dades.
            caracter = llistaDeLLetres[posicio];
            posicio--;
            //System.out.println("BorsaDeLLetresNW.treure(): NOTIFY(), caracter = " + caracter);
            notify();
        } else {
            try {
                System.out.println("BorsaDeLLetres.treure(): WAIT(),  voliem treure element de posicio = " + posicio);
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        return caracter;
    }
    
}
