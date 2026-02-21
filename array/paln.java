class palindrome{
    public boolean isPalindrome(int n){
        int revNum=0;
        int dup=n;
        while(n>0){
            int ld=n%10;
            revNum=(revNum*10)+ld;
            n=n/10;

        }
        return dup==revNum;
    }
}
public class paln {
    public static void main(String[] args) {
        int number = 45554; // Example number
        palindrome obj = new palindrome();
        if (obj.isPalindrome(number)) { // Check if the number is a palindrome
            System.out.println(number + " is a palindrome.");
        } else {
            System.out.println(number + " is not a palindrome.");
        }
    }
}
