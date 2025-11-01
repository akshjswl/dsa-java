// package Strings;
import java.util.Scanner;

public class countWords {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        s=s.trim();
        if(s.isEmpty()){
            System.out.println("0");
        }else{
            String[] words=s.split("\\s+");
            System.out.println("total words:"+words.length);
        }
        sc.close();
    }
}
