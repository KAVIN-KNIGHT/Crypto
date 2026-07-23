import java.util.*;

public class ColumnarTransposition {

    static String encrypt(String text, int cols) {

        text = text.replaceAll("\\s", "").toUpperCase();

        while (text.length() % cols != 0)
            text += 'X';

        int rows = text.length() / cols;

        char[][] matrix = new char[rows][cols];

        int k = 0;

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                matrix[i][j] = text.charAt(k++);

        StringBuilder cipher = new StringBuilder();

        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                cipher.append(matrix[i][j]);

        return cipher.toString();
    }

    static String decrypt(String cipher, int cols) {

        int rows = cipher.length() / cols;

        char[][] matrix = new char[rows][cols];

        int k = 0;

        for (int j = 0; j < cols; j++)
            for (int i = 0; i < rows; i++)
                matrix[i][j] = cipher.charAt(k++);

        StringBuilder plain = new StringBuilder();

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < cols; j++)
                plain.append(matrix[i][j]);

        return plain.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plain Text: ");
        String text = sc.nextLine();

        System.out.print("Enter Number of Columns: ");
        int cols = sc.nextInt();

        String cipher = encrypt(text, cols);

        System.out.println("Encrypted Text : " + cipher);
        System.out.println("Decrypted Text : " + decrypt(cipher, cols));
    }
}