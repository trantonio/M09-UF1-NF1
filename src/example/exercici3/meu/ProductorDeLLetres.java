package example.exercici3.meu;

public class ProductorDeLLetres implements Runnable {
    private String lletres = "ABCDEFGHIJKLMNOPQRSTUVXYZ";
    private BorsaDeLLetres borsaDeLLetres;

    public ProductorDeLLetres(BorsaDeLLetres borsaDeLLetres) {
        this.borsaDeLLetres = borsaDeLLetres;
    }

    @Override
    public void run() {

    }
}
