/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.util.Arrays;

/**
 *
 * @author gmartinez
 */
public class Encriptar {
    // Variable global que és el "vector d'inicialització (IV)" que es farà servir en el xifrat/desxifrat AES en mode CBC.
    public static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03, 
                                       0x04, 0x05, 0x06, 0x07, 
                                       0x08, 0x09, 0x0A, 0x0B, 
                                       0x0C, 0x0D, 0x0E, 0x0F}; 
    private static final String DIRECTORI_FITXERS = "fitxers/";
    private static final String fitxerDeDadesOriginal = "00_fitxerDeDadesOriginal.txt";
    private static final String fitxerDeDadesEncriptatAmbClauApartirDeContrasenya = "01_fitxerDeDadesEncriptatAmbClauApartirDeContrasenya.txt";
    private static final String fitxerDeDadesEncriptatAmbAES = "02_fitxerDeDadesEncriptatAmbAES.txt";
    private static final String fitxerAmbClauAESEncriptadaAmbClauRSA = "03_fitxerAmbClauAESEncriptadaAmbClauRSA.txt";
    
    
    
    

    public static void encriptarFitxerAmbClauApartirDeContrasenya() throws Exception {
        SecretKey clauSecretaSimetrica = Encriptar.passwordKeyGeneration();

        //leer archivo
        String cadena;
        FileReader f = new FileReader(DIRECTORI_FITXERS+fitxerDeDadesOriginal);
        BufferedReader b = new BufferedReader(f);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
        cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);

        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbClauApartirDeContrasenya), cipher);
        while((cadena = b.readLine())!=null) {
            byte[] fitbyte = cadena.getBytes();

            cos.write(fitbyte);
            System.out.println(cadena);
        }
        cos.close();
        b.close();

    }


    public static SecretKey passwordKeyGeneration() {
        String text = "Larga vida al imperio Klingon";
        int keySize = 128;
        SecretKey sKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                byte[] data = text.getBytes("UTF-8");
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.reset();
                md.update(data);
                byte[] hash = md.digest(data);
                byte[] key = Arrays.copyOf(hash, keySize/8);
                sKey = new SecretKeySpec(key, "AES");
            } catch (Exception ex ) {
                System.err.println("Error generant la clau:" + ex);
            }
        }
        return sKey;
    }


    public static KeyPair encriptarFitxerAmbClauRSA() throws Exception {
        SecretKey clauSecretaSimetrica = Encriptar.passwordKeyGeneration();
        byte[] clauAESEncriptadaEnByte;

        File file = new File(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbClauApartirDeContrasenya);
        FileInputStream fis = new FileInputStream(file);
        clauAESEncriptadaEnByte = new byte[(int) file.length()];
        fis.read(clauAESEncriptadaEnByte);
        fis.close();

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
        cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);

        CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(DIRECTORI_FITXERS+fitxerAmbClauAESEncriptadaAmbClauRSA), cipher);

        cos.write(clauAESEncriptadaEnByte);

        cos.close();

        CipherInputStream cis = new CipherInputStream(new FileInputStream(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbClauApartirDeContrasenya), cipher);
        BufferedReader br = new BufferedReader(new InputStreamReader(cis));

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbAES)));

        System.out.println("dadesDesencriptadesEnString:");

        String temp;
        while ((temp = br.readLine()) != null) {
            bw.write(temp + System.getProperty("line.separator"));
        }
        br.close();
        bw.close();


        return null;
    }

}
