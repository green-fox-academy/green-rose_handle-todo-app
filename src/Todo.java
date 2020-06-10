import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Todo {
    public static void main(String[] args) {
        List<String> allowedArguments = new ArrayList<String>();
        allowedArguments.add("-l");
        if (args.length==0) PrintUsage.printUsage();
        else if (allowedArguments.contains(args[0])) {
            // Todo Some Logic here
        } else {
            System.out.println("Cant recognize your argument: "+args[0]);
            PrintUsage.printUsage();
        }
    }
}
