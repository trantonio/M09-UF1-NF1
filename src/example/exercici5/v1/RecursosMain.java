package example.exercici5.v1;

public class RecursosMain {
    public static void main(String[] args) {
        Recursos recursos = new Recursos(2);
        RecursosConsumidor consumidor1 = new RecursosConsumidor(recursos);
        RecursosConsumidor consumidor3 = new RecursosConsumidor(recursos);
        RecursosConsumidor consumidor2 = new RecursosConsumidor(recursos);
        Thread c1 = new Thread(consumidor1);
        Thread c2 = new Thread(consumidor2);
        Thread c3 = new Thread(consumidor3);
        c1.setName("consumidor1");
        c2.setName("consumidor2");
        c3.setName("consumidor3");

        c1.start();
        c2.start();
        c3.start();
    }
}
