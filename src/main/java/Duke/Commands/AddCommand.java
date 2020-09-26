package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {
    private static final String INPUT_COMMAND_DEADLINE = "deadline";
    private static final String INPUT_COMMAND_EVENT = "event";
    private static final String INPUT_COMMAND_TODO = "todo";
    private String taskType;
    private String description;
    private String currentInput;

    public AddCommand(String taskType, String description, String currentInput) {
        this.taskType = taskType;
        this.description = description;
        this.currentInput = currentInput;
    }

    /**
     * Adds a particular task type corresponding to the user's input command
     *
     * @param tasks TaskList object that stores existing tasks
     * @param ui User interface of application
     * @param storage Storage object that stores existing tasks into a data file
     */
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        switch(taskType) {
        case INPUT_COMMAND_TODO:
            TaskList.createTodoTask(description, tasks);
            break;
        case INPUT_COMMAND_DEADLINE:
            TaskList.errorCheckingDeadline(currentInput, description, tasks);
            break;
        case INPUT_COMMAND_EVENT:
            TaskList.errorCheckingEvent(currentInput, description, tasks);
            break;
        }
    }

    public Boolean isExit() {
        return false;
    }

}
