public class ListCommand extends Command {
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.printListMessage(tasks);
    }

    public Boolean isExit() {
        return false;
    }
}
