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
