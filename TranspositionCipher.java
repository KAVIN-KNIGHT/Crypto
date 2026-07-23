import java.util.*;

public class TranspositionCipher {

    static String encrypt(String text) {

        StringBuilder even = new StringBuilder();
        StringBuilder odd = new StringBuilder();

        text = text.replaceAll("\\s", "");

        for (int i = 0; i < text.length(); i++) {

            if (i % 2 == 0)
                even.append(text.charAt(i));
            else
                odd.append(text.charAt(i));
        }

        return even.toString() + odd.toString();
    }

    static String decrypt(String cipher) {

        int mid = (cipher.length() + 1) / 2;

        String even = cipher.substring(0, mid);
        String odd = cipher.substring(mid);

        StringBuilder plain = new StringBuilder();

        int i = 0, j = 0;

        while (i < even.length() || j < odd.length()) {

            if (i < even.length())
                plain.append(even.charAt(i++));

            if (j < odd.length())
                plain.append(odd.charAt(j++));
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");

        String text = sc.nextLine();

        String cipher = encrypt(text);

        System.out.println("Encrypted Text : " + cipher);

        System.out.println("Decrypted Text : " + decrypt(cipher));
    }
}