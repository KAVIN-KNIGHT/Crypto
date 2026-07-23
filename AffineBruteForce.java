import java.util.*;

public class AffineBruteForce {

    static int modInverse(int a, int m) {
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1)
                return i;
        }
        return -1;
    }

    static String decrypt(String cipher, int a, int b) {
        int inv = modInverse(a, 26);

        if (inv == -1)
            return "";

        StringBuilder result = new StringBuilder();

        for (char ch : cipher.toUpperCase().toCharArray()) {

            if (Character.isLetter(ch)) {
                int y = ch - 'A';
                int x = (inv * (y - b + 26)) % 26;
                result.append((char) (x + 'A'));
            } else {
                result.append(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Cipher Text: ");
        String cipher = sc.nextLine();

        int[] validA = {1,3,5,7,9,11,15,17,19,21,23,25};

        for (int a : validA) {

            for (int b = 0; b < 26; b++) {

                System.out.println("a = " + a + " b = " + b +
                        " -> " + decrypt(cipher, a, b));
            }
        }
    }
}