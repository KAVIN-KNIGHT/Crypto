import java.util.*;

public class Gcd {
    private static int gcd(int a, int b) { // recursive 
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static int gcd_iter(int a, int b){ // iterative
        while(b!=0)
        {
            int t = b;
            b=a%b;
            a=t;
        }
        return a;
    }

    public static void main(String[] args) {

        int z = gcd(13, 26);
        System.out.println(z);
        System.out.println(gcd_iter(13, 26));
    }
}