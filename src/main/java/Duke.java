import java.util.Scanner;
import Task.Deadline;
import Task.ToDo;
import Task.Events;
import Task.Task;
import Task.DukeException;
import java.util.ArrayList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {

    static ArrayList<Task> tasks = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void displayTaskList() {
        if (tasks.size() == 0) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Your list is empty!!\n" +
                    "    ____________________________________________________________\n");
        } else {
            System.out.println("    ____________________________________________________________\n" +
                    "     Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                if (tasks.get(i).getTaskType().equals("Deadline")) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                } else if (tasks.get(i).getTaskType().equals("Events")) {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                } else {
                    System.out.println("     " + (i + 1) + "." + tasks.get(i));
                }
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }

    public static void taskMarkAsDone(String currentInput) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        tasks.get(itemIndex).taskDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       " + tasks.get(itemIndex) + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void deleteTask(String currentInput) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + tasks.get(itemIndex) + "\n" +
                "     Now you have " + (tasks.size()-1) + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        tasks.remove(itemIndex);

    }

    public static void errorCheckingTaskDone(String currentInput) {
        try {
            taskMarkAsDone(currentInput);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (tasks.size() == 0) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Your list is empty!!\n" +
                        "    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     Please input a valid task number!!\n" +
                        "    ____________________________________________________________\n");
            }
        }
    }

    public static void errorCheckingDelete(String currentInput) {
        try {
            deleteTask(currentInput);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (tasks.size() == 0) {
                System.out.println("    ____________________________________________________________\n" +
                        "     Your list is empty!!\n" +
                        "    ____________________________________________________________\n");
            } else {
                System.out.println("    ____________________________________________________________\n" +
                        "     Please input a valid task number!!\n" +
                        "    ____________________________________________________________\n");
            }
        }

    }

    public static void checkTask(String currentInput) {
        String taskType = currentInput.split(" ")[0];
        String taskDescription;

        if (currentInput.split(" ").length > 1) {
            taskDescription = currentInput.split(" /", 2)[0].split(" ", 2)[1];
        }
        else {
            taskDescription = currentInput;
        }

        switch (taskType) {
        case "deadline":
            errorCheckingDeadline(currentInput, taskDescription);
            break;
        case "event":
            errorCheckingEvent(currentInput, taskDescription);
            break;
        case "todo":
            createTodoTask(taskDescription);
            break;
        case "done":
            errorCheckingTaskDone(currentInput);
            break;
        case "delete":
            errorCheckingDelete(currentInput);
            break;
        default:
            invalidTaskInput();
            break;
        }
    }

    public static void errorCheckingDeadline(String currentInput, String taskDescription) {
        try {
            createDeadlineTask(currentInput, taskDescription);
        } catch (DukeException e) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Please enter deadline of task:)\n" +
                    "     For example: deadline (task description) /by (task deadline)\n" +
                    "    ____________________________________________________________\n");
        }
    }

    public static void errorCheckingEvent(String currentInput, String taskDescription) {
        try {
            createEventTask(currentInput, taskDescription);
        } catch (DukeException e) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Please enter date of event:)\n" +
                    "     For example: event (task description) /at (date of event)\n" +
                    "    ____________________________________________________________\n");
        }
    }



    public static void createDeadlineTask(String currentInput, String taskDescription) throws DukeException {
        if (!currentInput.contains("/by")) {
            throw new DukeException();
        }
        String deadlineBy = currentInput.split("/by ")[1];
        Task newDeadlineTask = new Deadline(taskDescription, deadlineBy);
        tasks.add(newDeadlineTask);
        addedTaskMessage();
    }

    public static void createEventTask(String currentInput, String taskDescription) throws DukeException {
        if (!currentInput.contains("/at")) {
            throw new DukeException();
        }
        String eventAt = currentInput.split("/at ")[1];
        Task newEventTask = new Events(taskDescription, eventAt);
        tasks.add(newEventTask);
        addedTaskMessage();
    }

    public static void createTodoTask(String taskDescription) {
        Task newToDoTask = new ToDo(taskDescription);
        tasks.add(newToDoTask);
        addedTaskMessage();
    }

    public static void addedTaskMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + tasks.get(tasks.size()-1) + "\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void invalidTaskInput() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n");
    }

    private static String loadExistingList(ArrayList<Task> tasks) {
        checkSpecificFolderExist();
        String dataListFilePath = "data/duke.txt";
        try {
            File dataListFile = checkFileExists(dataListFilePath);
            readDataList(tasks, dataListFile);
        } catch (IOException e) {
            System.out.println("IOException error has occurred");
        }
        return dataListFilePath;
    }

    private static void checkSpecificFolderExist() {
        File folder = new File("data/");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private static File checkFileExists(String dataListFilePath) throws IOException {
        File dataFile = new File(dataListFilePath);
        if (!dataFile.exists()) {
            dataFile.createNewFile();
        }
        return dataFile;
    }

    private static void readDataList(ArrayList<Task> tasks, File dataListFile) throws FileNotFoundException {
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
                tasks.add(toDoTask);
                break;
            case "D":
                String deadlineBy = currentLine.split(" \\| ")[3];
                Task deadlineTask = new Deadline(taskDescription, deadlineBy);
                if (isTaskDone.equals("1")) {
                    deadlineTask.taskDone();
                }
                tasks.add(deadlineTask);
                break;
            case "E":
                String eventAt = currentLine.split(" \\| ")[3];
                Task eventTask = new Events(taskDescription, eventAt);
                if (isTaskDone.equals("1")) {
                    eventTask.taskDone();
                }
                tasks.add(eventTask);
                break;
            default:
                System.out.println("Undefined task type - should not happen");
                break;
            }
        }
    }
    private static void saveTaskList(ArrayList<Task> tasks, String dataListFilePath) throws IOException {
        FileWriter fw = new FileWriter(dataListFilePath);
        //for (Task task : t)
        for (Task task : tasks) {
            String taskDescription = task.getTaskDescription();
            String taskType = task.getTaskType();
            String isTaskDone;
            if (task.getTaskStatus()) {
                isTaskDone = "1";
            } else {
                isTaskDone = "0";
            }
            switch (taskType) {
            case "ToDo":
                fw.write("T | " + isTaskDone + " | " + taskDescription + System.lineSeparator());
                break;
            case "Deadline":
                fw.write("D | " + isTaskDone + " | " + taskDescription + " | " + task.getTimeline() + System.lineSeparator());
                break;
            case "Events":
                fw.write("E | " + isTaskDone + " | " + taskDescription + " | " + task.getTimeline() + System.lineSeparator());
                break;
            default:
                System.out.println("Undefined task type - should not happen");
                break;
            }
        }
        fw.close();
    }

    public static void main(String[] args) {
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        System.out.println(greetings);

        String output;
        String dataFileLocation = loadExistingList(tasks);
        String currentInput = in.nextLine();

        while (!currentInput.equals("bye")) {
            if (currentInput.equals("list")) {
                displayTaskList();
            } else {
                checkTask(currentInput);
                try {
                    saveTaskList(tasks, dataFileLocation);
                } catch (IOException e) {
                    System.out.println("IOException error has occurred");
                }
            }
            currentInput = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }

}
