package Duke.Ui;

import java.util.Scanner;
import Duke.TaskList.TaskList;

/**
 * Text UI of the application.
 */
public class Ui {

    private static final String MESSAGE_GREETINGS = "     Hello! I'm Duke\n" +
            "     What can I do for you?";

    private static final String MESSAGE_SINGLE_LINE = "    ____________________________________________________________";

    private static final String MESSAGE_GOODBYE = "     Bye. Hope to see you again soon!";

    private static final String MESSAGE_ADD_TASK = "     Got it. I've added this task:";

    private static final String MESSAGE_DONE_TASK = "     Nice! I've marked this task as done:";

    private static final String MESSAGE_REMOVE_TASK = "     Noted. I've removed this task:";

    private static final String MESSAGE_TASK_LIST = "     Here are the tasks in your list:";

    private static final String MESSAGE_FIND_TASK = "     Here are the matching tasks in your list:";

    private static final String ERROR_MESSAGE_INVALID_FIND_TASK = "     There are no task(s) that match your keyword!!";

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

    /**
     * Reads user's input command.
     */
    public Ui() {
        in = new Scanner(System.in);
    }

    /**
     * Prints welcome message upon the start of the application.
     */
    public void printStartDukeMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_GREETINGS);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints goodbye message upon termination of the application.
     */
    public static void printGoodbyeMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_GOODBYE);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints description of the added task.
     *
     * @param tasks TaskList object that stores existing tasks
     */
    public static void printAddTaskMessage(TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_ADD_TASK);
        System.out.println("       " + tasks.getTaskList().get(tasks.getTaskList().size()-1));
        printNoOfTaskForAddingFunction(tasks);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    private static void printNoOfTaskForAddingFunction(TaskList tasks) {
        System.out.println("     Now you have " + tasks.getTaskList().size() + " tasks in the list.");
    }

    private static void printNoOfTaskForDeleteFunction(TaskList tasks) {
        System.out.println("     Now you have " + (tasks.getTaskList().size() - 1) + " tasks in the list.");
    }

    /**
     * Prints description of the task that is marked as done.
     *
     * @param itemIndex Index of the task to be marked as done
     * @param tasks TaskList object that stores existing tasks
     */
    public static void printDoneTaskMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_DONE_TASK);
        System.out.println("       " + tasks.getTaskList().get(itemIndex));
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints description of the task that is deleted.
     *
     * @param itemIndex Index of the task to be deleted
     * @param tasks TaskList object that stores existing tasks
     */
    public static void printDeleteTaskMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_REMOVE_TASK);
        System.out.println("       " + tasks.getTaskList().get(itemIndex));
        printNoOfTaskForDeleteFunction(tasks);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints date and time of the task
     *
     * @param itemIndex Index of the task to be deleted
     * @param tasks askList object that stores existing tasks
     */
    public static void printDateTimeMessage(int itemIndex, TaskList tasks) {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(MESSAGE_DATE_TIME);
        System.out.println("       " + tasks.getTaskList().get(itemIndex).getTimeline());
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints the list of existing tasks in the Arraylist.
     *
     * @param tasks TaskList object that stores existing tasks
     */
    public static void printListMessage(TaskList tasks) {
        if (tasks.getTaskList().size() == 0) {
            printEmptyListErrorMessage();
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

    /**
     * Finds the task(s) corresponding to the keyword the user has input
     *
     * @param tasks TaskList object that stores existing tasks
     * @param keyword Keyword used to find a task
     */
    public static void findTask(TaskList tasks, String keyword) {
        Boolean foundTaskWithKeyword = false;
        int taskIndex = 0;
        if (tasks.getTaskList().size() == 0) {
            printEmptyListErrorMessage();
        } else {
            System.out.println(MESSAGE_SINGLE_LINE);
            for (int i = 0; i < tasks.getTaskList().size(); i++) {
                if (tasks.getTaskList().get(i).getTaskDescription().contains(keyword)) {
                    if(!foundTaskWithKeyword) {
                        System.out.println(MESSAGE_FIND_TASK);
                    }
                    System.out.println("     " + (taskIndex + 1) + "." + tasks.getTaskList().get(i));
                    foundTaskWithKeyword = true;
                    taskIndex++;
                }
            }
            if (!foundTaskWithKeyword) {
                System.out.println(ERROR_MESSAGE_INVALID_FIND_TASK);
            }
            System.out.println(MESSAGE_SINGLE_LINE);
        }
    }

    /**
     * Prints error message if the Arraylist is empty.
     */
    public static void printEmptyListErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_EMPTY_LIST);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints error message when user provides invalid task number.
     */
    public static void printInvalidTaskNumberErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_TASK_NO);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints error message when user provides invalid input command.
     */
    public void printInvalidTaskInputErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_TASK_INPUT);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints error message when user provides invalid Deadline task
     */
    public static void printInvalidDeadlineErrorMessage() {
        System.out.println(MESSAGE_SINGLE_LINE);
        System.out.println(ERROR_MESSAGE_INVALID_DEADLINE);
        System.out.println(MESSAGE_SINGLE_LINE);
    }

    /**
     * Prints error message when user provides invalid Event task
     */
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
