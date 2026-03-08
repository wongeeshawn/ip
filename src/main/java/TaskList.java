import java.util.ArrayList;

/**
 * Contains the task list and provides operations to add, delete, and retrieve tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /** Returns the underlying list (for saving/display). */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /** Returns the number of tasks. */
    public int size() {
        return tasks.size();
    }

    /** Returns true if there are no tasks. */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /** Gets a task by 0-based index. */
    public Task get(int index) throws ChatException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ChatException("You don't have any tasks! Add some tasks first.");
            }
            throw new ChatException("That task number doesn't exist! Choose a number from 1 to " + tasks.size() + ".");
        }
        return tasks.get(index);
    }

    /** Adds a task and returns it. */
    public Task add(Task task) {
        tasks.add(task);
        return task;
    }

    /** Removes a task by 0-based index and returns the removed task. */
    public Task delete(int index) throws ChatException {
        if (index < 0 || index >= tasks.size()) {
            if (tasks.isEmpty()) {
                throw new ChatException("You don't have any tasks to delete! Add some tasks first.");
            }
            throw new ChatException("That task number doesn't exist! Choose a number from 1 to " + tasks.size() + ".");
        }
        return tasks.remove(index);
    }

    /** Marks a task as done by 0-based index. */
    public Task markDone(int index) throws ChatException {
        Task task = get(index);
        task.markAsDone();
        return task;
    }

    /** Marks a task as not done by 0-based index. */
    public Task markNotDone(int index) throws ChatException {
        Task task = get(index);
        task.markAsNotDone();
        return task;
    }
}