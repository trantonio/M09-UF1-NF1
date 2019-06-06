package example.exercici5.v1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Recursos {
    private int tamanyRecursos;     //El tamany dels recursos.
    private Semaphore semafor;      //El semàfor que controlarà l'accés als recursos.
    private Integer[] recursos;     //Són els recursos als quals es vol accedir. El tamany
                                    //depen de "tamanyRecursos".
    private boolean[] utilitzats;   //Indica si cadascun dels recursos s'està fent servir o
                                    //no (false = lliure).


    public Recursos(int tamany) {
        this.tamanyRecursos = tamany;
        semafor = new Semaphore(tamany);
        recursos = new Integer[tamany];
        utilitzats = new boolean[tamany];
    }

    public int adquirirRecurs() {
        try {
            semafor.acquire();
            Logger.getLogger(Recursos.class.getName()).log(Level.INFO, Thread.currentThread().getName());
            Thread.currentThread().sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return getRecurs();
    }

    public int getRecurs() {
        int i = 0;
        while (i < tamanyRecursos) {
            if (!utilitzats[i]) {
                utilitzats[i] = true;
                break;
            }
            i++;
        }
        return (i);
    }

    public void alliberarRecurs(int recurs) {
        utilitzats[recurs] = false;
        semafor.release();
    }
}
