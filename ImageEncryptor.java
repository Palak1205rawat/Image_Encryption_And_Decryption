package ImgEncryptor;
import java.util.Scanner;

public class ImageEncryptor {
	static EncryptImage em = new EncryptImage();
	static DecryptImage dm = new DecryptImage();
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\xyz.jpg";
        String encryptedFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\encrypted_image.enc";
        String decryptedFile = "C:\\Users\\ASUS\\OneDrive\\Desktop\\decrypted_image.jpg";
        String password = "abc";
        Scanner sc = new Scanner(System.in);

        try {
        	System.out.println("1.Image Encryption\n2. Image Decryption\n Your Choice:");
        	int x = sc.nextInt();
        	switch (x) {
        	case 1:
        		em.encryptImage(inputFile, encryptedFile, password);
                System.out.println("Image encryption completed successfully.");
        		break;
        	case 2:
                dm.decryptImage(encryptedFile, decryptedFile, password);
                System.out.println("Image decryption completed successfully.");
                break;
        	default:
        		System.out.println("Wrong option");	
        	}
        	} catch (Exception e) {
            e.printStackTrace();
        }
        sc.close();
    }
}


