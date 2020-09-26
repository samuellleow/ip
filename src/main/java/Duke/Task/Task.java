package Duke.Task;

/**
 * Represents a Task in the task list.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Task class constructor.
     *
     * @param description Description of task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    /**
     * Marks a particular task as done.
     */
    public void taskDone(){
        this.isDone = true;
    }

    /**
     * Returns description of specified task.
     *
     * @return Description of task
     */
    public String getTaskDescription() {
        return description;
    }

    /**
     * Returns date and/or time of specified task.
     *
     * @return Date and/or time of task
     */
    public String getTimeline() {
        return null;
    }

    /**
     * Returns type of the task.
     *
     * @return Type of task
     */
    public String getTaskType() {
        return null;
    }

    /**
     * Returns the status of the specified task.
     *
     * @return Status of task
     */
    public boolean getTaskStatus() {
        return isDone;
    }

    /**
     * Returns details of the specified task.
     *
     * @return Details of task
     */
    @Override
    public String toString() {
        return description;
    }


}
