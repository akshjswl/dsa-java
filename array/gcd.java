class Solution{
    public static int findGcd(int a, int b){
        while(a>0 && b>0){
            if(a>b){
                a=a%b;
            }
            else{
                b=b%a;
            }
        }
        if(a==0){
            return b;
        }
        return a;
    }
}

public interface gcd {
    public static void main(String[] args) {
          int n1 = 9, n2 = 12;
          Solution so=new Solution();
         System.out.println(so.findGcd(n1,n2)); 
    }
}
