import java.util.*;

public class HillCipher {

    static int[][] key = new int[2][2];

    static String encrypt(String text) {

        text = text.toUpperCase().replaceAll("\\s", "");

        if (text.length() % 2 != 0)
            text += "X";

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            int[] p = new int[2];
            p[0] = text.charAt(i) - 'A';
            p[1] = text.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * p[0] + key[0][1] * p[1]) % 26;
            int c2 = (key[1][0] * p[0] + key[1][1] * p[1]) % 26;

            cipher.append((char) (c1 + 'A'));
            cipher.append((char) (c2 + 'A'));
        }

        return cipher.toString();
    }

    static int modInverse(int a, int m) {
        a %= m;
        for (int i = 1; i < m; i++) {
            if ((a * i) % m == 1)
                return i;
        }
        return -1;
    }

    static String decrypt(String cipher) {

        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;

        if (det < 0)
            det += 26;

        int detInv = modInverse(det, 26);

        if (detInv == -1)
            return "Key matrix not invertible.";

        int[][] inv = new int[2][2];

        inv[0][0] = (key[1][1] * detInv) % 26;
        inv[0][1] = ((-key[0][1] + 26) * detInv) % 26;
        inv[1][0] = ((-key[1][0] + 26) * detInv) % 26;
        inv[1][1] = (key[0][0] * detInv) % 26;

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {

            int[] c = new int[2];

            c[0] = cipher.charAt(i) - 'A';
            c[1] = cipher.charAt(i + 1) - 'A';

            int p1 = (inv[0][0] * c[0] + inv[0][1] * c[1]) % 26;
            int p2 = (inv[1][0] * c[0] + inv[1][1] * c[1]) % 26;

            plain.append((char) (p1 + 'A'));
            plain.append((char) (p2 + 'A'));
        }

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter 2x2 Key Matrix:");

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        String cipher = encrypt(text);

        System.out.println("Encrypted Text : " + cipher);

        System.out.println("Decrypted Text : " + decrypt(cipher));
    }
}