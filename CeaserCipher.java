import java.util.*;
public class CeaserCipher {
    public static void main(String[] args)
    {
        //Encryption
        Scanner sc = new Scanner(System.in);
        System.out.println(" text : ");
        String str = sc.nextLine();
        System.out.println("key : ");
        int key = sc.nextInt();

        StringBuilder encrypted = new StringBuilder();
        for( char ch : str.toCharArray())
        {
            ch =(char)((ch -'a' + key) % 26 + 'a');
           encrypted.append(ch); 
        }
        System.out.println("encrypted text : " + encrypted);


        //Decryption
        StringBuilder decrypted = new StringBuilder();
        for( char ch : encrypted.toString().toCharArray())
        {
            ch =(char)((ch -'a' - key + 26) % 26 + 'a');
           decrypted.append(ch); 
        }
         System.out.println("decrypted text : " + decrypted);
    }
}