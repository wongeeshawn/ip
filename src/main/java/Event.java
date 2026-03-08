/**
 * Represents a task that starts at a specific date/time and ends at another date/time.
 * Example: team project meeting /from 2/10/2019 2pm /to 4pm
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task with the given description, start, and end times.
     *
     * @param description the description of the event
     * @param from        the start date/time of the event
     * @param to          the end date/time of the event
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type icon for an Event task.
     *
     * @return "E"
     */
    @Override
    public String getTypeIcon() {
        return "E";
    }

    /**
     * Returns a string representation of the event task.
     *
     * @return formatted string including start and end times
     */
    @Override
    public String toString() {
        return "[" + getTypeIcon() + "][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}