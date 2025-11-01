import java.util.HashMap;
import java.util.Scanner;

public class anagramCheck {
    public static boolean isanagram(String s1,String s2){
        if (s1.length()!=s2.length()) {
            return false;
        }
        HashMap<Character,Integer>map=new HashMap<>();
        HashMap<Character,Integer>map2=new HashMap<>();
        for (char c  : s1.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0)+1);
        }
        for (char c  : s2.toCharArray()) {
            map2.put(c, map2.getOrDefault(c, 0)+1);
        }
        return map.equals(map2);
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s1=sc.nextLine();
        String s2=sc.nextLine();

        if (isanagram(s1, s2)) {
            System.out.println("it is anagram");
        }else{
            System.out.println("not anagram");
        }
        sc.close();
    }
}
