import java.util.Scanner;

public class asciiManipulation {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    String s=sc.nextLine();
    StringBuffer sb=new StringBuffer();
    for(int i=0;i<s.length();i++){
        char ch=s.charAt(i);
        if (ch>='A' && ch<='Z') {
            sb.append((char)(ch+32));
        }
        else if (ch>='a' && ch<='z') {
            sb.append((char)(ch-32));
        }
        else sb.append(ch);
    }
    System.out.println(sb.toString());
 }
}
