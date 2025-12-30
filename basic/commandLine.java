public class commandLine {
    public static void main(String[] args) {
        if (args.length==0) {
            System.out.println("no args");
        }
        else{
            System.out.println("the args are:");

            for (int i = 0; i < args.length; i++) {
                System.out.println("Argument "+i+":"+args[i]);
            }
        }
    }
}