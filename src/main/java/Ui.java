import java.util.Scanner;
import Task.Task;

public class Ui {

    private static final String MESSAGE_GREETINGS = "     Hello! I'm Duke\n" +
            "     What can I do for you?";

    private static final String MESSAGE_SINGLE_LINE = "    ____________________________________________________________";

    private static final String MESSAGE_GOODBYE = "     Bye. Hope to see you again soon!";

    private static final String MESSAGE_ADD_TASK = "     Got it. I've added this task:";

    private static final String MESSAGE_DONE_TASK = "     Nice! I've marked this task as done:";

    private static final String MESSAGE_REMOVE_TASK = "     Noted. I've removed this task:";

    private static final String MESSAGE_TASK_LIST = "     Here are the tasks in your list:";

    private static final String MESSAGE_DATE_TIME = "     This deadline/event is by/at ";

    private static final String ERROR_MESSAGE_EMPTY_LIST = "     Your list is empty!!";

    private static final String ERROR_MESSAGE_INVALID_TASK_NO = "     Please input a valid task number!!";

    private static final String ERROR_MESSAGE_INVALID_TASK_INPUT = "     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";

    private static final String ERROR_MESSAGE_INVALID_DEADLINE = "     Please enter deadline of task:)\n" +
            "     For example: deadline (task description) /by (yyyy-MM-dd HH:mm)";

    private static final String ERROR_MESSAGE_INVALID_EVENT = "     Please enter date of event:)\n" +
            "     For example: event (task description) /at (yyyy-MM-dd HH:mm)";

    private static final String ERROR_MESSAGE_INVALID_DATE_TIME = "     Please use the correct date and time format:)\n" +
            "     /by (yyyy-MM-dd)T(HH:mm) OR\n" +
            "     /at (yyyy-MM-dd)T(HH:mm)";



    public Scanner in;

    public Ui() {
        in = new Scanner(System.in);
    }

    public void printStartDukeMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_GREETINGS);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printGoodbyeMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printAddTaskMessage(TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_ADD_TASK);
        //POTENTIAL PROBLEM//
        System.out.println("       " + tasks.getTaskList().get(tasks.getTaskList().size()-1));
        printNoOfTaskForAddingFunction(tasks);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printNoOfTaskForAddingFunction(TaskList tasks) {
        System.out.println("     Now you have " + tasks.getTaskList().size() + " tasks in the list.");
    }

    public static void printNoOfTaskForDeleteFunction(TaskList tasks) {
        System.out.println("     Now you have " + (tasks.getTaskList().size() - 1) + " tasks in the list.");
    }

    public static void printDoneTaskMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_DONE_TASK);
        System.out.println("       " + tasks.getTaskList().get(itemIndex));
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printDeleteTaskMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_REMOVE_TASK);
        System.out.println("       " + tasks.getTaskList().get(itemIndex));
        printNoOfTaskForDeleteFunction(tasks);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printDateTimeMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_DATE_TIME);
        System.out.println("       " + tasks.getTaskList().get(itemIndex).getTimeline());
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printListMessage(TaskList tasks) {
        if (tasks.getTaskList().size() == 0) {
            Ui.printEmptyListErrorMessage();
        } else {
            System.out.println(MESSAGE_SINGLE_LINE);
            System.out.println(MESSAGE_TASK_LIST);
            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                if (tasks.getTaskList().get(i).getTaskType().equals("Deadline")) {
                    System.out.println("     " + (i + 1) + "." + tasks.getTaskList().get(i));
                } else if (tasks.getTaskList().get(i).getTaskType().equals("Events")) {
                    System.out.println("     " + (i + 1) + "." + tasks.getTaskList().get(i));
                } else {
                    System.out.println("     " + (i + 1) + "." + tasks.getTaskList().get(i));
                }
            }
            System.out.println(MESSAGE_SINGLE_LINE);
        }
    }

    public static void printEmptyListErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_EMPTY_LIST);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printInvalidTaskNumberErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_TASK_NO);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public void printInvalidTaskInputErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_TASK_INPUT);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printInvalidDeadlineErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_DEADLINE);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printInvalidEventErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_EVENT);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    public static void printInvalidDateTimeErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_DATE_TIME);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

}
