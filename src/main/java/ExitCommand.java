/** Command to exit the application. */
public class ExitCommand extends Command {

    /**
     * Displays the goodbye message to the user.
     *
     * @param tasks   the current task list (unused)
     * @param ui      the UI handler for output
     * @param storage the storage handler (unused)
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * Returns true to signal the application should exit.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}