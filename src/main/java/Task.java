/**
 * Represents an abstract task with a description and completion status.
 * This is a base class for specific task types like Todo, Deadline, and Event.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns the type icon for the task.
     * This must be implemented by subclasses to identify the task type.
     */
    public abstract String getTypeIcon();

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description;
    }
}