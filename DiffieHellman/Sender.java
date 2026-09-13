import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

public class Sender {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            System.out.print("Enter prime number q : ");
            BigInteger q = sc.nextBigInteger();

            System.out.print("Enter primitive root alpha : ");
            BigInteger alpha = sc.nextBigInteger();

            System.out.print("Enter private key of User A : ");
            BigInteger Xa = sc.nextBigInteger();

            DiffieHellman dh = new DiffieHellman(q, alpha);

            // Generate public key of A
            BigInteger Ya = dh.generatePublicKey(Xa);

            System.out.println("\nPublic Key of User A : " + Ya);

            Socket socket = new Socket("localhost", 5000);

            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());

            DataInputStream in =
                    new DataInputStream(socket.getInputStream());

            // Send q, alpha and A's public key
            out.writeUTF(q.toString());
            out.writeUTF(alpha.toString());
            out.writeUTF(Ya.toString());

            out.flush();

            // Receive B's public key
            BigInteger Yb =
                    new BigInteger(in.readUTF());

            System.out.println("Public Key of User B : " + Yb);

            // Calculate shared key
            BigInteger Ka =
                    dh.generateSharedKey(Yb, Xa);

            System.out.println(
                    "Shared Key calculated by A : " + Ka
            );

            // Receive confirmation
            String response = in.readUTF();

            System.out.println("Receiver : " + response);

            socket.close();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        sc.close();
    }
}