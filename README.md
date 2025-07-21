# Image Encryption and Decryption in Java
This project provides a secure way to encrypt and decrypt image files using AES (Advanced Encryption Standard) in CBC mode with password-based encryption (PBKDF2). It's built with Java and includes a simple console menu to choose between encryption and decryption.

# Project Structure
ImgEncryptorProject/
│
├── src/
│   └── ImgEncryptor/
│       ├── ImageEncryptor.java       # Main class with menu
│       ├── EncryptImage.java         # Image encryption logic (AES)
│       └── DecryptImage.java         # Image decryption logic (AES)


 # Features
1.  AES 256-bit encryption in CBC mode
2.  Password-based key generation using PBKDF2
3.  Serializes and stores IV and encrypted data
4.   Supports binary files like .jpg, .png, etc.

# Sample Encryption Flow
1. User chooses option 1
2. The image at path xyz.jpg is encrypted using a password
3. Encrypted file and IV are saved as encrypted_image.enc

# Sample Decryption Flow
1. User chooses option 2
2. The encrypted file and IV are loaded
3. Decrypted image is saved as decrypted_image.jpg
