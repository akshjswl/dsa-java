class sol{
    public void rotateArr(int[] arr, int k){
        int n=arr.length;
        k=k%n;
        if(n==0) return;
        reverse(arr,0,n-1);
        reverse(arr,k,n-1);
        reverse(arr,0,k-1);
}
    public void reverse(int[] arr, int start, int end)
    {
        while (start<end) {
            int temp=arr[start];
            arr[start]=arr[end];
            arr[end]=temp;
            start++;
            end--;
        }
    }
}
public class leftRot1pl {
    public static void main(String[] args) {
        sol s=new sol();
        int[] arr={1,2,3,4,5,6,7};
        s.rotateArr(arr,3);

        for (int i : arr) {
            System.out.print(i+" ");
        }
    }
}
