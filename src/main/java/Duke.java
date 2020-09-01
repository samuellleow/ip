import java.util.Scanner;

public class Duke {

    static Task[] t = new Task[100];
    static String output;
    static Scanner in = new Scanner(System.in);

    public static void displayTaskList() {
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < Task.getNoOfTask(); i++) {
            if(t[i].getTaskType().equals("Deadline")) {
                System.out.println("     " + (i+1) + "." + t[i]);
            } else if(t[i].getTaskType().equals("Events")) {
                System.out.println("     " + (i+1) + "." + t[i]);
            } else {
                System.out.println("     " + (i+1) + "." + t[i]);
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void taskMarkAsDone(int itemIndex) {
        String taskDoneDescription = "";
        for(int i = 0; i < Task.getNoOfTask(); i++) {
            if(itemIndex == i) {
                taskDoneDescription = t[i].description;
                t[i].taskDone();
            }
        }
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done:\n" +
                "       [✓] " + taskDoneDescription + "\n" +
                "    ____________________________________________________________\n");
    }

    public static void addTask(String currentInput) {
        String taskType = currentInput.split(" ")[0];
        String taskDescription = currentInput.split(" /", 2)[0].split(" ", 2)[1];

        switch (taskType) {
        case "deadline":
            String deadlineBy = currentInput.split("/by ")[1];
            t[Task.getNoOfTask()] = new Deadline(taskDescription, deadlineBy);
            break;
        case "event":
            String eventBy = currentInput.split("/at ")[1];
            t[Task.getNoOfTask()] = new Events(taskDescription, eventBy);
            break;
        case "todo":
            t[Task.getNoOfTask()] = new ToDo(taskDescription);
            break;
        default:
            t[Task.getNoOfTask()] = new ToDo(currentInput);
            break;
        }
        output = "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + t[Task.getNoOfTask()-1] + "\n" +
                "     Now you have " + (Task.getNoOfTask()) + " tasks in the list.\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);
    }

    public static void main(String[] args) {
        String greetings = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n" +
                "\n";
        System.out.println(greetings);

        String currentInput = in.nextLine();

        while (!currentInput.equals("bye")) {
            if(currentInput.equals("list")) {
                displayTaskList();
            } else if(currentInput.contains("done")) {
                int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
                taskMarkAsDone(itemIndex);
            } else {
                addTask(currentInput);
            }
            currentInput = in.nextLine();
        }

        output = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        System.out.println(output);

    }

}
