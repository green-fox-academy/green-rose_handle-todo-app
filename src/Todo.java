import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Todo {
    public static String todoFileName = "todo.todo";
    //public List<String> arguments = new ArrayList<>();

    public static void main(String[] args) {
        List<String> allowedArguments = List.of("-l","-a","-c", "-r");
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
            case "-l": lCase();
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
            System.out.println("Input new task (or '-q' for quit):");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            args.add(0,input);
            aCase(args);
        } else if (args.get(0).compareTo("-q")!=0) {
            FileHandling f = new FileHandling();
            Task task = new Task(args.get(0).replaceAll("^\"|\"$", ""));
            // todo: handling more args (no "")
            System.out.println(f.addTask(todoFileName,task)?task.task+" added.":"Something went wrong");
        }
    }

    private static void lCase(){
        FileHandling f = new FileHandling();
        List<Task> tasks = new ArrayList<>();
        tasks = f.getTasks(todoFileName);
        if (tasks.isEmpty()){
            System.out.println("No todos for today! :)");
        } else {
            int i = 1;
            for (Task task: tasks) {
                System.out.println(i+" - "+task);
                i++;
            }
        }
    }

    private static void rCase(List<String> args) {
        if (args.size()==0){
            System.out.println("Remove which task (or '-q' for quit):");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            args.add(0,input);
            rCase(args);
        } else if (args.get(0).compareTo("-q")!=0) {
            FileHandling f = new FileHandling();
            List<Task> tasks = new ArrayList<>();
            tasks = f.getTasks(todoFileName);
            Integer numberOfDeletedTask=Integer.parseInt(args.get(0));
            if (numberOfDeletedTask>0 && numberOfDeletedTask<=tasks.size()) {
                Task deletedTask = tasks.remove(numberOfDeletedTask-1);
                System.out.println(f.rewriteTask(todoFileName,tasks)?deletedTask+" deleted.":"Something went wrong");
            }
        }
    }

    private static void cCase(List<String> args) {
        if (args.size()==0){
            System.out.println("Check which task (or '-q' for quit):");
            Scanner s = new Scanner(System.in);
            String input = s.nextLine();
            args.add(0,input);
            cCase(args);
        } else if (args.get(0).compareTo("-q")!=0) {
            FileHandling f = new FileHandling();
            List<Task> tasks = new ArrayList<>();
            tasks = f.getTasks(todoFileName);
            Integer numberOfCheckedTask=Integer.parseInt(args.get(0));
            if (numberOfCheckedTask>0 && numberOfCheckedTask<=tasks.size()) {
               tasks.get(numberOfCheckedTask-1).setStatus(true);
               System.out.println(f.rewriteTask(todoFileName,tasks)?tasks.get(numberOfCheckedTask-1)+" checked.":"Something went wrong");
            }
        }
    }


}
