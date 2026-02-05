/**
 * Represents a task that starts at a specific date/time and ends at another date/time.
 * Example: team project meeting from 2/10/2019 2pm to 4pm
 */
public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTypeIcon() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}