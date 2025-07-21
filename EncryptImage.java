package ImgEncryptor;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
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

public class EncryptImage {
	private static SecretKey generateKey(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), new byte[16], 65536, 256);
        SecretKey tmp = factory.generateSecret(spec);
        return new SecretKeySpec(tmp.getEncoded(), "AES");
    }
	public void encryptImage(String inputFile, String outputFile, String password) throws Exception {
        // Generate a key from the password
        SecretKey secretKey = generateKey(password);

        // Initialize Cipher for encryption
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        IvParameterSpec ivParameterSpec = cipher.getParameters().getParameterSpec(IvParameterSpec.class);

        byte[] imageData = Files.readAllBytes(Path.of(inputFile));

        byte[] encryptedData = cipher.doFinal(imageData);

        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(outputFile))) {
            outputStream.writeObject(ivParameterSpec.getIV());
            outputStream.writeObject(encryptedData);
        }
    }


}
