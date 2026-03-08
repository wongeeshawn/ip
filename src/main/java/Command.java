/**
 * Represents an executable user command.
 * Each subclass encapsulates the logic for one type of command.
 */
public abstract class Command {

    /**
     * Executes this command, potentially modifying tasks and producing UI output.
     *
     * @param tasks   the current task list
     * @param ui      the UI handler for output
     * @param storage the storage handler for persistence
     * @throws ChatException if the command cannot be completed
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws ChatException;

    /**
     * Returns true if this command should terminate the application.
     */
    public boolean isExit() {
        return false;
    }
}