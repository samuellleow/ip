public class DoneCommand extends Command {
    private String currentInput;

    public DoneCommand(String currentInput) {
        this.currentInput = currentInput;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        TaskList.errorCheckingTaskDone(currentInput, tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
