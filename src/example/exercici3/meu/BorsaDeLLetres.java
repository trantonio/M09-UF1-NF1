package example.exercici3.meu;

public class BorsaDeLLetres {
    private char[] llistaDeLLetres = new char[10];
    private int index = -1;
    private String log = "";

    public synchronized void afegirLletra(char lletra) {
        if (quedaEspai()) {

        } else {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized char treureLletra() {
        return ' ';
    }

    public synchronized boolean quedaEspai() {
        if (index > 8) {
            return false;
        } else {
            return true;
        }
    }

    public synchronized boolean quedenLletres() {
        if (index > -1) {
            return true;
        } else {
            return false;
        }
    }

}
