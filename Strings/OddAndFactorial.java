// store n numbers of positive integer in computer memory 
//collect the odd numbers from those elements and store them in a seprate loaction
//find out the factorial of even posn 

import java.util.*;
public class OddAndFactorial {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        System.out.println("Enter " + n + " positive integers:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        List<Integer> oddNumbers = new ArrayList<>();
        for (int num : arr) {
            if (num % 2 != 0) {
                oddNumbers.add(num);
            }
        }

        List<Long> factorials = new ArrayList<>();
        for (int i = 0; i <n; i++) { 
            if (i % 2 == 0) {
                factorials.add(factorial(arr[i]));
            }
        }
        System.out.println("\nOriginal array: " + Arrays.toString(arr));
        System.out.println("Odd numbers: " + oddNumbers);
        System.out.println("Factorials of even-placed numbers: " + factorials);
        sc.close();
    }
    public static long factorial(int num) {
        long fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        return fact;
    }
}