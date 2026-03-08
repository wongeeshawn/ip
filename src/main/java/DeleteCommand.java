/** Command to delete a task by 0-based index. */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException {
        Task removed = tasks.delete(index);
        storage.save(tasks.getTasks());
        ui.showTaskDeleted(removed, tasks.size());
    }
}