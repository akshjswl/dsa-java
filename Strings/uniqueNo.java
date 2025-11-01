import java.util.Scanner;

public class uniqueNo {
    public static boolean uniqEle(int num){
        boolean[] seen= new boolean[10];
        while (num>0) {
            int digit=num%10;
            if (seen[digit]) return false;
            seen[digit]=true;
            num/=10;
        }
        return true;
    }
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        int count=0;
        for(int i=n1;i<=n2;i++){
            if (uniqEle(i)) count++;
        }
        System.out.println(count);
        sc.close();
    }
}
