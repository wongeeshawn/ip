import java.util.Scanner;

public class Chat {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Hello! I'm Chat");
        System.out.println("     What can I do for you?");
        System.out.println("    ____________________________________________________________\n");

        String input;
        while (true) {
            input = scanner.nextLine();

            System.out.println("    ____________________________________________________________");
            System.out.println("     " + input);
            System.out.println("    ____________________________________________________________\n");

            if (input.equals("bye")) {
                break;
            }
        }

        scanner.close();
    }
}