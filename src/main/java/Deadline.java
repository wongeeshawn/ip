/**
 * Represents a task that needs to be done before a specific date/time.
 * Example: submit report /by 11/10/2019 5pm
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a Deadline task with the given description and due date.
     *
     * @param description the description of the deadline task
     * @param by          the date/time by which the task must be completed
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Returns the type icon for a Deadline task.
     *
     * @return "D"
     */
    @Override
    public String getTypeIcon() {
        return "D";
    }

    /**
     * Returns a string representation of the deadline task.
     *
     * @return formatted string including the due date
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (by: " + by + ")";
    }
}