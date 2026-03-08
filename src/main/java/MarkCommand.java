/** Command to mark a task as done or not done. */
public class MarkCommand extends Command {
    private final int index;
    private final boolean markDone;

    /**
     * Constructs a MarkCommand for the task at the given 0-based index.
     *
     * @param index    the 0-based index of the task to mark
     * @param markDone true to mark as done, false to mark as not done
     */
    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.markDone = markDone;
    }

    /**
     * Marks the task as done or not done, saves to storage, and notifies the user.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws ChatException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        if (markDone) {
            Task task = tasks.markDone(index);
            storage.save(tasks.getTasks());
            ui.showTaskMarkedDone(task);
        } else {
            Task task = tasks.markNotDone(index);
            storage.save(tasks.getTasks());
            ui.showTaskMarkedNotDone(task);
        }
    }
}