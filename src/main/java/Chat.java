import java.util.Scanner;

/**
 * Main class for the Chat task manager application.
 * Handles user input and manages a list of tasks.
 */
public class Chat {
    private static final int MAX_TASKS = 100;
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_MARK = "mark ";
    private static final String COMMAND_UNMARK = "unmark ";
    private static final String COMMAND_TODO = "todo ";
    private static final String COMMAND_DEADLINE = "deadline ";
    private static final String COMMAND_EVENT = "event ";
    private static final String DIVIDER = "    ____________________________________________________________";
    private static final String INDENT = "     ";

    private static final int COMMAND_TODO_LENGTH = 5;
    private static final int COMMAND_DEADLINE_LENGTH = 9;
    private static final int COMMAND_EVENT_LENGTH = 6;
    private static final int COMMAND_MARK_LENGTH = 5;
    private static final int COMMAND_UNMARK_LENGTH = 7;

    private static Task[] tasks = new Task[MAX_TASKS];
    private static int taskCount = 0;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        showWelcomeMessage();
        processUserCommands();
        scanner.close();
    }

    /**
     * Displays the welcome message to the user.
     */
    private static void showWelcomeMessage() {
        printMessage("Hello! I'm Chat");
        printMessage("What can I do for you?");
    }

    /**
     * Continuously processes user commands until the exit command is received.
     */
    private static void processUserCommands() {
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println(DIVIDER);

            if (isExitCommand(input)) {
                printMessageAndClose("Bye. Hope to see you again soon!");
                break;
            }

            executeCommand(input);
        }
    }

    /**
     * Checks if the input is an exit command.
     */
    private static boolean isExitCommand(String input) {
        return input.equals(COMMAND_BYE);
    }

    /**
     * Executes the appropriate command based on user input.
     */
    private static void executeCommand(String input) {
        if (input.equals(COMMAND_LIST)) {
            listTasks();
        } else if (input.startsWith(COMMAND_MARK)) {
            markTaskAsDone(input);
        } else if (input.startsWith(COMMAND_UNMARK)) {
            markTaskAsNotDone(input);
        } else if (input.startsWith(COMMAND_TODO)) {
            addTodoTask(input);
        } else if (input.startsWith(COMMAND_DEADLINE)) {
            addDeadlineTask(input);
        } else if (input.startsWith(COMMAND_EVENT)) {
            addEventTask(input);
        } else {
            addDefaultTask(input);
        }
    }

    /**
     * Adds a default task (treated as a Todo) when no specific command is given.
     */
    private static void addDefaultTask(String description) {
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printMessageAndClose("added: " + description);
    }

    /**
     * Adds a Todo task to the task list.
     */
    private static void addTodoTask(String input) {
        String description = input.substring(COMMAND_TODO_LENGTH).trim();
        Task newTask = new Todo(description);
        addTask(newTask);
    }

    /**
     * Adds a Deadline task to the task list.
     */
    private static void addDeadlineTask(String input) {
        int byIndex = input.indexOf("/by");

        if (byIndex == -1) {
            printMessageAndClose("Please specify the deadline with /by");
            return;
        }

        String description = input.substring(COMMAND_DEADLINE_LENGTH, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        Task newTask = new Deadline(description, by);
        addTask(newTask);
    }

    /**
     * Adds an Event task to the task list.
     */
    private static void addEventTask(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1) {
            printMessageAndClose("Please specify the event with /from and /to");
            return;
        }

        String description = input.substring(COMMAND_EVENT_LENGTH, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        Task newTask = new Event(description, from, to);
        addTask(newTask);
    }

    /**
     * Adds a task to the task list and displays confirmation.
     */
    private static void addTask(Task task) {
        tasks[taskCount] = task;
        taskCount++;

        System.out.println(INDENT + "Got it. I've added this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + taskCount + " tasks in the list.");
        closeMessage();
    }

    /**
     * Displays all tasks in the task list.
     */
    private static void listTasks() {
        System.out.println(INDENT + "Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println(INDENT + (i + 1) + "." + tasks[i]);
        }
        closeMessage();
    }

    /**
     * Marks a task as done based on user input.
     */
    private static void markTaskAsDone(String input) {
        int taskIndex = parseTaskNumber(input, COMMAND_MARK_LENGTH);

        if (!isValidTaskIndex(taskIndex)) {
            printMessageAndClose("Invalid task number!");
            return;
        }

        tasks[taskIndex].markAsDone();
        System.out.println(INDENT + "Nice! I've marked this task as done:");
        System.out.println(INDENT + "  " + tasks[taskIndex]);
        closeMessage();
    }

    /**
     * Marks a task as not done based on user input.
     */
    private static void markTaskAsNotDone(String input) {
        int taskIndex = parseTaskNumber(input, COMMAND_UNMARK_LENGTH);

        if (!isValidTaskIndex(taskIndex)) {
            printMessageAndClose("Invalid task number!");
            return;
        }

        tasks[taskIndex].markAsNotDone();
        System.out.println(INDENT + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + "  " + tasks[taskIndex]);
        closeMessage();
    }

    /**
     * Parses the task number from the user input.
     * Returns the task index (0-based), or -1 if parsing fails.
     */
    private static int parseTaskNumber(String input, int commandLength) {
        try {
            int taskNumber = Integer.parseInt(input.substring(commandLength).trim());
            return taskNumber - 1;
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * Checks if the task index is valid.
     */
    private static boolean isValidTaskIndex(int taskIndex) {
        return taskIndex >= 0 && taskIndex < taskCount;
    }

    /**
     * Prints a message with dividers above and below.
     */
    private static void printMessage(String message) {
        System.out.println(DIVIDER);
        System.out.println(INDENT + message);
        System.out.println(DIVIDER + "\n");
    }

    /**
     * Prints a message and closes with a divider.
     */
    private static void printMessageAndClose(String message) {
        System.out.println(INDENT + message);
        closeMessage();
    }

    /**
     * Prints the closing divider.
     */
    private static void closeMessage() {
        System.out.println(DIVIDER + "\n");
    }
}