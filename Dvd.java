import java.util.*;
import java.lang.*;

class Dvd {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        
        while (true) {
            System.out.println("---------------------------");
            System.out.println("\tCaeser Cipher!");
            System.out.println("---------------------------");
            System.out.println("1.Encrypt\n2.Decrypt\n3.Exit");
            System.out.print("Enter your choice: ");
            
            // Check if there is an integer to read to prevent crashes
            if (!sc.hasNextInt()) {
                sc.next(); // clear invalid input
                continue;
            }
            ch = sc.nextInt();
            
            if (ch == 3) {
                break;
            }

            if (ch == 1) {
                System.out.println("--------------------");
                System.out.println("\tEncrypt");
                System.out.println("--------------------");
                System.out.print("Enter Plain Text (No spaces): ");
                String pt = sc.next(); // 🔥 Uses next() to bypass all buffer bugs
                
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                
                String ct = encrypt(pt, key);
                System.out.println("Cipher Text: " + ct);
                
            } else if (ch == 2) {
                System.out.println("--------------------");
                System.out.println("\tDecrypt");
                System.out.println("--------------------");
                System.out.print("Enter Cipher Text (No spaces): ");
                String ct = sc.next(); // 🔥 Uses next() to bypass all buffer bugs
                
                System.out.print("Enter key: ");
                int key = sc.nextInt();
                
                String pt = decrypt(ct, key);
                System.out.println("Plain Text: " + pt);
            }
        }
        System.out.println("---------------------------");
        System.out.println("\t\tEXITED");
        System.out.println("---------------------------");
    }

    public static String encrypt(String pt, int key) {
        if (pt == null || pt.isEmpty()) return ""; // Guard condition
        StringBuilder ct = new StringBuilder();
        for (int i = 0; i < pt.length(); i++) {
            char mod = Character.isUpperCase(pt.charAt(i)) ? 'A' : 'a';
            char ch = (char) ((((pt.charAt(i) - mod) + key) % 26) + mod);
            ct.append(ch);
        }
        return ct.toString();
    }

    public static String decrypt(String ct, int key) {
        if (ct == null || ct.isEmpty()) return ""; // 🔥 Guard condition stops the crash
        StringBuilder pt = new StringBuilder();
        for (int i = 0; i < ct.length(); i++) {
            char mod = Character.isUpperCase(ct.charAt(i)) ? 'A' : 'a';
            char ch = (char) (((((ct.charAt(i) - mod) - (key % 26)) + 26) % 26) + mod);
            pt.append(ch);
        }
        return pt.toString();
    }
}