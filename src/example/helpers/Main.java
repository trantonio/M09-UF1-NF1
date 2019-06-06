package example.helpers;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Kristall k = new Kristall();
        try {
            k.menuKristall();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
