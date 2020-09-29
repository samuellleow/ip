package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Prints the date and/or time of Deadline and Event task
 */
public class DateTimeCommand extends Command {
    private String currentInput;

    public DateTimeCommand(String currentInput) {
        this.currentInput = currentInput;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        TaskList.errorCheckingDateTime(currentInput, tasks);
    }

    public Boolean isExit() {
        return false;
    }
}
