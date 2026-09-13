import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Scanner;

public class ElGamalReceiver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println("Receiver started...");
            System.out.println("Waiting for sender...");

            Socket socket = serverSocket.accept();

            System.out.println("Sender connected!");

            DataInputStream in = new DataInputStream(socket.getInputStream());

            // Receive p
            BigInteger p = new BigInteger(in.readUTF());

            // Receive g
            BigInteger g = new BigInteger(in.readUTF());

            // Receive private key x
            BigInteger x = new BigInteger(in.readUTF());

            // Receive ciphertext c1
            BigInteger c1 = new BigInteger(in.readUTF());

            // Receive ciphertext c2
            BigInteger c2 = new BigInteger(in.readUTF());

            System.out.println("\nReceived values:");
            System.out.println("p  = " + p);
            System.out.println("g  = " + g);
            System.out.println("x  = " + x);
            System.out.println("c1 = " + c1);
            System.out.println("c2 = " + c2);

            // Calculate shared secret
            // s = c1^x mod p
            BigInteger s = c1.modPow(x, p);

            // Calculate inverse of shared secret
            BigInteger sInverse = s.modInverse(p);

            // Decrypt
            // M = c2 * s^-1 mod p
            BigInteger message = c2.multiply(sInverse).mod(p);

            System.out.println("Decrypted Message = " + message);

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        sc.close();
    }
}