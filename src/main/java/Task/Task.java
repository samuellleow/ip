package Task;

public class Task {
    protected String description;
    protected boolean isDone;
    protected static int noOfTask = 0;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
        noOfTask++;
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public void taskDone(){
        this.isDone = true;
    }

    public String getTaskDescription() {
        return description;
    }

    public String getTaskType() {
        return null;
    }

    public static int getNoOfTask() {
        return noOfTask;
    }

    @Override
    public String toString() {
        return description;
    }


}
