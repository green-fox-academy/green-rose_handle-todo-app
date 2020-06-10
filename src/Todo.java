import java.util.Scanner;

public class Todo {
    public static void main(String[] args) {

        if (args.length==0) PrintUsage.printUsage();
        Scanner s = new Scanner(System.in);
        String question = s.nextLine();
    }
}
