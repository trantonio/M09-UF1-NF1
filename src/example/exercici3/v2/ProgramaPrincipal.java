package example.exercici3.v2;

public class ProgramaPrincipal {
    public static void main(String[] args) {
        BorsaDeLletres borsaDeLletres = new BorsaDeLletres();
        ProductorDeLletres productorDeLletres = new ProductorDeLletres(borsaDeLletres);
        ConsumidorDeLletres consumidorDeLletres = new ConsumidorDeLletres(borsaDeLletres);

        Thread productor = new Thread(productorDeLletres);
        Thread consumidor = new Thread(consumidorDeLletres);

        productor.start();
        consumidor.start();

        try {
            consumidor.join();
            productor.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
