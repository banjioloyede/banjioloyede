
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import java.util.Base64;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

public class AESMultipleFile {

    private SecretKey key;
    private final int KEY_SIZE = 128;
    private final int T_LEN = 128;
    private Cipher encryptionCipher;

    public void init() throws Exception {
          KeyGenerator generator = KeyGenerator.getInstance("AES");
          generator.init(KEY_SIZE);
          key = generator.generateKey();
         
        //key = new SecretKeySpec(new byte[]{22, 51, -12, -82, 111, 36, -102, 119, -60, -55, 29, 79}, "AES");
         System.out.println(key);
         System.out.println(Arrays.toString(key.getEncoded()));
        

    }

    public String encrypt(String message) throws Exception {
        byte[] messageInBytes = message.getBytes();
        encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        //GCMParameterSpec spec = new GCMParameterSpec(T_LEN, encryptionCipher.getIV());
        
        encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        System.out.println(Arrays.toString(encryptionCipher.getIV()));
                
        byte[] encryptedBytes = encryptionCipher.doFinal(messageInBytes);
        return encode(encryptedBytes);
    }

    public String decrypt(String encryptedMessage) throws Exception,NullPointerException {
        byte[] messageInBytes = decode(encryptedMessage);
        //encryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        //encryptionCipher.init(Cipher.ENCRYPT_MODE, key);
        Cipher decryptionCipher = Cipher.getInstance("AES/GCM/NoPadding");
        GCMParameterSpec spec = new GCMParameterSpec(T_LEN, new byte[] {46, 41, -108, 10, 16, 87, 10, 27, 106, 73, -18, 13});
        //GCMParameterSpec spec = new GCMParameterSpec(T_LEN, new byte[] {-68, 107, -108, 87, -27, -17, -99, 96, 34, -100, -121, 6, -27, 27, 29, -117});
        //GCMParameterSpec spec = new GCMParameterSpec(T_LEN, decryptionCipher.getIV());
        
        decryptionCipher.init(Cipher.DECRYPT_MODE, key, spec);
        byte[] decryptedBytes = decryptionCipher.doFinal(messageInBytes);
        return new String(decryptedBytes);
    }

    private String encode(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    private byte[] decode(String data) {
        return Base64.getDecoder().decode(data);
    }

    public static void main(String[] args) throws IOException {
        String[] filenames = new String[] {"file1.txt","file2.txt","file3.txt","file4.txt","file5.txt", "file6.txt", "file7.txt","file8.txt","file9.txt", "file10.txt","file11.txt", "file12.txt", "file13.txt","file14.txt","file15.txt"};

        for (String filename : filenames) {
        long start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)); BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted-aes-" + filename))) {
         String input = reader.lines().collect(Collectors.joining("\n"));
        //try (BufferedReader reader = new BufferedReader(new FileReader("file9.txt")); 
                //BufferedWriter writer = new BufferedWriter(new FileWriter("output1.txt"))){
                //BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted19-aes.txt"))) {
            //String input = reader.lines().collect(Collectors.joining("\n"));  
            //String line;
            AES aes = new AES();

            aes.init();

            /*while((line = reader.readLine()) != null)
        {
            System.out.println(line);

            String encryptedMessage = aes.encrypt(line);
            String decryptedMessage = aes.decrypt(encryptedMessage);

            System.err.println("Encrypted Message : " + encryptedMessage);
            System.err.println("Decrypted Message : " + decryptedMessage);
        }*/
            //String input = reader.lines().collect(Collectors.joining("\n"));
            String encryptedMessage = aes.encrypt(input);

            writer.write(encryptedMessage);
            //String decryptedMessage = aes.decrypt(encryptedMessage);

            //System.err.println("Encrypted Message : " + encryptedMessage);
            //System.err.println("Decrypted Message : " + decryptedMessage);
        } catch (Exception e) {
        }
          System.out.println(filename + "\t" + (System.currentTimeMillis() - start) / 1000d + " seconds");
        //System.out.println("encrypted19-aes.txt" + "\t" + (System.currentTimeMillis() - start) / 1000d + " seconds");
    }
    }
    public static void main3 (String[] args) throws IOException,FileNotFoundException {
        String[] filenames = new String[] {"encrypted-aes-file1.txt", "encrypted-aes-file2.txt", "encrypted-aes-file3.txt","encrypted-aes-file4.txt", "encrypted-aes-file5.txt", "encrypted-aes-file6.txt","encrypted-aes-file7.txt", "encrypted-aes-file8.txt", "encrypted-aes-file9.txt","encrypted-aes-file10.txt", "encrypted-aes-file11.txt","encrypted-aes-file12.txt", "encrypted-aes-file13.txt", "encrypted-aes-file14.txt","encrypted-aes-file15.txt"};
        for (String filename : filenames) {
        long start = System.currentTimeMillis();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename)); BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted-aes-" + filename ))) {
        String input = reader.lines().collect(Collectors.joining("\n"));
        //long start = System.currentTimeMillis();
        //try (BufferedReader reader = new BufferedReader(new FileReader("encrypted19-aes.txt")); 
                //(BufferedReader reader = new BufferedReader(new FileReader("output1.txt"));
            //BufferedWriter writer = new BufferedWriter(new FileWriter("decrypted19-aes.txt"))) {
            //String input = reader.lines().collect(Collectors.joining("\n"));  
            //String line;
            AES aes = new AES();

            aes.init();

            /*while((line = reader.readLine()) != null)
        {
            System.out.println(line);

            String encryptedMessage = aes.encrypt(line);
            String decryptedMessage = aes.decrypt(encryptedMessage);

            System.err.println("Encrypted Message : " + encryptedMessage);
            System.err.println("Decrypted Message : " + decryptedMessage);
        }*/
            //String input = reader.lines().collect(Collectors.joining());
            String decryptedMessage = aes.decrypt(input);

            System.out.println("Input: " + input);
            
            
            System.out.println("Output: " + decryptedMessage);
            writer.write(decryptedMessage);
            //String decryptedMessage = aes.decrypt(encryptedMessage);

            //System.err.println("Encrypted Message : " + encryptedMessage);
            //System.err.println("Decrypted Message : " + decryptedMessage);
        } catch (Exception e) {
        }
        System.out.println(filename + "\t" + (System.currentTimeMillis() - start) / 1000d + " seconds");
        //System.out.println("decrypted19-aes.txt" + "\t" + (System.currentTimeMillis() - start) / 1000d + " seconds");
    }
    }
}





