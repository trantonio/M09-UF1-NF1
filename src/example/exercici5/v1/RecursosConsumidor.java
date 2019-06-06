package example.exercici5.v1;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RecursosConsumidor implements Runnable {
    private Recursos recursos;

    public RecursosConsumidor(Recursos recursos) {
        this.recursos = recursos;
    }

    public void run() {
        int recurs = recursos.adquirirRecurs();
        for (int i = 0; i < 10; i++) {
            Logger.getLogger(Recursos.class.getName())
                    .log(Level.INFO, Thread.currentThread().getName()+ " a adquirit el recurs nº " + recurs);

            recursos.alliberarRecurs(recurs);
            Logger.getLogger(Recursos.class.getName())
                    .log(Level.INFO, Thread.currentThread().getName()+ " a alliberat el recurs nº " + recurs);
        }
    }
}
