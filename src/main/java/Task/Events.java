package Task;

public class Events extends Task {

    protected String at;
    protected String taskType;

    public Events(String description, String at) {
        super(description);
        this.at = at;
        this.taskType = "Events";
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
