package Duke.Commands;

import Duke.Ui.Ui;
import Duke.TaskList.TaskList;
import Duke.Storage.Storage;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String inputCommand) {
        this.keyword = inputCommand.split(" ")[1];;
    }

    public void executeCommand(TaskList tasks, Ui ui, Storage storage) {
        Ui.findTask(tasks, keyword);
    }

    public Boolean isExit() {
        return false;
    }
}
