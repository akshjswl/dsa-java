public class selection{
    public static void selection_sort(int[] array){
        for (int i = 0; i < array.length-1; i++) {
            int mini=i;
            for(int j=i+1;j<array.length;j++){
                if (array[j]<array[mini]) {
                    mini=j;
                }
            }
            int temp=array[mini];
            array[mini]=array[i];
            array[i]=temp;
        }
        System.out.println("After selection sort:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
    public static void insertion_sort(int[] array){
        int n=array.length;
        for (int i = 1; i < n; i++) {
            int j=i-1;
            int key=array[i];
            while (j>=0 && array[j]>key) {
                array[j+1]=array[j];
                j--;
            }
            array[j+1]=key;
        }
        
    }
    public static void main(String[] args) {
        int[] arr = {13, 46, 24, 52, 20, 9};
        System.out.println("Before selection sort:");
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        selection_sort(arr);
        System.out.println("\n"+"Insetion sort");
        insertion_sort(arr);
        for (int num : arr) {
            System.out.print(+num + " ");
        }
        }

    }
