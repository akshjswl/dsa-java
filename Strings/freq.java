// package Strings;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class freq {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        Map<Character,Integer> map=new HashMap<>();
        for (char c : str.toCharArray()) {
            map.put(c, map.getOrDefault(c,0) +1);
        }
        System.out.println(map);
        sc.close();
    }
}