
public class factorial {
    public static void main(String[] args) {
        int result=facto(3);
        System.out.println(result);

    }
    public static int facto(int i){
        if (i!=0) {
            return i* facto(i-1);
        }
        return 1;
    }
}
