import java.util.ArrayList;

/**
 * Contains the task list and provides operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the underlying list of tasks.
     *
     * @return the list of tasks
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the task count
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns true if there are no tasks in the list.
     *
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the task at the given 0-based index.
     *
     * @param index the 0-based index of the task
     * @return the task at the given index
     * @throws ChatException if the index is out of bounds
     */
    public Task get(int index) throws ChatException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ChatException("You don't have any tasks! Add some tasks first.");
            }
            throw new ChatException("That task number doesn't exist! Choose a number from 1 to " + tasks.size() + ".");
        }
        return tasks.get(index);
    }

    /**
     * Adds a task to the list.
     *
     * @param task the task to add
     * @return the added task
     */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /**
     * Removes and returns the task at the given 0-based index.
     *
     * @param index the 0-based index of the task to delete
     * @return the removed task
     * @throws ChatException if the index is out of bounds
     */
    public Task delete(int index) throws ChatException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ChatException("You don't have any tasks to delete! Add some tasks first.");
            }
            throw new ChatException("That task number doesn't exist! Choose a number from 1 to " + tasks.size() + ".");
        }
        return tasks.remove(index);
    }

    /**
     * Marks the task at the given 0-based index as done.
     *
     * @param index the 0-based index of the task
     * @return the updated task
     * @throws ChatException if the index is out of bounds
     */
    public Task markDone(int index) throws ChatException {
        Task task = get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the given 0-based index as not done.
     *
     * @param index the 0-based index of the task
     * @return the updated task
     * @throws ChatException if the index is out of bounds
     */
    public Task markNotDone(int index) throws ChatException {
        Task task = get(index);
        task.markAsNotDone();
        return task;
    }
}