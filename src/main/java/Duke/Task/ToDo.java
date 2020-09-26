package Duke.Task;

/**
 * Represents a Todo task.
 */
public class ToDo extends Task {

    protected String taskType;

    public ToDo(String description) {
        super(description);
        this.taskType = "ToDo";
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + super.toString();
    }
}
