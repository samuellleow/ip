package Duke.Parser;

import Duke.Commands.Command;
import Duke.Commands.AddCommand;
import Duke.Commands.DateTimeCommand;
import Duke.Commands.DeleteCommand;
import Duke.Commands.DoneCommand;
import Duke.Commands.ExitCommand;
import Duke.Commands.ListCommand;

public class Parser {
    private static final String INPUT_COMMAND_DEADLINE = "deadline";
    private static final String INPUT_COMMAND_EVENT = "event";
    private static final String INPUT_COMMAND_TODO = "todo";
    private static final String INPUT_COMMAND_LIST = "list";
    private static final String INPUT_COMMAND_DONE = "done";
    private static final String INPUT_COMMAND_DELETE = "delete";
    private static final String INPUT_COMMAND_BYE = "bye";
    private static final String INPUT_COMMAND_DATETIME = "datetime";

    /**
     * Parses user input into command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command parse(String inputCommand) {
        Command commandType = null;
        if (inputCommand.equals(INPUT_COMMAND_BYE)) {
            commandType = new ExitCommand();
        } else if (inputCommand.equals(INPUT_COMMAND_LIST)) {
            commandType = new ListCommand();
        } else {
            commandType = checkTask(inputCommand);
        }
        return commandType;
    }

    /**
     * Parses user input related to tasks into command for execution.
     *
     * @param inputCommand Full user input command string
     * @return Command object corresponding to the input command of the user
     */
    public static Command checkTask(String inputCommand) {
        Command commandType = null;
        String taskType = inputCommand.split(" ")[0];
        String taskDescription;

        // Check user's input command - user might enter incorrect inputs which does not correspond to any of our function
        if (inputCommand.split(" ").length > 1) {
            taskDescription = inputCommand.split(" /", 2)[0].split(" ", 2)[1];
        }
        else {
            taskDescription = inputCommand;
        }

        switch (taskType) {
        case INPUT_COMMAND_DEADLINE:
        case INPUT_COMMAND_EVENT:
        case INPUT_COMMAND_TODO:
            commandType = new AddCommand(taskType, taskDescription, inputCommand);
            break;
        case INPUT_COMMAND_DONE:
            commandType = new DoneCommand(inputCommand);
            break;
        case INPUT_COMMAND_DELETE:
            commandType = new DeleteCommand(inputCommand);
            break;
        case INPUT_COMMAND_DATETIME:
            commandType = new DateTimeCommand(inputCommand);
        }
        return commandType;
    }


}
