import java.io.*;
import java.net.*;
import java.math.BigInteger;
import java.util.Scanner;

public class RsaServer {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        // Server starts
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("RSA Server started...");
        System.out.println("Waiting for Client...");

        Socket socket = serverSocket.accept();
        System.out.println("Client connected.");

        DataInputStream in = new DataInputStream(socket.getInputStream());
        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        // Receive p, q and encrypted message from client
        BigInteger p = new BigInteger(in.readUTF());
        BigInteger q = new BigInteger(in.readUTF());
        BigInteger e = new BigInteger(in.readUTF());
        BigInteger encrypted = new BigInteger(in.readUTF());

        // Calculate n and phi
        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE)
                          .multiply(q.subtract(BigInteger.ONE));

        // Calculate private key d
        BigInteger d = e.modInverse(phi);

        System.out.println("\n--- RSA Parameters ---");
        System.out.println("p       = " + p);
        System.out.println("q       = " + q);
        System.out.println("n       = " + n);
        System.out.println("phi(n)  = " + phi);
        System.out.println("e       = " + e);
        System.out.println("d       = " + d);

        // Decrypt
        BigInteger decrypted = encrypted.modPow(d, n);

        char message = (char) decrypted.intValue();

        System.out.println("\nEncrypted Message = " + encrypted);
        System.out.println("Decrypted Message = " + message);

        // Send decrypted message back to client
        out.writeUTF(String.valueOf(message));
        out.flush();

        socket.close();
        serverSocket.close();
        sc.close();
    }
}