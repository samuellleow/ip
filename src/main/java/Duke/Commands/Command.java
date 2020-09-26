package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Represents an executable command.
 */
public abstract class Command {
    /**
     * Executes the command corresponding to user's input.
     * @param tasks TaskList object that stores existing tasks
     * @param ui User interface of application
     * @param storage Storage object that stores existing tasks into a data file
     */
    public abstract void executeCommand(TaskList tasks, Ui ui, Storage storage);

    /**
     * Returns an exit flag that is determined by user's command.
     * @return Terminate the program if user's command equals 'bye'
     */
    public abstract Boolean isExit();
}
