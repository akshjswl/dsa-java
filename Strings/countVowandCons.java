package Strings;
import java.util.Scanner;

public class countVowandCons {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        s=s.toLowerCase();
        int vowels=0;
        int consonats=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            if (ch>='a' && ch<='z') {
                if (ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u') {
                    vowels++;
                }
                else consonats++;
            }
        }
        System.out.println("vowels:"+vowels);
        System.out.println("consonats:"+consonats);
        sc.close();
    }
}
