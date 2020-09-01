public class Deadline extends Task {

    protected String by;
    protected String taskType;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        this.taskType = "Deadline";
    }

    @Override
    public String getTaskType() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return "[D][" + getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}