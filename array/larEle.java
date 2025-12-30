
class sol{
        public static int largElem(int[] arr,int n){
        int max=arr[0];
        for (int i = 1; i <n; i++) {
            if (arr[i]>max) {
                max=arr[i];
            }
        }
        return max;
    }
}
public class larEle {
    public static void main(String[] args) {
        int[] arr={5,7,4,8,2};
        int n=arr.length;
        int max=sol.largElem(arr,n);
        System.out.println("the largest element: "+max);
    }
}
