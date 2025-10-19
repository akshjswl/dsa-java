public class bubble {
    public static void main(String[] args) {
        int arr[]={8,6,9,3,5,2,1};
        int size=arr.length;
        int temp=0;
        System.out.println("array before sorting:");
        for(int num:arr){
            System.out.print(num+" ");
        }
        for(int i=0;i<size;i++){
            for(int j=0;j<size-i-1;j++){
                if (arr[j]>arr[j+1]) {
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
            System.out.println();
            for (int num : arr) {
                System.out.print(num+" ");
            }
        }
    }
}
