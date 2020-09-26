package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

/**
 * Deletes a task identified using the displayed index from the task list.
 */
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
