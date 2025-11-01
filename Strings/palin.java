public class palin {
    public static void main(String[] args) {
        String s="Madam";
        String rev= new StringBuffer(s).reverse().toString();
        System.out.println(s.equalsIgnoreCase(rev));
    }
}
