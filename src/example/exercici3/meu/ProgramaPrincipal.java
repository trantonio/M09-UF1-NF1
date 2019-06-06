package example.exercici3.meu;

public class ProgramaPrincipal {

    // Ctrl + Shift + F8

    public static void main(String[] args) {
        BorsaDeLLetres borsaDeLLetres = new BorsaDeLLetres();

        ProductorDeLLetres productorDeLLetres = new ProductorDeLLetres(borsaDeLLetres);
        Thread productor = new Thread(productorDeLLetres);
        ConsumidorDeLLetres consumidorDeLLetres = new ConsumidorDeLLetres(borsaDeLLetres);
        Thread consumidor = new Thread(consumidorDeLLetres);

        //borsaDeLLetres.log += "Main.INICI\n";

        productor.start();
        consumidor.start();

        try {
            System.out.println("=={PETICIÓ JOIN}==");
            productor.join(1000);
            consumidor.join(1000);
            System.out.println("=={RECEPCIÓ JOIN}==");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //borsaDeLLetres.log += "Main.FI";

    }
}
