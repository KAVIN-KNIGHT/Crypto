import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.util.Scanner;

public class RsaClient {

    // Check whether a number is prime
    static boolean isPrime(BigInteger n) {

        if (n.compareTo(BigInteger.TWO) < 0)
            return false;

        return n.isProbablePrime(20);
    }

    // Find a suitable value of e
    static BigInteger findE(BigInteger phi) {

        BigInteger e = BigInteger.valueOf(2);

        while (e.compareTo(phi) < 0) {

            if (e.gcd(phi).equals(BigInteger.ONE))
                return e;

            e = e.add(BigInteger.ONE);
        }

        return null;
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Input p
        BigInteger p;

        while (true) {

            System.out.print("Enter prime number p: ");
            p = sc.nextBigInteger();

            if (isPrime(p))
                break;

            System.out.println("p is not prime. Enter a prime number.");
        }

        // Input q
        BigInteger q;

        while (true) {

            System.out.print("Enter prime number q: ");
            q = sc.nextBigInteger();

            if (!isPrime(q)) {
                System.out.println("q is not prime. Enter a prime number.");
            }
            else if (p.equals(q)) {
                System.out.println("p and q must be different.");
            }
            else {
                break;
            }
        }

        // Calculate n
        BigInteger n = p.multiply(q);

        // Calculate phi(n)
        BigInteger phi = p.subtract(BigInteger.ONE)
                          .multiply(q.subtract(BigInteger.ONE));

        // Find e
        BigInteger e = findE(phi);

        // Input message
        System.out.print("Enter a single character to encrypt: ");
        char message = sc.next().charAt(0);

        // Convert character to number
        BigInteger m = BigInteger.valueOf((int) message);

        // Message must be smaller than n
        if (m.compareTo(n) >= 0) {

            System.out.println(
                "Error: Message value must be smaller than n (" + n + ")."
            );

            sc.close();
            return;
        }

        // Encrypt
        BigInteger encrypted = m.modPow(e, n);

        System.out.println("\n--- RSA Encryption ---");
        System.out.println("p       = " + p);
        System.out.println("q       = " + q);
        System.out.println("n       = " + n);
        System.out.println("phi(n)  = " + phi);
        System.out.println("e       = " + e);
        System.out.println("Message = " + message);
        System.out.println("ASCII   = " + m);
        System.out.println("Encrypted Message = " + encrypted);

        // Connect to server
        Socket socket = new Socket("localhost", 5000);

        DataInputStream in =
                new DataInputStream(socket.getInputStream());

        DataOutputStream out =
                new DataOutputStream(socket.getOutputStream());

        // Send RSA values to server
        out.writeUTF(p.toString());
        out.writeUTF(q.toString());
        out.writeUTF(e.toString());
        out.writeUTF(encrypted.toString());
        out.flush();

        // Receive decrypted message
        String decrypted = in.readUTF();

        System.out.println("Decrypted Message received from Server = "
                           + decrypted);

        socket.close();
        sc.close();
    }
}