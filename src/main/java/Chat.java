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
            } else {
                handleAddTask(input);
            }
        }

        scanner.close();
    }

    private static void handleAddTask(String description) {
        tasks[taskCount] = new Task(description);
        taskCount++;
        printMessageAndClose("added: " + description);
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
