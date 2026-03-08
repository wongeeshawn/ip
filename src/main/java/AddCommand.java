/** Command to add a new task (Todo, Deadline, or Event) to the task list. */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the task to be added.
     *
     * @param task the task to add
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the list, saves to storage, and notifies the user.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.save(tasks.getTasks());
        ui.showTaskAdded(task, tasks.size());
    }
}