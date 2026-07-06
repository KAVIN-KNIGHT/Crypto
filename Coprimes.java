import java.util.*;
public class Coprimes {
    private static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    public static void main(String[] args) {
       Scanner sc = new Scanner (System.in);
       System.out.println("enter two nums");
       int a =sc.nextInt();
       int b =sc.nextInt();
       if (gcd(a, b) == 1) {
           System.out.println(a + " and " + b + " are coprime.");
       } else {
           System.out.println(a + " and " + b + "  not coprime.");
       }

    }
    
}
