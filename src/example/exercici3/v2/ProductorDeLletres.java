package example.exercici3.v2;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductorDeLletres implements Runnable {

    private static final Logger LOGGER = Logger.getLogger(ProductorDeLletres.class.getName());
    private String alfabet = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    BorsaDeLletres borsa;

    public ProductorDeLletres(BorsaDeLletres borsa) {
        this.borsa = borsa;
    }

    @Override
    public void run() {
        char lletra;

        LOGGER.log(Level.INFO,"Productor.START");
        for (int i = 0; i <= 200; i++){
            lletra = alfabet.charAt((int)(Math.random()*25));
            borsa.setLletra(lletra);
        }
        borsa.setFinish(true);
        LOGGER.log(Level.INFO,"Productor.FINISH");
    }
}
