package example.helpers;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

public class Encriptacio {


    public static SecretKey generadorDeClausSimetriques(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128)||(keySize == 192)||(keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;

    }

    public static byte[] menu2(SecretKey clauSecretaSimetrica) {
        return new byte[0];
    }

    public static void menu3(SecretKey clauSecretaSimetrica, byte[] dadesEncriptadesEnByte) {
    }

    public KeyPair generadorDeClausAsimetriques(int i) {

        return null;
    }

    public byte[][] menu12(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
        return new byte[0][];
    }

    public void menu13(KeyPair clauPublicaIPrivada, byte[][] dadesIClauEncriptadesEnByte) {
    }

    public void menu21(SecretKey clauSecretaSimetrica) {
    }

    public void menu22(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
    }

    public void menu31(SecretKey clauSecretaSimetrica) {
    }

    public void menu32(KeyPair clauPublicaIPrivada) {
    }

    public void menu41(SecretKey clauSecretaSimetrica) {
    }

    public void menu42() {
    }
}
