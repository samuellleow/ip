package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Displays goodbye message when application is terminated by the user
 */
public class ExitCommand extends Command {
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.printGoodbyeMessage();
    }

    public Boolean isExit() {
        return true;
    }
}
