import java.util.Arrays;

class Solution{
    public int maxSize(int[] arr){
        Arrays.sort(arr);
        int left=0;
        int maxSize=0;
        for(int right=0;right<arr.length; right++){
            while (arr[right]-arr[left]>5) {
                left++;
            }
            maxSize=Math.max(maxSize, right-left+1);
        }
        return maxSize;
    }
}

public class test1 {
    public static void main(String[] args) {
        Solution s = new Solution();

        int[] a1 = {4, 17, 8, 24, 6, 10, 12, 9};
        System.out.println("maxSize(a1) = " + s.maxSize(a1));

        int[] a2 = {4, 6, 8, 9};
        System.out.println("maxSize(a2) = " + s.maxSize(a2));

        int[] a3 = {10, 16, 22, 28};
        System.out.println("maxSize(a3) = " + s.maxSize(a3));
    }
}
