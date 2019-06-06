package example.exercici3.v2;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BorsaDeLletres {

    private static final Logger LOGGER = Logger.getLogger(BorsaDeLletres.class.getName());
    private boolean finish;
    private char[] llistaDeLLetres = new char[10];
    private int index = -1;

    public BorsaDeLletres() {
        finish = false;
    }

    public synchronized char getLletra() {
        char c = '0';
        try {
            if (!quedenLletres()) {
                LOGGER.log(Level.INFO, "CONSUMIDOR.WAIT");
                wait();
            }
            c = llistaDeLLetres[index];
            LOGGER.log(Level.INFO, "CONSUMIDOR.RETIRAT[" + index + "] = " + c);
            index--;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
        return c;
    }

    public synchronized void setLletra(char c) {
        try {
            if (!quedaEspai()) {
                LOGGER.log(Level.INFO, "PRODUCTOR.WAIT");
                wait();
            }
            index++;
            llistaDeLLetres[index] = c;
            LOGGER.log(Level.INFO, "BORSA.AFEGIT[" + index + "] = " + c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();
    }

    public boolean quedaEspai() {
        return index < 9;
    }

    public boolean quedenLletres() {
        return index < -1;
    }

    public boolean isFinish() {
        return finish;
    }

    public void setFinish(boolean finish) {
        this.finish = finish;
    }
}
