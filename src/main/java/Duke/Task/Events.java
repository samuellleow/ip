package Duke.Task;

/**
 * Represents an Event task.
 */
public class Events extends Task {

    protected String at;
    protected String taskType;

    public Events(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "Events";
    }

    @Override
    public String getTimeline() {
        return this.at;
    }

    @Override
    public String getTaskType() {
        return taskType;
    }

    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + super.toString() + " (at: " + at + ")";
    }

}
