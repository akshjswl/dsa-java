class Solution{
    public static int sLargest(int[]a, int n){
        if(n<2) return -1;
        int largest=Integer.MIN_VALUE;
        int slargest=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(a[i]>largest){
                slargest=largest;
                largest=a[i];
            }
            else if (a[i]!=largest && a[i]>slargest) {
                slargest=a[i];
            }
        }
        return slargest;
    }
}

public class secL{
    public static void main(String[] args) {
        int[] a={2,3,5,7,7,6};
        int n=a.length;
        int sL=Solution.sLargest(a,n);
        System.out.println("second largest is "+sL);
    }
}