package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Prints a list of existing tasks.
 */
public class ListCommand extends Command {
    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.printListMessage(tasks);
    }

    public Boolean isExit() {
        return false;
    }
}
