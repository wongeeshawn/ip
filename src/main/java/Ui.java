import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all interactions with the user.
 * Responsible for reading user input and displaying output messages.
 */
public class Ui {
    private static final String DIVIDER = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /** Reads the next line of user input. */
    public String readCommand() {
        return scanner.nextLine();
    }

    /** Closes the scanner. */
    public void close() {
        scanner.close();
    }

    /** Prints the divider line. */
    public void showLine() {
        System.out.println(DIVIDER);
    }

    /** Prints the welcome message. */
    public void showWelcome() {
        showLine();
        System.out.println(INDENT + "Hello! I'm Chat");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    /** Prints the goodbye message. */
    public void showBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

    /** Prints an error message. */
    public void showError(String message) {
        System.out.println(INDENT + "Error: " + message);
    }

    /** Prints a loading error when saved tasks cannot be read. */
    public void showLoadingError() {
        System.out.println(INDENT + "[Warning] Could not load saved tasks. Starting fresh.");
    }

    /** Prints a closing newline after the divider. */
    public void showLineTail() {
        System.out.println(DIVIDER + "\n");
    }

    /** Shows the full task list. */
    public void showTaskList(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "You don't have any tasks yet! Why not add one to get started?");
        } else {
            System.out.println(INDENT + "Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
            }
        }
    }

    /** Shows a message confirming a task was added. */
    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    /** Shows a message confirming a task was deleted. */
    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    /** Shows a message confirming a task was marked as done. */
    public void showTaskMarkedDone(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    /** Shows a message confirming a task was marked as not done. */
    public void showTaskMarkedNotDone(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }
}