import Task.Task;
import Task.ToDo;
import Task.Deadline;
import Task.Events;
import Task.DukeException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public static void createTodoTask(String taskDescription, TaskList tasks) {
        Task newToDoTask = new ToDo(taskDescription);
        tasks.getTaskList().add(newToDoTask);
        Ui.printAddTaskMessage(tasks);
    }

    public static void errorCheckingDeadline(String currentInput, String taskDescription, TaskList tasks) {
        try {
            createDeadlineTask(currentInput, taskDescription, tasks);
        } catch (DukeException e) {
            Ui.printInvalidDeadlineErrorMessage();
        }
    }

    public static void createDeadlineTask(String currentInput, String taskDescription, TaskList tasks) throws DukeException {
        if (!currentInput.contains("/by")) {
            throw new DukeException();
        }
        String deadlineBy = currentInput.split("/by ")[1];
        deadlineBy = checkDateTime(deadlineBy);
        Task newDeadlineTask = new Deadline(taskDescription, deadlineBy);
        tasks.getTaskList().add(newDeadlineTask);
        Ui.printAddTaskMessage(tasks);
    }

    public static void errorCheckingEvent(String currentInput, String taskDescription, TaskList tasks) {
        try {
            createEventTask(currentInput, taskDescription, tasks);
        } catch (DukeException e) {
            Ui.printInvalidEventErrorMessage();
        }
    }

    public static void createEventTask(String currentInput, String taskDescription, TaskList tasks) throws DukeException {
        if (!currentInput.contains("/at")) {
            throw new DukeException();
        }
        String eventAt = currentInput.split("/at ")[1];
        eventAt = checkDateTime(eventAt);
        Task newEventTask = new Events(taskDescription, eventAt);
        tasks.getTaskList().add(newEventTask);
        Ui.printAddTaskMessage(tasks);
    }

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

    public static void deleteTask(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        Ui.printDeleteTaskMessage(itemIndex, tasks);
        tasks.getTaskList().remove(itemIndex);
    }

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

    public static void taskMarkAsDone(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        tasks.getTaskList().get(itemIndex).taskDone();
        Ui.printDoneTaskMessage(itemIndex, tasks);
    }

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

    public static void taskDateTime(String currentInput, TaskList tasks) {
        int itemIndex = Integer.parseInt(currentInput.replaceAll("\\D+","")) - 1;
        tasks.getTaskList().get(itemIndex).getTimeline();
        Ui.printDateTimeMessage(itemIndex, tasks);
    }

}
