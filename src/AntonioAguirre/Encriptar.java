/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AntonioAguirre;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.PublicKey;
import java.util.Arrays;


public class Encriptar {
    // Variable global que és el "vector d'inicialització (IV)" que es farà servir en el xifrat/desxifrat AES en mode CBC.
    public static final byte[] IV_PARAM = {0x00, 0x01, 0x02, 0x03, 
                                       0x04, 0x05, 0x06, 0x07, 
                                       0x08, 0x09, 0x0A, 0x0B, 
                                       0x0C, 0x0D, 0x0E, 0x0F}; 
    private static final String DIRECTORI_FITXERS = "files/";
    private static final String fitxerDeDadesOriginal = "fitxerDeClaus_CS.txt";
    private static final String fitxerDeDadesEncriptatAmbClauApartirDeContrasenya = "01_fitxerDeDadesEncriptatAmbClauApartirDeContrasenya.txt";
    private static final String fitxerDeDadesEncriptatAmbAES = "02_fitxerDeDadesEncriptatAmbAES.txt";
    private static final String fitxerAmbClauAESEncriptadaAmbClauRSA = "fitxerDeClaus_RSA.txt";
    
    
    
    

    public static void encriptarFitxerAmbClauApartirDeContrasenya(String clave) throws Exception {
        SecretKey clauSecretaSimetrica = Encriptar.passwordKeyGeneration(clave);

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


    public static SecretKey passwordKeyGeneration(String clave) {
        String text = clave;
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


    public static byte[][] encryptWrappedData() throws IOException {
//        PublicKey pub = Encriptar.passwordKeyGeneration("117531 arriba Esparta 135711");
        SecretKey pub = Encriptar.passwordKeyGeneration("117531 arriba Esparta 135711");
        File file = new File(DIRECTORI_FITXERS+fitxerDeDadesOriginal);
        BufferedReader br = new BufferedReader(new FileReader(file));



    byte[] data = br.readLine().getBytes();
        byte[][] encWrappedData = new byte[2][];
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey sKey = kgen.generateKey();
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encMsg = cipher.doFinal(data);
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.WRAP_MODE, pub);
            byte[] encKey = cipher.wrap(sKey);
            encWrappedData[0] = encMsg;
            encWrappedData[1] = encKey;
        } catch (Exception ex) {
            System.err.println("Ha succeït un error xifrant: " + ex);
        }
        return encWrappedData;
    }

}
