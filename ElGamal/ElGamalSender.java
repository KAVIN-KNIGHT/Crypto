import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class ElGamalSender {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            Socket socket = new Socket("localhost", 5000);

            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());

            System.out.println("Connected to receiver.");

            // Enter p
            System.out.print("Enter prime number p: ");
            BigInteger p = sc.nextBigInteger();

            // Check p
            if (!p.isProbablePrime(10)) {
                System.out.println("p must be a prime number.");
                return;
            }

            // Enter g
            System.out.print("Enter primitive root g: ");
            BigInteger g = sc.nextBigInteger();

            if (g.compareTo(BigInteger.ONE) <= 0 ||
                g.compareTo(p) >= 0) {

                System.out.println("g must satisfy 1 < g < p.");
                return;
            }

            // Enter private key x
            System.out.print("Enter private key x: ");
            BigInteger x = sc.nextBigInteger();

            if (x.compareTo(BigInteger.ONE) <= 0 ||
                x.compareTo(p.subtract(BigInteger.ONE)) >= 0) {

                System.out.println("x must satisfy 1 < x < p-1.");
                return;
            }

            // Calculate public key
            // y = g^x mod p
            BigInteger y = g.modPow(x, p);

            System.out.println("Public Key y = " + y);

            // Enter message
            System.out.print("Enter message M: ");
            BigInteger message = sc.nextBigInteger();

            // Check message
            if (message.compareTo(BigInteger.ONE) < 0 ||
                message.compareTo(p) >= 0) {

                System.out.println("Message must satisfy 1 <= M < p.");
                return;
            }

            // Enter random key k
            System.out.print("Enter random key k: ");
            BigInteger k = sc.nextBigInteger();

            if (k.compareTo(BigInteger.ONE) <= 0 ||
                k.compareTo(p.subtract(BigInteger.ONE)) >= 0) {

                System.out.println("k must satisfy 1 < k < p-1.");
                return;
            }

            // Encryption
            // c1 = g^k mod p
            BigInteger c1 = g.modPow(k, p);

            // c2 = M * y^k mod p
            BigInteger c2 =
                    message.multiply(y.modPow(k, p)).mod(p);

            System.out.println("\nEncrypted Message:");
            System.out.println("c1 = " + c1);
            System.out.println("c2 = " + c2);

            // Send values to receiver
            out.writeUTF(p.toString());
            out.writeUTF(g.toString());
            out.writeUTF(x.toString());
            out.writeUTF(c1.toString());
            out.writeUTF(c2.toString());

            out.flush();

            System.out.println("\nEncrypted data sent to receiver.");

            socket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}