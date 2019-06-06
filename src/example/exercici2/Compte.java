package example.exercici2;


import java.util.logging.Level;
import java.util.logging.Logger;

import static example.exercici2.Bank.filOperacio1ID;


public class Compte {
    int saldo = 0;



    public Compte() {
    }


    public synchronized void ingressarDinners(int euros) {
        System.out.println("    " + Thread.currentThread().getName() + ".REALITZO L'OPERACIÓ");
        if (Thread.currentThread().getId() == filOperacio1ID) {
            try {
                System.out.println("    " + Thread.currentThread().getName() + ".SLEEP");
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Compte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        this.saldo = this.saldo + euros;
    }


    public synchronized void treureDinners(int euros) {
        this.saldo = this.saldo + euros;    //És una suma perquè el nº que entra per paràmetre ya és negatiu.
    }


    public synchronized int comprovarSaldo() {
        return this.saldo;
    }
}
