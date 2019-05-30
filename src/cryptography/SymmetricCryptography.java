package cryptography;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class SymmetricCryptography {
    // Source
    //https://www.codejava.net/coding/file-encryption-and-decryption-simple-example
    //https://www.baeldung.com/java-cipher-input-output-stream

    /*
    AES
    - jest z rodziny szyfrow o roznych dlogosciach klucza.
    - do szyfrowania i do odszyfrowywania wykorzystywany jest ten sam klucz.
    -  posiada określony rozmiar bloku.
    - Rozmiar klucza używany w algorytmie określa liczbę powtórzeń transformacji,
      które przekształcają dane wejściowe (czyli tekst jawny) w dane wyjściowe (szyfrogram)
     */

    private static final String ALGORITHM = "AES";
    private static final String TRANSFORMATION = "AES";
    public static boolean decrypted = false;

    public static void encrypt(String key, File inputFile, File outputFile){
        // ENCRYPT_MODE - Constant used to initialize cipher to encryption mode.
        doCrypto(Cipher.ENCRYPT_MODE, key, inputFile, outputFile);
    }

    public static void decrypt(String key, File inputFile, File outputFile) {
        // ENCRYPT_MODE - Constant used to initialize cipher to encryption mode.
        doCrypto(Cipher.DECRYPT_MODE, key, inputFile, outputFile);
    }

    private static void doCrypto(int cipherMode, String key, File inputFile, File outputFile) {
        try {


            /*
                1. Create a Key from a given byte array for a given algorithm.
                2. Get an instance of Cipher class for a given algorithm transformation.
                3. Initialize the Cipher with an appropriate mode (encrypt or decrypt) and the given Key.
                4. Invoke doFinal(input_bytes) method of the Cipher class to perform encryption or decryption on the input_bytes, which returns an encrypted or decrypted byte array.
                5. Read an input file to a byte array and write the encrypted/decrypted byte array to an output file accordingly.
             */
            Key secretKey = new SecretKeySpec(key.getBytes(), ALGORITHM);

            // Object Cipher
            Cipher cipher = Cipher.getInstance(TRANSFORMATION);
            cipher.init(cipherMode, secretKey);


            FileInputStream inputStream = new FileInputStream(inputFile);
            byte[] inputBytes = new byte[(int) inputFile.length()];
            inputStream.read(inputBytes);

            byte[] outputBytes = cipher.doFinal(inputBytes);

            FileOutputStream outputStream = new FileOutputStream(outputFile);
            outputStream.write(outputBytes);

            inputStream.close();
            outputStream.close();
            decrypted = true;

        } catch (NoSuchPaddingException | NoSuchAlgorithmException
                | InvalidKeyException | BadPaddingException
                | IllegalBlockSizeException | IOException ex) {
                 decrypted = false;
                 System.out.println("Error encrypting/decrypting file");
        }
    }
}
