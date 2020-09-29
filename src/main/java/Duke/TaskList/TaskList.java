package Duke.TaskList;

import Duke.Task.Task;
import Duke.Task.ToDo;
import Duke.Task.Deadline;
import Duke.Task.Events;
import Duke.Task.DukeException;
import Duke.Ui.Ui;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Gets the arraylist that contains existing tasks
     *
     * @return Arraylist that contains existing tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Checks if user added task description.
     * Creates and adds a new Todo task into the arraylist if no error is found.
     *
     * @param currentInput Full user's input
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingTodo(String currentInput, TaskList tasks) {
        try {
            createTodoTask(currentInput, tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            Ui.printInvalidTodoErrorMessage();
        }
    }

    private static void createTodoTask(String currentInput, TaskList tasks) {
        String taskDescription = currentInput.split(" ")[1];
        Task newToDoTask = new ToDo(taskDescription);
        tasks.getTaskList().add(newToDoTask);
        Ui.printAddTaskMessage(tasks);
    }

    /**
     * Checks for formatting error in user's input on adding Deadline task.
     * Creates and adds a new Deadline task into the arraylist if no error is found.
     *
     * @param currentInput Full user's input
     * @param taskDescription Description of Deadline task
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingDeadline(String currentInput, String taskDescription, TaskList tasks) {
        try {
            createDeadlineTask(currentInput, taskDescription, tasks);
        } catch (DukeException e) {
            Ui.printInvalidDeadlineErrorMessage();
        }
    }

    private static void createDeadlineTask(String currentInput, String taskDescription, TaskList tasks) throws DukeException {
        if (!currentInput.contains("/by")) {
            throw new DukeException();
        }
        String deadlineBy = currentInput.split("/by ")[1];
        deadlineBy = checkDateTime(deadlineBy);
        Task newDeadlineTask = new Deadline(taskDescription, deadlineBy);
        tasks.getTaskList().add(newDeadlineTask);
        Ui.printAddTaskMessage(tasks);
    }

    /**
     * Checks for formatting error in user's input on adding Event task.
     * Creates and adds a new Event task into the arraylist if no error is found.
     *
     * @param currentInput Full user's input
     * @param taskDescription Description of Event task
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingEvent(String currentInput, String taskDescription, TaskList tasks) {
        try {
            createEventTask(currentInput, taskDescription, tasks);
        } catch (DukeException e) {
            Ui.printInvalidEventErrorMessage();
        }
    }

    private static void createEventTask(String currentInput, String taskDescription, TaskList tasks) throws DukeException {
        if (!currentInput.contains("/at")) {
            throw new DukeException();
        }
        String eventAt = currentInput.split("/at ")[1];
        eventAt = checkDateTime(eventAt);
        Task newEventTask = new Events(taskDescription, eventAt);
        tasks.getTaskList().add(newEventTask);
        Ui.printAddTaskMessage(tasks);
    }

    /**
     * Checks for indexing error in user's input on deleting task.
     * Deletes the task corresponding to the user's index input if no error is found.
     *
     * @param currentInput Full user's input
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingDelete(String currentInput, TaskList tasks) {
        try {
            deleteTask(currentInput, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (tasks.getTaskList().size() == 0) {
                Ui.printEmptyListErrorMessage();
            } else {
                Ui.printInvalidTaskNumberErrorMessage();
            }
        }
    }

    private static void deleteTask(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        Task taskToBeDeleted = tasks.getTaskList().get(itemIndex);
        tasks.getTaskList().remove(itemIndex);
        Ui.printDeleteTaskMessage(taskToBeDeleted, tasks);
    }

    /**
     * Checks for indexing error in user's input on marking task as done.
     * Sets the task as done corresponding to the user's index input if no error is found.
     *
     * @param currentInput Full user's input
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingTaskDone(String currentInput, TaskList tasks) {
        try {
            taskMarkAsDone(currentInput, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (tasks.getTaskList().size() == 0) {
                Ui.printEmptyListErrorMessage();
            } else {
                Ui.printInvalidTaskNumberErrorMessage();
            }
        }
    }

    private static void taskMarkAsDone(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        tasks.getTaskList().get(itemIndex).taskDone();
        Ui.printDoneTaskMessage(itemIndex, tasks);
    }

    /**
     * Checks for formatting error in user's input on date and time input.
     * Returns the date and time details in <MMM d yyyy HH:mm> format if no error is found.
     *
     * @param dateTimeDetails Details of date and/or time of Deadline or Event task
     * @return Details of date and/or time
     */
    public static String checkDateTime(String dateTimeDetails) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeDetails);
            dateTimeDetails = dateTime.format(formatter);
        } catch (java.time.format.DateTimeParseException e) {
            Ui.printInvalidDateTimeErrorMessage();
        }
        return dateTimeDetails;
    }

    /**
     * Checks for indexing error in user's input on checking date and time of task.
     * Returns the date and time details corresponding to the user's index input if no error is found.
     *
     * @param currentInput Full user's input
     * @param tasks Arraylist that stores existing tasks
     */
    public static void errorCheckingDateTime(String currentInput, TaskList tasks) {
        try {
            taskDateTime(currentInput, tasks);
        } catch (NumberFormatException | IndexOutOfBoundsException | NullPointerException e) {
            if (tasks.getTaskList().size() == 0) {
                Ui.printEmptyListErrorMessage();
            } else {
                Ui.printInvalidTaskNumberErrorMessage();
            }
        }
    }

    private static void taskDateTime(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        tasks.getTaskList().get(itemIndex).getTimeline();
        Ui.printDateTimeMessage(itemIndex, tasks);
    }
}
