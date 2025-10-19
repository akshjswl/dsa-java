public class selection{
    public static void main(String[] args) {
        int arr[]={8,6,9,3,5,2,1};
        int size=arr.length;
        int temp=0;
        int minIndex=-1;
        System.out.println("array before sorting:");
        for(int num:arr){
            System.out.print(num+" ");
        }
        for(int i=0;i<size-1;i++){
            minIndex=i;
            for(int j=i+1;j<size;j++){
                if (arr[minIndex]>arr[j]) 
                    minIndex=j;
                }
                temp=arr[minIndex];
                arr[minIndex]=arr[i];
                arr[i]=temp;
                System.out.println();
                for(int num:arr){
                    System.out.print(num+" ");
                }
            }
        }

    }
