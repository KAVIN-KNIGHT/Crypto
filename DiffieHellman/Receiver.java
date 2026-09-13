import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Receiver {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            ServerSocket serverSocket = new ServerSocket(5000);

            System.out.println(
                    "Receiver waiting for connection..."
            );

            Socket socket = serverSocket.accept();

            DataInputStream in =
                    new DataInputStream(socket.getInputStream());

            DataOutputStream out =
                    new DataOutputStream(socket.getOutputStream());

            // Receive q, alpha and A's public key
            BigInteger q =
                    new BigInteger(in.readUTF());

            BigInteger alpha =
                    new BigInteger(in.readUTF());

            BigInteger Ya =
                    new BigInteger(in.readUTF());

            System.out.println("Public Key of User A : " + Ya);

            System.out.print(
                    "Enter private key of User B : "
            );

            BigInteger Xb = sc.nextBigInteger();

            DiffieHellman dh =
                    new DiffieHellman(q, alpha);

            // Generate B's public key
            BigInteger Yb =
                    dh.generatePublicKey(Xb);

            System.out.println(
                    "Public Key of User B : " + Yb
            );

            // Calculate shared key
            BigInteger Kb =
                    dh.generateSharedKey(Ya, Xb);

            System.out.println(
                    "Shared Key calculated by B : " + Kb
            );

            // Send B's public key
            out.writeUTF(Yb.toString());

            if (Kb != null) {
                out.writeUTF(
                        "Shared Key calculated by B = " + Kb
                );
            }

            out.flush();

            socket.close();
            serverSocket.close();

        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
        }

        sc.close();
    }
}