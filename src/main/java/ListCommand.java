/** Command to display all tasks in the task list. */
public class ListCommand extends Command {

    /**
     * Displays all tasks to the user.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showTaskList(tasks.getTasks());
    }
}