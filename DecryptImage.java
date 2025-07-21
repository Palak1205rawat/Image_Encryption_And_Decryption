public class DecryptImage {
    
}
package ImgEncryptor;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class DecryptImage {

    private static SecretKey generateKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[16], 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
	 public void decryptImage(String inputFile, String outputFile, String password) throws Exception {
	        // Generate a key from the password
	        SecretKey secretKey = generateKey(password);

	        // Initialize Cipher for decryption
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

	        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(inputFile))) {
	            byte[] iv = (byte[]) inputStream.readObject();
	            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

	            byte[] encryptedData = (byte[]) inputStream.readObject();

	            cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
	            byte[] decryptedData = cipher.doFinal(encryptedData);
	            Files.write(Path.of(outputFile), decryptedData);
	        }
	    }
}
