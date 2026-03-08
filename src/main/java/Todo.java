/**
 * Represents a simple task without any date or time attached to it.
 * Example: visit new theme park
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description.
     *
     * @param description the description of the todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns the type icon for a Todo task.
     *
     * @return "T"
     */
    @Override
    public String getTypeIcon() {
        return "T";
    }
}