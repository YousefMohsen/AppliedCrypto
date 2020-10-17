package chatapplication_server.components;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.util.Base64;

import javax.crypto.spec.SecretKeySpec;


public class EncryptionEngine {

    static String ALGO = "AES";


    public static String encrypt(String msg, int sharedSecretKey) {
        try {
            Key key = generateKey(intToByteArray(sharedSecretKey));
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.ENCRYPT_MODE, key);
            byte[] encVal = c.doFinal(msg.getBytes());
            return Base64.getEncoder().encodeToString(encVal);
        } catch (BadPaddingException | InvalidKeyException | NoSuchPaddingException | IllegalBlockSizeException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return msg;
    }

    public static String decrypt(String encryptedData, int sharedSecretKey) {
        try {
            Key key = generateKey(intToByteArray(sharedSecretKey));
            Cipher c = Cipher.getInstance(ALGO);
            c.init(Cipher.DECRYPT_MODE, key);
            byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
            byte[] decValue = c.doFinal(decordedValue);
            return new String(decValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedData;
    }

    private static Key generateKey(byte[] sharedSecretKey) {
        return new SecretKeySpec(sharedSecretKey, ALGO);
    }

    private static final byte[] intToByteArray(int value) {
        return new byte[] {
                (byte)(value >>> 24),
                (byte)(value >>> 16),
                (byte)(value >>> 8),
                (byte)value};
    }

}
