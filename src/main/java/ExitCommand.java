public class ExitCommand extends Command {
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.printGoodbyeMessage();
    }

    public Boolean isExit() {
        return true;
    }
}
