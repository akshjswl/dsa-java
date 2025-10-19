// package Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class firstNonRepeatingChar {
    public static String firstNonRepeatingCharacter(String s){
         Map<Character,Integer>map=new HashMap<>();
        for (int i=0;i<s.length();i++) {
            char c=s.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (int i = 0; i < s.length(); i++) {
            char c=s.charAt(i);
            if(map.get(c)==1) {
                return String.valueOf(c);
            }
        }
        return null;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s=sc.nextLine();
        System.out.println(firstNonRepeatingCharacter(s));
        sc.close();
    }
}
