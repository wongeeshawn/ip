public class Chat {
    private static final String FILE_PATH = "./data/chat.txt";

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Chat(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (ChatException e) {
                ui.showError(e.getMessage());
            } catch (Exception e) {
                ui.showError("Something went wrong: " + e.getMessage());
            } finally {
                ui.showLineTail();
            }
        }
        ui.close();
    }

    public static void main(String[] args) {
        new Chat(FILE_PATH).run();
    }
}