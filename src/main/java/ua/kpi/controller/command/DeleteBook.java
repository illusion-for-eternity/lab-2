package ua.kpi.controller.command;

import ua.kpi.model.service.BookService;
import ua.kpi.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBook implements Command {
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = request.getRequestURI();
        int bookId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));

        bookService.delete(bookId);

        response.sendRedirect(request.getContextPath() + PathsHolder.BOOKS);
        return "redirect";
    }
}