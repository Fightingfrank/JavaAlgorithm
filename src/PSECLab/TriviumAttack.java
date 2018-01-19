package PSECLab;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TriviumAttack {
    // Hexadecimal representation of two ciphertexts encrypted with the same (unknown) Trivium key stream
    static private String ciphertext1 = "B3FCE10F55C9D3";
    static private String ciphertext2 = "B4EBE20E49D2DA";

    //
    // Main method
    //
    public static void main(String[] args) throws Exception {
        // Compute ciphertext1 XOR ciphertext2 (which is the same as plaintext1 XOR plaintext2)
        byte[] ciphertext1Bytes = hexToBytes(ciphertext1);
        byte[] ciphertext2Bytes = hexToBytes(ciphertext2);
        byte[] plaintextXOR = new byte[7];
        for (int i=0; i<7; i++) {
            plaintextXOR[i] = (byte)(ciphertext1Bytes[i] ^ ciphertext2Bytes[i]);
        }

        // Read all dictionary words of size 7
        String[] words = readDictionary(7);

        // Brute force attack
        bruteForceAttack(plaintextXOR, words);
    }

    //
    // Brute force attack: check all pairs (word1,word2) of seven-character words from the dictionary
    // until (word1 XOR word2) matches the expectedPlaintextXOR; and print the matching word pair.
    //
    static private void bruteForceAttack(byte[] expectedPlaintextXOR, String[] words) {

        for(int i = 0 ; i < words.length; i++){
            for(int j = i+1; j < words.length; j++){
                byte[] words1Bytes = words[i].getBytes();
                byte[] words2Bytes = words[j].getBytes();
                byte[] cipherTextXOR = new byte[7];
                for(int k = 0; k < 7; k++){
                    cipherTextXOR[k] = (byte)(words1Bytes[k]^words2Bytes[k]);
                }
                if(Arrays.equals(expectedPlaintextXOR,cipherTextXOR))
                    System.out.println("the words1 is : " + words[i] + "; and the words1 is :" + words[j]);
                }
            }
        }


    // Read dictionary
    static private String[] readDictionary(int len) throws IOException, FileNotFoundException {
        File f = new File("dictionary.txt");
        FileReader fr = new FileReader(f);
        BufferedReader reader = new BufferedReader(fr);

        ArrayList<String> words = new ArrayList<String>();
        String line;
        while ((line=reader.readLine())!=null) {
            if (line.length()==len) {
                words.add(line);
            }
        }
        reader.close();
        fr.close();

        return words.toArray(new String[words.size()]);
    }

    // Auxiliary function
    public static byte[] hexToBytes(String s) {
        byte[] result = new byte[s.length() / 2];
        for (int i = 0; i < result.length; i++) {
            int x = Integer.parseInt(s.substring(2*i, 2*i+2), 16);
            result[i] = (byte) x;
        }
        return result;
    }
}
