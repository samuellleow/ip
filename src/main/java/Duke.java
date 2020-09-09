import java.util.Scanner;
import Task.Deadline;
import Task.ToDo;
import Task.Events;
import Task.Task;
import Task.DukeException;

public class Duke {

    static Task[] t = new Task[100];
    static Scanner in = new Scanner(System.in);

    public static void displayTaskList() {
        if (Task.getNoOfTask() == 0) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Your list is empty!!\n" +
                    "    ____________________________________________________________\n");
        } else {
            System.out.println("    ____________________________________________________________\n" +
                    "     Here are the tasks in your list:");
            for (int i = 0; i < Task.getNoOfTask(); i++) {
                if (t[i].getTaskType().equals("Deadline")) {
                    System.out.println("     " + (i + 1) + "." + t[i]);
                } else if (t[i].getTaskType().equals("Events")) {
                    System.out.println("     " + (i + 1) + "." + t[i]);
                } else {
                    System.out.println("     " + (i + 1) + "." + t[i]);
                }
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }

    public static void taskMarkAsDone(String currentInput) {
        String taskDoneDescription;
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        taskDoneDescription = t[itemIndex].getTaskDescription();
        t[itemIndex].taskDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       [✓] " + taskDoneDescription + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void errorCheckingTaskDone(String currentInput) {
        try {
            taskMarkAsDone(currentInput);
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Please input a valid task number!!\n" +
                    "    ____________________________________________________________\n");
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
        t[Task.getNoOfTask()] = new Deadline(taskDescription, deadlineBy);
        addedTaskMessage();
    }

    public static void createEventTask(String currentInput, String taskDescription) throws DukeException {
        if (!currentInput.contains("/at")) {
            throw new DukeException();
        }
        String eventAt = currentInput.split("/at ")[1];
        t[Task.getNoOfTask()] = new Events(taskDescription, eventAt);
        addedTaskMessage();
    }

    public static void createTodoTask(String taskDescription) {
        t[Task.getNoOfTask()] = new ToDo(taskDescription);
        addedTaskMessage();
    }

    public static void addedTaskMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + t[Task.getNoOfTask()-1] + "\n" +
                "     Now you have " + (Task.getNoOfTask()) + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
    }

    public static void invalidTaskInput() {
        System.out.println("    ____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        System.out.println(greetings);

        String output;
        String currentInput = in.nextLine();

        while (!currentInput.equals("bye")) {
            if (currentInput.equals("list")) {
                displayTaskList();
            } else {
                checkTask(currentInput);
            }
            currentInput = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }

}
