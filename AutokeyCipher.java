import java.util.*;

public class AutokeyCipher {

    static String encrypt(String text, String key) {

        text = text.toUpperCase();
        key = key.toUpperCase();

        String fullKey = key + text;

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {

            int p = text.charAt(i) - 'A';
            int k = fullKey.charAt(i) - 'A';

            cipher.append((char) ((p + k) % 26 + 'A'));
        }

        return cipher.toString();
    }

    static String decrypt(String cipher, String key) {

        cipher = cipher.toUpperCase();
        key = key.toUpperCase();

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i++) {

            int c = cipher.charAt(i) - 'A';

            int k;

            if (i < key.length())
                k = key.charAt(i) - 'A';
            else
                k = plain.charAt(i - key.length()) - 'A';

            char p = (char) ((c - k + 26) % 26 + 'A');

            plain.append(p);
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