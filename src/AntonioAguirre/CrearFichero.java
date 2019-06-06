package AntonioAguirre;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CrearFichero {

    public static BufferedWriter bw;

    public static void crearFichero(String nombreFichero, String text) throws IOException {

        String ruta = "files/"+nombreFichero;
        File archivo = new File(ruta);
        bw = new BufferedWriter(new FileWriter(archivo));
            bw.write(text);
//        if(archivo.exists()) {
//            bw = new BufferedWriter(new FileWriter(archivo));
//            bw.write(text);
//        } else {
//            bw = new BufferedWriter(new FileWriter(archivo));
//            bw.write(text);
//        }
    }

}
