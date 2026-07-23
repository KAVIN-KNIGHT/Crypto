import java.util.*;

public class VigenereCipher {

    static String generateKey(String text, String key) {
        key = key.toUpperCase();
        StringBuilder newKey = new StringBuilder(key);

        for (int i = key.length(); i < text.length(); i++) {
            newKey.append(key.charAt((i - key.length()) % key.length()));
        }

        return newKey.toString();
    }

    static String encrypt(String text, String key) {

        text = text.toUpperCase();
        key = generateKey(text, key);

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            char ch = text.charAt(i);

            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                int k = key.charAt(i) - 'A';

                cipher.append((char) ((x + k) % 26 + 'A'));
            } else {
                cipher.append(ch);
            }
        }

        return cipher.toString();
    }

    static String decrypt(String cipher, String key) {

        key = generateKey(cipher, key);

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {

            char ch = cipher.charAt(i);

            if (Character.isLetter(ch)) {

                int y = ch - 'A';
                int k = key.charAt(i) - 'A';

                plain.append((char) ((y - k + 26) % 26 + 'A'));
            } else {
                plain.append(ch);
            }
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String cipher = encrypt(text, key);

        System.out.println("Encrypted Text : " + cipher);
        System.out.println("Decrypted Text : " + decrypt(cipher, key));
    }
}