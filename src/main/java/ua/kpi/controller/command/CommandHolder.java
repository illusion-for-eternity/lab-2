package ua.kpi.controller.command;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    public static final String DELIMITER = ":";

    private final Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {
        commands.put("GET:/books", new GetBooks());
        commands.put("GET:/books/addBook", new GetBook());
        commands.put("GET:/books/editBook", new GetBook());

        commands.put("POST:/books/addBook", new AddBook());
        commands.put("POST:/books/editBook", new EditBook());
        commands.put("POST:/books/delete", new DeleteBook());
    }

    public Command getCommand(String commandKey) {
        return commands.getOrDefault(commandKey, (req, resp) -> "/WEB-INF/view/error/pageNotFound.jsp");
    }
}