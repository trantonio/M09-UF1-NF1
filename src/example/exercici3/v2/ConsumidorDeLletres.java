package example.exercici3.v2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsumidorDeLletres implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ConsumidorDeLletres.class.getName());
    private BorsaDeLletres borsa;

    public ConsumidorDeLletres(BorsaDeLletres borsa) {
        this.borsa = borsa;
    }

    @Override
    public void run() {
        LOGGER.log(Level.INFO,"Consumidor.START");
        while (true) {
            if (borsa.isFinish()) {
                LOGGER.log(Level.INFO,"Consumidor.BORSA_IS_FINISHED");
                break;
            }
            borsa.getLletra();
        }
        LOGGER.log(Level.INFO,"Consumidor.FINISH");
    }
}
