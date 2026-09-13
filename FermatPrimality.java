import java.math.BigInteger;
import java.util.Scanner;

public class FermatPrimality {

    static boolean fermatTest(BigInteger p) {

        for (BigInteger a = BigInteger.ONE;
             a.compareTo(p) < 0;
             a = a.add(BigInteger.ONE)) {

            // Calculate (a^p) mod p
            BigInteger remainder = a.modPow(p, p);

            // Display only the remainder
            System.out.println(
                "a = " + a + " -> " + a + "^" + p +
                " mod " + p + " = " + remainder
            );

            // Fermat condition: a^p mod p = a mod p
            if (!remainder.equals(a.mod(p))) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter p: ");
        BigInteger p = sc.nextBigInteger();

        if (p.compareTo(BigInteger.TWO) < 0) {
            System.out.println("Composite");
            sc.close();
            return;
        }

        if (p.equals(BigInteger.TWO)) {
            System.out.println("Prime");
            sc.close();
            return;
        }

        if (p.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
            System.out.println("Composite");
            sc.close();
            return;
        }

        boolean result = fermatTest(p);

        if (result) {
            System.out.println("\n" + p + " is Prime");
        } else {
            System.out.println("\n" + p + " is Composite");
        }

        sc.close();
    }
}