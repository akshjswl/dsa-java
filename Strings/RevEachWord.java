// package Strings;

import java.util.Scanner;

public class RevEachWord {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String[] word=s.split("\\s+");
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < word.length; i++) {
            StringBuilder rev=new StringBuilder(word[i]);
            rev.reverse();
            sb.append(rev);
            if(i<word.length-1) sb.append(" ");
        }
        System.out.println(sb.toString());
        sc.close();
    }
}
