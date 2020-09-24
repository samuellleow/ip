public class DeleteCommand extends Command {
    private String currentInput;

    public DeleteCommand(String currentInput) {
        this.currentInput = currentInput;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        TaskList.errorCheckingDelete(currentInput, tasks);
    }

    public Boolean isExit() {
        return false;
    }

}
