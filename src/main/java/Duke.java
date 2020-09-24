import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private static final String FILE_PATH = "data/tasks.txt";

    public Duke(String filePath) {
        ui = new Ui();
        tasks = new TaskList();
        storage = new Storage(filePath);
        storage.loadDataList(tasks);
    }

    public void run() {
        ui.printStartDukeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
            Command commandInput = Parser.parse(ui.in.nextLine());
            commandInput.executeCommand(tasks, ui, storage);
            storage.saveTaskList(tasks, FILE_PATH);
            isExit = commandInput.isExit();
            } catch (NullPointerException | StringIndexOutOfBoundsException | IOException e) {
                ui.printInvalidTaskInputErrorMessage();
            }
        }

    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
