import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandling {
    public List<Task> getTasks (String fileName){
        List<Task> tasks= new ArrayList<Task>();
        try {
            File myObj = new File(fileName);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                tasks.add(new Task(myReader.nextLine()));
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred while reading the file: "+ fileName);
            System.out.println("Your path is :"+Paths.get("").toAbsolutePath().toString());
        }
        return (tasks);
    }

    public boolean addTask(String fileName, Task task){
        return true;
    }
}
