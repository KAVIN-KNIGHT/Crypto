import java.util.*;

public class MultiplicativeInverse {

    static int extendedEuclid(int a, int m) {

        int m0 = m;
        int x0 = 0, x1 = 1;

        if (m == 1)
            return 0;

        while (a > 1) {

            int q = a / m;

            int temp = m;

            m = a % m;
            a = temp;

            temp = x0;

            x0 = x1 - q * x0;
            x1 = temp;
        }

        if (x1 < 0)
            x1 += m0;

        return x1;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Number: ");
        int a = sc.nextInt();

        System.out.print("Enter Modulus: ");
        int m = sc.nextInt();

        if (gcd(a, m) != 1) {
            System.out.println("Multiplicative Inverse does not exist.");
        } else {
            int inverse = extendedEuclid(a, m);
            System.out.println("Multiplicative Inverse = " + inverse);
        }
    }

    static int gcd(int a, int b) {

        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }

        return a;
    }
}