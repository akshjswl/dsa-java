// package Strings;

import java.util.Scanner;

public class rotCheck {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s1=sc.nextLine();
        String s2=sc.nextLine();
        if (s1.length()==s2.length()&& (s1+s1).contains(s2)) {
            System.out.println(true);
        }
        else System.out.println(false);
    }
}
