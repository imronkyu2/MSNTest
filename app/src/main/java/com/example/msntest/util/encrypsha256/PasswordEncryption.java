package com.example.msntest.util.encrypsha256;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordEncryption {

    public static String encryptPassword(String password, byte[] salt) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");

            // Gabungkan password dan salt
            byte[] passwordBytes = password.getBytes();
            byte[] combinedBytes = new byte[passwordBytes.length + salt.length];
            System.arraycopy(passwordBytes, 0, combinedBytes, 0, passwordBytes.length);
            System.arraycopy(salt, 0, combinedBytes, passwordBytes.length, salt.length);

            // Hitung hash
            byte[] hashBytes = md.digest(combinedBytes);

            // Konversi hasil hash ke dalam bentuk hexadecimal
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Metode untuk menghasilkan salt acak
    public static byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }
}
