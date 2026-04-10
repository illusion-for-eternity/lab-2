package ua.kpi.controller.command;

import ua.kpi.model.entity.Book;
import ua.kpi.model.service.BookService;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBooks implements Command {
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Book> books = bookService.getAll();

        request.setAttribute(AttributesHolder.BOOKS, books);
        return PagesHolder.BOOKS;
    }
}