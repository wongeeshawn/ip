/**
 * Represents a simple task without any date or time attached to it.
 * Example: visit new theme park
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public String getTypeIcon() {
        return "T";
    }
}