package Duke;

import Duke.Storage.Storage;
import Duke.TaskList.TaskList;
import Duke.Ui.Ui;
import Duke.Commands.Command;
import Duke.Parser.Parser;

import java.io.IOException;

/**
 * Entry point of the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "data/tasks.txt";

    /**
     * Initializes the application.
     * Sets up the required objects.
     *
     * @param filePath Path of the data file that stores existing tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        storage.loadDataList(tasks);
    }

    /**
     * Prints welcome message.
     * Loads existing tasks from the data file.
     * Starts the interaction with the user.
     * Runs the program until termination by the user.
     */
    public void run() {
        ui.printStartDukeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
            Command commandInput = Parser.parse(ui.in.nextLine());
            commandInput.executeCommand(tasks, ui, storage);
            storage.saveTaskList(tasks, FILE_PATH);
            isExit = commandInput.isExit();
            } catch (NullPointerException | StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException| IOException e) {
                ui.printInvalidTaskInputErrorMessage();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
