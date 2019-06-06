package AntonioAguirre;

import sun.security.krb5.internal.crypto.Des;

import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws Exception {

        CrearFichero.crearFichero("fitxerDeClaus_CS.txt",GeneradorDeClaus.generadorDeClausSimetriques(128)+"\n");
        CrearFichero.bw.write("Clave patataVoladora----"+GeneradorDeClaus.generadorDeClauApartirDeContrasenya("patataVoladora",128)+"\n");

        CrearFichero.bw.write("Clave MoniatoPeleon-----"+GeneradorDeClaus.generadorDeClauApartirDeContrasenya("MoniatoPeleon",128)+"\n");

        CrearFichero.bw.close();
        Encriptar.encriptarFitxerAmbClauApartirDeContrasenya("117531 arriba Esparta 135711");
//        Encriptar.encryptWrappedData();
//        Desencriptar.desencriptarFitxerAmbClauRSA();

    }

}
