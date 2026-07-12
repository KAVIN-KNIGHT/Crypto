import java.util.*;

public class PlayfairCipher {

    static char[][] matrix = new char[5][5];

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Key: ");
        String key = sc.nextLine().toLowerCase().replace(" ", "").replace('j', 'i');

        createMatrix(key);

        System.out.println("\nKey Matrix:");
        printMatrix();

        System.out.print("\nEnter Plain Text: ");
        String plain = sc.nextLine().toLowerCase().replace(" ", "").replace('j', 'i');

        plain = preparePlainText(plain);

        System.out.println("Prepared Text : " + plain);

        String cipher = encrypt(plain);

        System.out.println("Cipher Text   : " + cipher);

        String decrypted = decrypt(cipher);

        System.out.println("Decrypted Text: " + decrypted);
    }

    // ---------------- Matrix ----------------

    static void createMatrix(String key) {

        LinkedHashSet<Character> set = new LinkedHashSet<>();

        for (char c : key.toCharArray())
            if (Character.isLetter(c))
                set.add(c);

        for (char c = 'a'; c <= 'z'; c++) {
            if (c == 'j')
                continue;
            set.add(c);
        }

        Iterator<Character> it = set.iterator();

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                matrix[i][j] = it.next();
    }

    static void printMatrix() {

        for (char[] row : matrix) {
            for (char c : row)
                System.out.print(c + " ");
            System.out.println();
        }
    }

    // ---------------- Prepare Plain Text ----------------

    static String preparePlainText(String text) {

        StringBuilder sb = new StringBuilder();

        int i = 0;

        while (i < text.length()) {

            char first = text.charAt(i);

            char second;

            if (i + 1 < text.length())
                second = text.charAt(i + 1);
            else
                second = 'x';

            if (first == second) {

                sb.append(first);
                sb.append('x');
                i++;

            } else {

                sb.append(first);
                sb.append(second);
                i += 2;
            }
        }

        if (sb.length() % 2 != 0)
            sb.append('x');

        return sb.toString();
    }

    // ---------------- Find Position ----------------

    static int[] find(char c) {

        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (matrix[i][j] == c)
                    return new int[] { i, j };

        return null;
    }

    // ---------------- Encrypt ----------------

    static String encrypt(String text) {

        StringBuilder cipher = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {

            char a = text.charAt(i);
            char b = text.charAt(i + 1);

            int[] p1 = find(a);
            int[] p2 = find(b);

            int r1 = p1[0], c1 = p1[1];
            int r2 = p2[0], c2 = p2[1];

            if (r1 == r2) {

                cipher.append(matrix[r1][(c1 + 1) % 5]);
                cipher.append(matrix[r2][(c2 + 1) % 5]);

            } else if (c1 == c2) {

                cipher.append(matrix[(r1 + 1) % 5][c1]);
                cipher.append(matrix[(r2 + 1) % 5][c2]);

            } else {

                cipher.append(matrix[r1][c2]);
                cipher.append(matrix[r2][c1]);
            }
        }

        return cipher.toString();
    }

    // ---------------- Decrypt ----------------

    static String decrypt(String cipher) {

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < cipher.length(); i += 2) {

            char a = cipher.charAt(i);
            char b = cipher.charAt(i + 1);

            int[] p1 = find(a);
            int[] p2 = find(b);

            int r1 = p1[0], c1 = p1[1];
            int r2 = p2[0], c2 = p2[1];

            if (r1 == r2) {

                plain.append(matrix[r1][(c1 + 4) % 5]);
                plain.append(matrix[r2][(c2 + 4) % 5]);

            } else if (c1 == c2) {

                plain.append(matrix[(r1 + 4) % 5][c1]);
                plain.append(matrix[(r2 + 4) % 5][c2]);

            } else {

                plain.append(matrix[r1][c2]);
                plain.append(matrix[r2][c1]);
            }
        }

        return plain.toString();
    }
}