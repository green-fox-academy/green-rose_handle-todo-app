
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Todo {
    public static String todoFileName = "todo.todo";

    public static void main(String[] args) {
        List<String> allowedArguments = List.of("-l","-a","-c", "-r","-la");
        List <String>arguments = new ArrayList<>();
        arguments.addAll(Arrays.asList(args));
        if (arguments.size()==0) PrintUsage.printUsage();
        else if (allowedArguments.contains(arguments.get(0))) {
            todo(arguments);
        } else {
            System.out.println("Can't recognize your argument: "+arguments.get(0));
            PrintUsage.printUsage();
        }
    }

    private static void todo(List<String> arguments) {
        String arg = arguments.get(0);
        switch (arg) {
            case "-l": lCase(false);
                break;
            case "-la": lCase(true);
                break;
            case "-a":
                arguments.remove(0);
                aCase(arguments);
            break;
            case "-r":
                arguments.remove(0);
                rCase(arguments);
                break;
            case "-c":
                arguments.remove(0);
                cCase(arguments);
                break;

        }
    }

    private static void aCase(List<String> args) {
        if (args.size()==0){
            System.out.println("Unable to add: no task provided");
        } else {
            FileHandling f = new FileHandling();
            for (String arg: args) {
                Task task = new Task(arg.replaceAll("^\"|\"$", ""));
                System.out.println(f.addTask(todoFileName,task)?task.task+" added.":"Something went wrong");
            }
        }
    }

    private static void lCase(boolean all){
        FileHandling f = new FileHandling();
        List<Task> tasks = f.getTasks(todoFileName);
        if (tasks.isEmpty()){
            System.out.println("No todos for today! :)");
        } else {
            int i = 1;
            for (Task task: tasks) {
                if (!task.isStatus()) System.out.println(i+" - "+task);
                else if (all) System.out.println(i+" - "+task);
                i++;
            }
        }
    }

    private static void rCase(List<String> args) {
        if (args.size()==0){
            System.out.println("Unable to remove: no index provided");
        } else {
            FileHandling f = new FileHandling();
            List<Task> tasks = f.getTasks(todoFileName);
            for (String arg: args) {
                try {
                    Integer numberOfDeletedTask=Integer.parseInt(arg);
                    if (numberOfDeletedTask>0 && numberOfDeletedTask<=tasks.size()) {
                        Task deletedTask = tasks.remove(numberOfDeletedTask-1);
                        System.out.println(f.rewriteTask(todoFileName,tasks)?deletedTask+" deleted.":"Something went wrong");
                    } else{
                        System.out.println("Unable to remove: index is out of bound");
                    }
                } catch (NumberFormatException e){
                    System.out.println("Index must be number");
                }
            }

        }
    }

    private static void cCase(List<String> args) {
        if (args.size()==0){
            System.out.println("Unable to check: no index provided");
        } else {
            FileHandling f = new FileHandling();
            List<Task> tasks = f.getTasks(todoFileName);
            for (String arg:args) {
                try {
                    Integer numberOfCheckedTask=Integer.parseInt(arg);
                    if (numberOfCheckedTask>0 && numberOfCheckedTask<=tasks.size()) {
                        tasks.get(numberOfCheckedTask-1).setStatus(true);
                        System.out.println(f.rewriteTask(todoFileName,tasks)?tasks.get(numberOfCheckedTask-1)+" checked.":"Something went wrong");
                    } else System.out.println("Unable to remove: index is out of bound.");
                } catch (NumberFormatException e){
                    System.out.println("Unable to remove: index is not a number");
                }
            }
        }
    }


}
