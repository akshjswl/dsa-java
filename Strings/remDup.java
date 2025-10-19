// package Strings;

import java.util.Scanner;

public class remDup {
    public static String removeDuplicates(String s){
    if(s==null) return null;
    StringBuilder sb=new StringBuilder();
    for(int i=0;i<s.length();i++){
        char c=s.charAt(i);
        if(sb.indexOf(String.valueOf(c))==-1){
            sb.append(c);
        }
    }
    return sb.toString();
}
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(removeDuplicates(s));
        sc.close();
    }
}
