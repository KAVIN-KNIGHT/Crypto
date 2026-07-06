// Online Java Compiler
// Use this editor to write, compile and run your Java code online

import java.util.*;
import java.lang.*;

class Playfair {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int ch;
        
        while (true) {
            System.out.println("---------------------------");
            System.out.println("\tPlay Fair Cipher!");
            System.out.println("---------------------------");
            System.out.println("1.Encrypt\n2.Decrypt\n3.Exit");
            System.out.print("Enter your choice: ");
            
            // Check if there is an integer to read to prevent crashes
            if (!sc.hasNextInt()) {
                sc.next(); // clear invalid input
                continue;
            }
            ch = sc.nextInt();
            
            if (ch == 3) {
                break;
            }

            if (ch == 1) {
                System.out.println("--------------------");
                System.out.println("\tEncrypt");
                System.out.println("--------------------");
                System.out.print("Enter Plain Text (No spaces): ");
                String pt = sc.next(); // 🔥 Uses next() to bypass all buffer bugs
                pt = pt.toLowerCase();
                
                System.out.print("Enter key: ");
                String key = sc.next();
                key = key.toLowerCase();
                
                String ct = encrypt(pt, key);
                System.out.println("Cipher Text: " + ct);
                
            } else if (ch == 2) {
                System.out.println("--------------------");
                System.out.println("\tDecrypt");
                System.out.println("--------------------");
                System.out.print("Enter Cipher Text (No spaces): ");
                String ct = sc.next(); // 🔥 Uses next() to bypass all buffer bugs
                ct = ct.toLowerCase();
                
                System.out.print("Enter key: ");
                String key = sc.next();
                key = key.toLowerCase();
                
                String pt = decrypt(ct, key);
                System.out.println("Plain Text: " + pt);
            }
        }
        System.out.println("---------------------------");
        System.out.println("\t\tEXITED");
        System.out.println("---------------------------");
    }

    public static String encrypt(String pt, String key) {
        if (pt == null || pt.isEmpty()) return ""; // Guard condition
        int[] alpha = new int[26];
        char[][] cipherMatrix = new char[5][5];
        Map<Character,Integer[]> charIndex = new HashMap<>();
        int m=0;
        int n=0;
        for(char ch:key.toCharArray()){
            if(alpha[ch-'a'] == 1) continue;
            if(ch == 'y'||ch == 'z'){
               alpha['y'-'a'] = 1;
               alpha['z'-'a'] = 1;
               cipherMatrix[m][n] = ch;
               charIndex.put('y',new Integer[]{m,n});
               charIndex.put('z',new Integer[]{m,n});
            }else{
                alpha[ch-'a'] = 1;
                cipherMatrix[m][n] = ch;
                charIndex.put(ch,new Integer[]{m,n});
            }
            n++;
            if(n == 5){
                m++;
                n = 0;
            }
        }
        int k = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(cipherMatrix[i][j]!=0) continue;
                while(k<26&&alpha[k] == 1) k++;
                if(k<26){
                    char ch = (char)(k+'a');
                    cipherMatrix[i][j] = ch;
                    charIndex.put(ch,new Integer[]{i,j});
                    alpha[k] = 1;
                }
            }
        }
        if(cipherMatrix[4][4] == 'y'){
            charIndex.put('z',new Integer[]{4,4});
        }
        
        
        //pt digram
        StringBuilder digram = new StringBuilder();
        char filler = 'x';
        int ptr1 = 0;
        int ptr2 = 1;
        while(ptr1<pt.length()){
            digram.append(pt.charAt(ptr1));
            if(ptr2>=pt.length()) break;
            if(pt.charAt(ptr1) == pt.charAt(ptr2)){
                digram.append(filler);
                ptr1 = ptr2;
                ptr2++;
                continue;
            }
            digram.append(pt.charAt(ptr2));
            ptr1+=2;
            ptr2+=2;
        }
        int len = digram.length();
        if((len&1) == 1){
            digram.append(filler);
        }
        int r = 0;
        int c = 1;
        StringBuilder ct = new StringBuilder();
        while(r<digram.length()){
            char ch1 = digram.charAt(r);
            char ch2 = digram.charAt(c);
            
            Integer[] ch1_idx = charIndex.get(ch1);
            Integer[] ch2_idx = charIndex.get(ch2);
            
            int act_idx1 = ch1_idx[0];
            int act_idx2 = ch2_idx[1];
            int act_idx3 = ch2_idx[0];
            int act_idx4 = ch1_idx[1];
            
            if(ch1_idx[0] == ch2_idx[0]){
                act_idx1 = ch1_idx[0];
                act_idx2 = (ch1_idx[1]+1)%5;
                act_idx3 = ch2_idx[0];
                act_idx4 = (ch2_idx[1]+1)%5;
            }else if(ch1_idx[1] == ch2_idx[1]){
                act_idx1 = (ch1_idx[0]+1)%5;
                act_idx2 = ch1_idx[1];
                act_idx3 = (ch2_idx[0]+1)%5;
                act_idx4 = ch2_idx[1];
            }
            
            char ct_ch1 = cipherMatrix[act_idx1][act_idx2];
            char ct_ch2 = cipherMatrix[act_idx3][act_idx4];
            
            ct.append(ct_ch1);
            ct.append(ct_ch2);
            r+=2;
            c+=2;
        }
        return ct.toString();
    }

    public static String decrypt(String ct, String key) {
        if (ct == null || ct.isEmpty()) return ""; // 🔥 Guard condition stops the crash
        int[] alpha = new int[26];
        char[][] cipherMatrix = new char[5][5];
        Map<Character,Integer[]> charIndex = new HashMap<>();
        int m=0;
        int n=0;
        for(char ch:key.toCharArray()){
            if(alpha[ch-'a'] == 1) continue;
            if(ch == 'y'||ch == 'z'){
               alpha['y'-'a'] = 1;
               alpha['z'-'a'] = 1;
               cipherMatrix[m][n] = ch;
               charIndex.put('y',new Integer[]{m,n});
               charIndex.put('z',new Integer[]{m,n});
            }else{
                alpha[ch-'a'] = 1;
                cipherMatrix[m][n] = ch;
                charIndex.put(ch,new Integer[]{m,n});
            }
            n++;
            if(n == 5){
                m++;
                n = 0;
            }
        }
        int k = 0;
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(cipherMatrix[i][j]!=0) continue;
                while(k<26&&alpha[k] == 1) k++;
                if(k<26){
                    char ch = (char)(k+'a');
                    cipherMatrix[i][j] = ch;
                    charIndex.put(ch,new Integer[]{i,j});
                    alpha[k] = 1;
                }
            }
        }
        if(cipherMatrix[4][4] == 'y'){
            charIndex.put('z',new Integer[]{4,4});
        }
        
        int r = 0;
        int c = 1;
        StringBuilder pt = new StringBuilder();
        while(r<ct.length()){
            char ch1 = ct.charAt(r);
            char ch2 = ct.charAt(c);
            
            Integer[] ch1_idx = charIndex.get(ch1);
            Integer[] ch2_idx = charIndex.get(ch2);
            
            int act_idx1 = ch1_idx[0];
            int act_idx2 = ch2_idx[1];
            int act_idx3 = ch2_idx[0];
            int act_idx4 = ch1_idx[1];
            
            if(ch1_idx[0] == ch2_idx[0]){
                act_idx1 = ch1_idx[0];
                act_idx2 = (ch1_idx[1]+4)%5;
                act_idx3 = ch2_idx[0];
                act_idx4 = (ch2_idx[1]+4)%5;
            }else if(ch1_idx[1] == ch2_idx[1]){
                act_idx1 = (ch1_idx[0]+4)%5;
                act_idx2 = ch1_idx[1];
                act_idx3 = (ch2_idx[0]+4)%5;
                act_idx4 = ch2_idx[1];
            }
            
            char pt_ch1 = cipherMatrix[act_idx1][act_idx2];
            char pt_ch2 = cipherMatrix[act_idx3][act_idx4];
            
            pt.append(pt_ch1);
            pt.append(pt_ch2);
            r+=2;
            c+=2;
        }
        return pt.toString();
    }
}