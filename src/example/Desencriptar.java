/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package example;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.Key;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.util.Arrays;

/**
 *
 * @author gmartinez
 */
public class Desencriptar {
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
    private static final String fitxerDeDadesDesencriptatAmbAES = "04_fitxerDeDadesDesencriptatAmbAES.txt";
    private static final String SALT_LINIA = System.getProperty("line.separator");
    private static final String fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya = "05_fitxerDeDadesDesencriptatAmbClauApartirDeContrasenya.txt";
    
    


    public static void desencriptarFitxerAmbClauRSA(KeyPair clauPublicaIPrivada) throws Exception {
        PrivateKey clauPrivada;
        byte[] clauAESEncriptadaEnByte;
        String clauAESEncriptadaEnString;
        Key clauAESDesencriptada;


            // LLegim la clau AES del fitxer.
            File file = new File(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbAES);
            FileInputStream fis = new FileInputStream(file);
            clauAESEncriptadaEnByte = new byte[(int) file.length()];
            fis.read(clauAESEncriptadaEnByte);
            fis.close();

            clauAESEncriptadaEnString = new BASE64Encoder().encode(clauAESEncriptadaEnByte);
            System.out.println("clauEncriptadaEnString = " + clauAESEncriptadaEnString);
            System.out.println();

            // Desencriptem la clau d'encriptació AES que es va fer servir per encriptar les dades amb RSA + la clau privada.
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            clauPrivada = clauPublicaIPrivada.getPrivate();
            cipher.init(Cipher.UNWRAP_MODE, clauPrivada);
            clauAESDesencriptada = cipher.unwrap(clauAESEncriptadaEnByte, "AES", Cipher.SECRET_KEY);

            // Desencriptem les dades amb AES en mode CBC.
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.DECRYPT_MODE, clauAESDesencriptada, iv);

            CipherInputStream cis = new CipherInputStream(new FileInputStream(DIRECTORI_FITXERS+fitxerDeDadesEncriptatAmbAES), cipher);
            BufferedReader br = new BufferedReader(new InputStreamReader(cis));

            // Preparem el fitxer per guardar les dades desxifrades.
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(DIRECTORI_FITXERS+fitxerDeDadesDesencriptatAmbAES)));

            System.out.println("dadesDesencriptadesEnString:");

            String temp;
            while ((temp = br.readLine()) != null) {
                bw.write(temp + SALT_LINIA);
                System.out.println(temp);
            }
            System.out.println();
            br.close();
            bw.close();


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
    
    public static void desencriptarFitxerAmbClauApartirDeContrasenya() {

    }
    public void menu32(KeyPair clauPublicaIPrivada) {
//        PrivateKey clauPrivada;
//        byte[] clauAESEncriptadaEnByte;
//        String clauAESEncriptadaEnString;
//        Key clauAESDesencriptada;
//
//        try {
//            // LLegim la clau AES del fitxer.
//            File file = new File(FITXER_CLAU_SIMETRICA_XIFRADA_RSA);
//            FileInputStream fis = new FileInputStream(file);
//            clauAESEncriptadaEnByte = new byte[(int) file.length()];
//            fis.read(clauAESEncriptadaEnByte);
//            fis.close();
//
//            clauAESEncriptadaEnString = new BASE64Encoder().encode(clauAESEncriptadaEnByte);
//            System.out.println("clauEncriptadaEnString = " + clauAESEncriptadaEnString);
//            System.out.println();
//
//            // Desencriptem la clau d'encriptació AES que es va fer servir per encriptar les dades amb RSA + la clau privada.
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
//            clauPrivada = clauPublicaIPrivada.getPrivate();
//            cipher.init(Cipher.UNWRAP_MODE, clauPrivada);
//            clauAESDesencriptada = cipher.unwrap(clauAESEncriptadaEnByte, "AES", Cipher.SECRET_KEY);
//
//            // Desencriptem les dades amb AES en mode CBC.
//            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
//            cipher.init(Cipher.DECRYPT_MODE, clauAESDesencriptada, iv);
//
//            CipherInputStream cis = new CipherInputStream(new FileInputStream(FITXER_DADES_TRIPULANTS_XIFRADES_RSA), cipher);
//            BufferedReader br = new BufferedReader(new InputStreamReader(cis));
//
//            // Preparem el fitxer per guardar les dades desxifrades.
//            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FITXER_DADES_TRIPULANTS_SENSE_XIFRAR_RSA)));
//
//            System.out.println("dadesDesencriptadesEnString:");
//
//            String temp;
//            while ((temp = br.readLine()) != null) {
//                bw.write(temp + SALT_LINIA);
//                System.out.println(temp);
//            }
//            System.out.println();
//            br.close();
//            bw.close();
//
//        } catch (Exception ex) {
//            System.err.println("ERROR: menu32() " + ex);
//        } finally {
//            System.out.println("menu menu32(): FINAL");
//        }
    }

}
