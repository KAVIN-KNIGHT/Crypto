import java.util.*;

public class Playfair52 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the key: ");
        String key = sc.nextLine();
        key = key.toLowerCase().replace('y','z');

        System.out.print("Enter the plane text: ");
        String pT = sc.nextLine();
        pT = pT.toLowerCase().replace('y','z');
        
        LinkedHashSet<Character> setkey = new LinkedHashSet<>();
        for(char c : key.toCharArray()) {
            if(setkey.contains(c)== false) {
                setkey.add(c);
            }
        }
        String alpha ="abcdefghijklmnopqrstuvwxz";
        for(char c : alpha.toCharArray()) {
            if(setkey.contains(c)== false) {
                setkey.add(c);
            }
        }
        char[][] matrix = new char[5][5];
        Iterator<Character> it = setkey.iterator();
        for(int i=0;i<5;i++) {
            for(int j=0;j<5;j++) {
                matrix[i][j] = it.next();
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        StringBuilder ptxt = new StringBuilder(pT);
        if(ptxt.length()%2!=0) {
            ptxt.append('x');
        }

        for(int i=0;i<ptxt.length();i+=2)
        {
            if(ptxt.charAt(i) == ptxt.charAt(i+1)) {
                ptxt.insert(i+1, 'x');
            } 
        }

        if(ptxt.length()%2!=0) {
            ptxt.append('x');
        }
        
        for(int i=0;i<ptxt.length();i+=3)
        {
            ptxt.insert(i, ' ');
        }
        System.out.println(ptxt);
        encrypt(matrix, ptxt.toString());
    }
    public  static void encrypt(char[][] matrix, String ptxt) {
        StringBuilder cT = new StringBuilder();
        for(int i=0;i<ptxt.length();i+=3) {
            char a = ptxt.charAt(i);
            char b = ptxt.charAt(i+1);
            int row1=0,row2=0,col1=0,col2=0;
            for(int j=0;j<5;j++) {
                for(int k=0;k<5;k++) {
                    if(matrix[j][k] == a) {
                        row1 = j;
                        col1 = k;
                    }
                    if(matrix[j][k] == b) {
                        row2 = j;
                        col2 = k;
                    }
                }
            }
            if(row1 == row2) {
                cT.append(matrix[row1][(col1+1)%5]);
                cT.append(matrix[row2][(col2+1)%5]);
            } else if(col1 == col2) {
                cT.append(matrix[(row1+1)%5][col1]);
                cT.append(matrix[(row2+1)%5][col2]);
            } else {
                cT.append(matrix[row1][col2]);
                cT.append(matrix[row2][col1]);
            }
        }
        System.out.println(cT);
    }
}