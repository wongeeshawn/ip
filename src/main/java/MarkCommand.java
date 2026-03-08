/** Command to mark a task as done or not done. */
public class MarkCommand extends Command {
    private final int index;
    private final boolean markDone;

    public MarkCommand(int index, boolean markDone) {
        this.index = index;
        this.markDone = markDone;
    }

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