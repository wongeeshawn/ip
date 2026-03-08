/** Command to delete a task from the task list by index. */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand for the task at the given 0-based index.
     *
     * @param index the 0-based index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the list, saves to storage, and notifies the user.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws ChatException if the index is out of bounds
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task removed = tasks.delete(index);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }
}