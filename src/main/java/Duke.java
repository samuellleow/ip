import java.util.Scanner;
import Task.Deadline;
import Task.ToDo;
import Task.Events;
import Task.Task;
import Task.DukeException;
import java.util.ArrayList;

public class Duke {

    static ArrayList<Task> t = new ArrayList<>();
    static Scanner in = new Scanner(System.in);

    public static void displayTaskList() {
        if (t.size() == 0) {
            System.out.println("    ____________________________________________________________\n" +
                    "     Your list is empty!!\n" +
                    "    ____________________________________________________________\n");
        } else {
            System.out.println("    ____________________________________________________________\n" +
                    "     Here are the tasks in your list:");
            for (int i = 0; i < t.size(); i++) {
                if (t.get(i).getTaskType().equals("Deadline")) {
                    System.out.println("     " + (i + 1) + "." + t.get(i));
                } else if (t.get(i).getTaskType().equals("Events")) {
                    System.out.println("     " + (i + 1) + "." + t.get(i));
                } else {
                    System.out.println("     " + (i + 1) + "." + t.get(i));
                }
            }
            System.out.println("    ____________________________________________________________\n");
        }
    }

    public static void taskMarkAsDone(String currentInput) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        t.get(itemIndex).taskDone();
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       " + t.get(itemIndex) + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void deleteTask(String currentInput) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        System.out.println("    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + t.get(itemIndex) + "\n" +
                "     Now you have " + (t.size()-1) + " tasks in the list.\n" +
                "    ____________________________________________________________\n");
        t.remove(itemIndex);

    }

    public static void errorCheckingTaskDone(String currentInput) {
        try {
            taskMarkAsDone(currentInput);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (t.size() == 0) {
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
            if (t.size() == 0) {
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
        t.add(newDeadlineTask);
        addedTaskMessage();
    }

    public static void createEventTask(String currentInput, String taskDescription) throws DukeException {
        if (!currentInput.contains("/at")) {
            throw new DukeException();
        }
        String eventAt = currentInput.split("/at ")[1];
        Task newEventTask = new Events(taskDescription, eventAt);
        t.add(newEventTask);
        addedTaskMessage();
    }

    public static void createTodoTask(String taskDescription) {
        Task newToDoTask = new ToDo(taskDescription);
        t.add(newToDoTask);
        addedTaskMessage();
    }

    public static void addedTaskMessage() {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + t.get(t.size()-1) + "\n" +
                "     Now you have " + t.size() + " tasks in the list.\n" +
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
