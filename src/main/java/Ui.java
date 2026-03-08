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

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

    public void showWelcome() {
        showLine();
        System.out.println(INDENT + "Hello! I'm Chat");
        System.out.println(INDENT + "What can I do for you?");
        System.out.println(DIVIDER + "\n");
    }

    public void showBye() {
        System.out.println(INDENT + "Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println(INDENT + "Error: " + message);
    }

    public void showLoadingError() {
        System.out.println(INDENT + "[Warning] Could not load saved tasks. Starting fresh.");
    }

    public void showLineTail() {
        System.out.println(DIVIDER + "\n");
    }

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

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskDeleted(Task task, int totalTasks) {
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + totalTasks + " tasks in the list.");
    }

    public void showTaskMarkedDone(Task task) {
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + task);
    }

    public void showTaskMarkedNotDone(Task task) {
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + task);
    }

    public void showMatchingTasks(ArrayList<Task> matches) {
        if (matches.isEmpty()) {
            System.out.println(INDENT + "No matching tasks found.");
        } else {
            System.out.println(INDENT + "Here are the matching tasks in your list:");
            for (int i = 0; i < matches.size(); i++) {
                System.out.println(INDENT + (i + 1) + "." + matches.get(i));
            }
        }
    }
}