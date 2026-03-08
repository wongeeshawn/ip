/**
 * Parses raw user input strings into structured Command objects.
 */
public class Parser {
    private static final int COMMAND_TODO_LENGTH = 5;
    private static final int COMMAND_DEADLINE_LENGTH = 9;
    private static final int COMMAND_EVENT_LENGTH = 6;
    private static final int COMMAND_MARK_LENGTH = 5;
    private static final int COMMAND_UNMARK_LENGTH = 7;
    private static final int COMMAND_DELETE_LENGTH = 7;
    private static final int COMMAND_FIND_LENGTH = 5;

    public static Command parse(String input) throws ChatException {
        if (input.trim().isEmpty()) {
            throw new ChatException("Hmm, I didn't catch that. Could you try again?");
        }

        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("mark") || input.equals("unmark")) {
            throw new ChatException("Which task would you like me to " + input + "? Please provide a task number.");
        } else if (input.startsWith("mark ")) {
            return new MarkCommand(parseIndex(input, COMMAND_MARK_LENGTH), true);
        } else if (input.startsWith("unmark ")) {
            return new MarkCommand(parseIndex(input, COMMAND_UNMARK_LENGTH), false);
        } else if (input.equals("delete")) {
            throw new ChatException("Which task would you like me to delete? Please provide a task number.");
        } else if (input.startsWith("delete ")) {
            return new DeleteCommand(parseIndex(input, COMMAND_DELETE_LENGTH));
        } else if (input.equals("todo")) {
            throw new ChatException("You forgot to tell me what the todo is! Please add a description.");
        } else if (input.startsWith("todo ")) {
            return parseTodo(input);
        } else if (input.equals("deadline")) {
            throw new ChatException("You forgot to tell me what the deadline is! Please add a description.");
        } else if (input.startsWith("deadline ")) {
            return parseDeadline(input);
        } else if (input.equals("event")) {
            throw new ChatException("You forgot to tell me what the event is! Please add a description.");
        } else if (input.startsWith("event ")) {
            return parseEvent(input);
        } else if (input.equals("find")) {
            throw new ChatException("What would you like to find? Please provide a keyword.");
        } else if (input.startsWith("find ")) {
            String keyword = input.substring(COMMAND_FIND_LENGTH).trim();
            if (keyword.isEmpty()) {
                throw new ChatException("What would you like to find? Please provide a keyword.");
            }
            return new FindCommand(keyword);
        } else {
            throw new ChatException("I don't recognize that command. Type 'list' to see your tasks or try adding a todo, deadline, or event.");
        }
    }

    private static int parseIndex(String input, int commandLength) throws ChatException {
        try {
            String numberStr = input.substring(commandLength).trim();
            if (numberStr.isEmpty()) {
                throw new ChatException("Please provide a valid task number.");
            }
            return Integer.parseInt(numberStr) - 1;
        } catch (NumberFormatException e) {
            throw new ChatException("That's not a valid task number. Please enter a number.");
        }
    }

    private static AddCommand parseTodo(String input) throws ChatException {
        String description = input.substring(COMMAND_TODO_LENGTH).trim();
        if (description.isEmpty()) {
            throw new ChatException("Your todo needs a description! What would you like to do?");
        }
        return new AddCommand(new Todo(description));
    }

    private static AddCommand parseDeadline(String input) throws ChatException {
        int byIndex = input.indexOf("/by");
        if (byIndex == -1) {
            throw new ChatException("Oops! I need to know when this is due. Use /by to specify the deadline.\n"
                    + "     Try: deadline <task description> /by <date/time>");
        }
        String description = input.substring(COMMAND_DEADLINE_LENGTH, byIndex).trim();
        String by = input.substring(byIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChatException("What's the deadline for? Please add a description before /by.");
        }
        if (by.isEmpty()) {
            throw new ChatException("When is this due? You need to specify a date/time after /by.");
        }
        return new AddCommand(new Deadline(description, by));
    }

    private static AddCommand parseEvent(String input) throws ChatException {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        if (fromIndex == -1 || toIndex == -1) {
            throw new ChatException("I need the event timing! Please use both /from and /to.\n"
                    + "     Try: event <description> /from <start time> /to <end time>");
        }
        if (fromIndex >= toIndex) {
            throw new ChatException("Hmm, something's not right. Make sure /from comes before /to!");
        }
        String description = input.substring(COMMAND_EVENT_LENGTH, fromIndex).trim();
        String from = input.substring(fromIndex + 5, toIndex).trim();
        String to = input.substring(toIndex + 3).trim();
        if (description.isEmpty()) {
            throw new ChatException("What's the event about? Please add a description before /from.");
        }
        if (from.isEmpty()) {
            throw new ChatException("When does the event start? You need to specify a time after /from.");
        }
        if (to.isEmpty()) {
            throw new ChatException("When does the event end? You need to specify a time after /to.");
        }
        return new AddCommand(new Event(description, from, to));
    }
}