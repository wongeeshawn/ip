import java.util.Scanner;

public class Chat {
    private static Task[] tasks = new Task[100];
    private static int taskCount = 0;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        printMessage("Hello! I'm Chat");
        printMessage("What can I do for you?");

        String input;
        while (true) {
            input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if (input.equals("bye")) {
                printMessageAndClose("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                printList();
                closeMessage();
            } else if (input.startsWith("mark ")) {
                handleMark(input);
            } else if (input.startsWith("unmark ")) {
                handleUnmark(input);
            } else if (input.startsWith("todo ")) {
                handleTodo(input);
            } else if (input.startsWith("deadline ")) {
                handleDeadline(input);
            } else if (input.startsWith("event ")) {
                handleEvent(input);
            } else {
                handleAddTask(input);
            }
        }

        scanner.close();
    }

    private static void handleAddTask(String description) {
        tasks[taskCount] = new Todo(description);
        taskCount++;
        printMessageAndClose("added: " + description);
    }

    private static void handleTodo(String input) {
        String description = input.substring(5).trim();
        tasks[taskCount] = new Todo(description);
        taskCount++;
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + tasks[taskCount - 1]);
        System.out.println("     Now you have " + taskCount + " tasks in the list.");
        closeMessage();
    }

    private static void handleDeadline(String input) {
        int byIndex = input.indexOf("/by");
        if (byIndex != -1) {
            String description = input.substring(9, byIndex).trim();
            String by = input.substring(byIndex + 3).trim();
            tasks[taskCount] = new Deadline(description, by);
            taskCount++;
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + tasks[taskCount - 1]);
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
            closeMessage();
        } else {
            printMessageAndClose("Please specify the deadline with /by");
        }
    }

    private static void handleEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex != -1 && toIndex != -1) {
            String description = input.substring(6, fromIndex).trim();
            String from = input.substring(fromIndex + 5, toIndex).trim();
            String to = input.substring(toIndex + 3).trim();
            tasks[taskCount] = new Event(description, from, to);
            taskCount++;
            System.out.println("     Got it. I've added this task:");
            System.out.println("       " + tasks[taskCount - 1]);
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
            closeMessage();
        } else {
            printMessageAndClose("Please specify the event with /from and /to");
        }
    }

    private static void printList() {
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskCount; i++) {
            System.out.println("     " + (i + 1) + "." + tasks[i]);
        }
    }

    private static void handleMark(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(5).trim());
            int taskIndex = taskNumber - 1;

            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsDone();
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + tasks[taskIndex]);
                closeMessage();
            } else {
                printMessageAndClose("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            printMessageAndClose("Please provide a valid task number!");
        }
    }

    private static void handleUnmark(String input) {
        try {
            int taskNumber = Integer.parseInt(input.substring(7).trim());
            int taskIndex = taskNumber - 1;

            if (taskIndex >= 0 && taskIndex < taskCount) {
                tasks[taskIndex].markAsNotDone();
                System.out.println("     OK, I've marked this task as not done yet:");
                System.out.println("       " + tasks[taskIndex]);
                closeMessage();
            } else {
                printMessageAndClose("Invalid task number!");
            }
        } catch (NumberFormatException e) {
            printMessageAndClose("Please provide a valid task number!");
        }
    }

    private static void printMessage(String message) {
        System.out.println("    ____________________________________________________________");
        System.out.println("     " + message);
        System.out.println("    ____________________________________________________________\n");
    }

    private static void printMessageAndClose(String message) {
        System.out.println("     " + message);
        closeMessage();
    }

    private static void closeMessage() {
        System.out.println("    ____________________________________________________________\n");
    }
}