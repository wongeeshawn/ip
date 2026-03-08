import java.util.ArrayList;

/**
 * Command to find tasks whose description contains a given keyword.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the given search keyword.
     *
     * @param keyword the keyword to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword.toLowerCase();
    }

    /**
     * Searches the task list for tasks matching the keyword and displays results.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().toLowerCase().contains(keyword)) {
                matches.add(task);
            }
        }
        ui.showMatchingTasks(matches);
    }
}