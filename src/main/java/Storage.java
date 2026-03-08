import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class Storage {
    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            return tasks;
        }

        try (Scanner fileScanner = new Scanner(file)) {
            int lineNumber = 0;
            while (fileScanner.hasNextLine()) {
                lineNumber++;
                String line = fileScanner.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                try {
                    Task task = parseTask(line);
                    tasks.add(task);
                } catch (ChatException e) {
                    System.out.println("[Warning] Skipping corrupted line " + lineNumber + ": " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("[Warning] Could not read data file: " + e.getMessage());
        }

        return tasks;
    }


    public void save(ArrayList<Task> tasks) {
        try {
            File file = new File(filePath);
            File parentDir = file.getParentFile();
            if (parentDir != null && !parentDir.exists()) {
                parentDir.mkdirs();
            }

            FileWriter writer = new FileWriter(file);
            for (Task task : tasks) {
                writer.write(serialiseTask(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("[Warning] Could not save tasks: " + e.getMessage());
        }
    }


    private String serialiseTask(Task task) {
        String done = task.isDone() ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + done + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "D | " + done + " | " + d.getDescription() + " | " + d.by;
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "E | " + done + " | " + e.getDescription() + " | " + e.from + " | " + e.to;
        }
        return "";
    }


    private Task parseTask(String line) throws ChatException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new ChatException("Too few fields: " + line);
        }

        String type = parts[0].trim();
        String doneFlag = parts[1].trim();
        String description = parts[2].trim();

        if (!doneFlag.equals("0") && !doneFlag.equals("1")) {
            throw new ChatException("Invalid done flag: " + doneFlag);
        }
        boolean isDone = doneFlag.equals("1");

        Task task;
        switch (type) {
        case "T":
            task = new Todo(description);
            break;
        case "D":
            if (parts.length < 4) {
                throw new ChatException("Deadline missing /by field: " + line);
            }
            task = new Deadline(description, parts[3].trim());
            break;
        case "E":
            if (parts.length < 5) {
                throw new ChatException("Event missing /from or /to field: " + line);
            }
            task = new Event(description, parts[3].trim(), parts[4].trim());
            break;
        default:
            throw new ChatException("Unknown task type: " + type);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}