import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[100];
        int taskCount = 0;

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Chat");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        String input;
        while (true) {
            input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");

            if (input.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ____________________________________________________________\n");
                break;
            } else if (input.equals("list")) {
                for (int i = 0; i < taskCount; i++) {
                    System.out.println("     " + (i + 1) + ". " + tasks[i]);
                }
                System.out.println("    ____________________________________________________________\n");
            } else {
                tasks[taskCount] = input;
                taskCount++;
                System.out.println("     added: " + input);
                System.out.println("    ____________________________________________________________\n");
            }
        }

        scanner.close();
    }
}