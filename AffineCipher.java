import java.util.*;

public class AffineCipher {

    static int modInverse(int a, int m) {
        a = a % m;
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1)
                return i;
        }
        return -1;
    }

    static String encrypt(String text, int a, int b) {
        StringBuilder result = new StringBuilder();

        for (char ch : text.toUpperCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                int x = ch - 'A';
                int enc = (a * x + b) % 26;
                result.append((char) (enc + 'A'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    static String decrypt(String cipher, int a, int b) {
        StringBuilder result = new StringBuilder();
        int a_inv = modInverse(a, 26);

        if (a_inv == -1) {
            return "Invalid key! 'a' has no multiplicative inverse.";
        }

        for (char ch : cipher.toUpperCase().toCharArray()) {
            if (Character.isLetter(ch)) {
                int y = ch - 'A';
                int dec = (a_inv * (y - b + 26)) % 26;
                result.append((char) (dec + 'A'));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter value of a (coprime with 26): ");
        int a = sc.nextInt();

        System.out.print("Enter value of b: ");
        int b = sc.nextInt();

        String cipher = encrypt(text, a, b);
        System.out.println("Encrypted Text : " + cipher);

        String plain = decrypt(cipher, a, b);
        System.out.println("Decrypted Text : " + plain);
    }
}