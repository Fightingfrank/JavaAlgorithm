package PSECLab;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESEncryptImage {
    // PNG image file path and name
    private static String path = "";
    private static String imageFileName = "elephant.png";
    private static String ivParameter = "1234567890123456";   //16bits long

    // Main method
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Generate random 128-bit AES (session) key
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128);
        SecretKey key = kgen.generateKey();
        System.out.println(key.toString().length());
        // Encrypt image
        encrypt(key, imageFileName);

        // Decrypt image
        decrypt(key, imageFileName);

        // Finish
        System.out.println("Done");
    }

    // Encryption
    private static void encrypt(SecretKey key, String imageFileName) throws IOException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        // Read image file
        byte[] imageBytes = readImageFile(path + imageFileName);

        // Instantiate AES cipher and initialise it for encryption
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        aesCipher.init(Cipher.ENCRYPT_MODE, key,iv);

        // Encrypt plaintext and use ciphertext to overwrite image
        byte[] ciphertext = aesCipher.doFinal(imageBytes);
        System.arraycopy(ciphertext, 0, imageBytes, 0, imageBytes.length);

        // Write encrypted image file
        writeImageFile(path + "encrypted-" + imageFileName);
    }

    // Decryption
    private static void decrypt(SecretKey key, String imageFileName) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        // Read encrypted image file
        byte[] imageBytes = readImageFile(path + "encrypted-" + imageFileName);

        // Instantiate AES cipher and initialise it for decryption
        Cipher aesCipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
        try {
            aesCipher.init(Cipher.DECRYPT_MODE, key, iv);
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        // Decrypt ciphertext and use plaintext to overwrite image
//        byte[] plaintext = aesCipher.doFinal(java.util.Arrays.copyOf(imageBytes, imageBytes.length-(imageBytes.length%16)));
        byte[] plaintext = aesCipher.doFinal(imageBytes);
        System.arraycopy(plaintext, 0, imageBytes, 0, plaintext.length);


        // Write decrypted image file
        writeImageFile(path + "decrypted-" + imageFileName);
    }

    // Auxiliary fields and methods
    private static BufferedImage img;

    private static byte[] readImageFile(String fileName) throws IOException {
        img = ImageIO.read(new File(fileName));
        WritableRaster raster = img.getRaster();
        DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
        return buffer.getData();
    }

    private static void writeImageFile(String fileName) throws IOException {
        File out = new File(fileName);
        out.delete();
        ImageIO.write(img, "png", out);
    }
}
