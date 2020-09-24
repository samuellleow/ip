import Task.Task;
import Task.ToDo;
import Task.Deadline;
import Task.Events;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static final String ERROR_MESSAGE_IOEXCEPTION = "IOException error has occurred";
    private static final String FILE_DIRECTORY = "data/";
    private static final String FILE_PATH = "data/tasks.txt";

    public Storage(String filePath) {
        checkSpecificFolderExist();
        try {
            File dataListFile = checkFileExists(filePath);
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE_IOEXCEPTION);
        }
    }

    private void checkSpecificFolderExist() {
        File folder = new File(FILE_DIRECTORY);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private File checkFileExists(String dataListFilePath) throws IOException {
        File dataListFile = new File(dataListFilePath);
        if (!dataListFile.exists()) {
            dataListFile.createNewFile();
        }
        return dataListFile;
    }

    public void loadDataList(TaskList tasks) {
        try {
            File dataListFile = checkFileExists(FILE_PATH);
            readDataList(dataListFile, tasks);
        } catch (IOException e) {
            System.out.println(ERROR_MESSAGE_IOEXCEPTION);
        }
    }

    private void readDataList(File dataListFile, TaskList tasks) throws FileNotFoundException {
        Scanner fileScanner = new Scanner(dataListFile);
        while (fileScanner.hasNextLine()) {
            String currentLine = fileScanner.nextLine();
            if (currentLine.isBlank()) {
                continue;
            }
            String taskType = currentLine.split(" \\| ")[0];
            String taskDescription = currentLine.split(" \\| ")[2];
            String isTaskDone = currentLine.split(" \\| ")[1];
            switch (taskType) {
            case "T":
                Task toDoTask = new ToDo(taskDescription);
                if (isTaskDone.equals("1")) {
                    toDoTask.taskDone();
                }
                tasks.getTaskList().add(toDoTask);
                break;
            case "D":
                String deadlineBy = currentLine.split(" \\| ")[3];
                Task deadlineTask = new Deadline(taskDescription, deadlineBy);
                if (isTaskDone.equals("1")) {
                    deadlineTask.taskDone();
                }
                tasks.getTaskList().add(deadlineTask);
                break;
            case "E":
                String eventAt = currentLine.split(" \\| ")[3];
                Task eventTask = new Events(taskDescription, eventAt);
                if (isTaskDone.equals("1")) {
                    eventTask.taskDone();
                }
                tasks.getTaskList().add(eventTask);
                break;
            default:
                System.out.println("Undefined task type - should not happen");
                break;
            }
        }
    }

    public void saveTaskList(TaskList tasks, String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getTaskList().size(); i++) {
            String taskDescription = tasks.getTaskList().get(i).getTaskDescription();
            String taskType = tasks.getTaskList().get(i).getTaskType();
            String isTaskDone;
            if (tasks.getTaskList().get(i).getTaskStatus()) {
                isTaskDone = "1";
            } else {
                isTaskDone = "0";
            }
            switch (taskType) {
            case "ToDo":
                fw.write("T | " + isTaskDone + " | " + taskDescription + System.lineSeparator());
                break;
            case "Deadline":
                fw.write("D | " + isTaskDone + " | " + taskDescription + " | " + tasks.getTaskList().get(i).getTimeline() + System.lineSeparator());
                break;
            case "Events":
                fw.write("E | " + isTaskDone + " | " + taskDescription + " | " + tasks.getTaskList().get(i).getTimeline() + System.lineSeparator());
                break;
            default:
                System.out.println("Undefined task type - should not happen");
                break;
            }
        }
        fw.close();
    }

}