//RESULT 

[12, 98, 119, 18, 103, -61, -128, -4, 55, -41, 17, -127]
file1.txt	0.983 seconds
[46, 14, 96, -90, -46, -38, -39, 56, -124, 8, -10, 99]
file2.txt	0.151 seconds
[59, 106, 42, -122, 27, 84, -79, 51, -80, -109, -43, 31]
file3.txt	0.261 seconds
[25, 87, 30, -18, 87, 115, -16, -119, 0, -121, 82, -67]
file4.txt	0.107 seconds
[8, 73, 47, 48, 122, -123, -98, -17, -15, -31, -52, -22]
file5.txt	0.142 seconds
[-114, -89, 116, -83, -32, 126, -50, -37, -81, -35, 46, 64]
file6.txt	0.098 seconds
[-81, 48, -105, 104, -128, -67, -57, 71, 13, 76, 88, -125]
file7.txt	0.174 seconds
[35, 15, -116, -21, -128, 66, 1, -50, 116, -82, -49, -1]
file8.txt	0.194 seconds
[-53, -60, -20, 107, 61, -85, -71, -12, 88, -1, 61, -39]
file9.txt	0.189 seconds
[-127, -79, 115, -52, -22, 102, -46, 17, 34, 101, -11, 40]
file10.txt	0.233 seconds
[-31, -3, 45, 60, -125, 92, -87, -4, 15, -43, 19, 6]
file11.txt	0.223 seconds
[88, -75, -73, -126, 95, 93, 73, 94, 15, 29, 103, 119]
file12.txt	0.3 seconds
[-116, -104, -84, -35, 124, -54, -65, -59, -112, 71, -103, 65]
file13.txt	0.22 seconds
[55, -102, 40, 94, -37, 91, -11, 45, -38, 73, 123, 92]
file14.txt	0.332 seconds
[-6, -11, 99, 94, 115, 93, -79, -59, -8, 18, 40, 61]
file15.txt	0.409 seconds

DECRYPTION RESULT

encrypted-aes-file1.txt	   0.338 seconds
encrypted-aes-file2.txt	   0.204 seconds
encrypted-aes-file3.txt	   0.132 seconds
encrypted-aes-file4.txt	   0.16 seconds
encrypted-aes-file5.txt	   0.101 seconds
encrypted-aes-file6.txt	   0.133 seconds
encrypted-aes-file7.txt	   0.111 seconds
encrypted-aes-file8.txt	   0.118 seconds
encrypted-aes-file9.txt	   0.126 seconds
encrypted-aes-file10.txt   0.187 seconds
encrypted-aes-file11.txt   0.168 seconds
encrypted-aes-file12.txt   0.135 seconds
encrypted-aes-file13.txt   0.213 seconds
encrypted-aes-file14.txt   0.167 seconds
encrypted-aes-file15.txt   0.187 seconds