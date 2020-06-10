import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Todo {

    public static void main(String[] args) {
        List<String> allowedArguments = Arrays.asList("-l");

        if (args.length==0) PrintUsage.printUsage();
        else if (allowedArguments.contains(args[0])) {
            todo(args[0]);
        } else {
            System.out.println("Can't recognize your argument: "+args[0]);
            PrintUsage.printUsage();
        }
    }

    private static void todo(String task) {
        switch (task) {
            case "-l": lCase();
        }
    }

    private static void lCase(){
        FileHandling f = new FileHandling();
        List<Task> tasks = new ArrayList<>();
        tasks = f.getTasks("todo.todo");
        if (tasks.isEmpty()){
            System.out.println("No todos for today! :)");
        } else {
            int i = 1;
            for (Task task: tasks) {
                System.out.println(i+" - "+task.task);
                i++;
            }
        }
    }
}
