package ua.kpi.controller.command;

import ua.kpi.model.entity.Book;
import ua.kpi.model.entity.Reader;
import ua.kpi.model.service.BookService;
import ua.kpi.model.service.ReaderService;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.PagesHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class GetBook implements Command {
    private final BookService bookService = BookService.getInstance();
    private final ReaderService readerService = ReaderService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Reader> readers = readerService.getAll();
        request.setAttribute(AttributesHolder.READERS, readers);

        String path = request.getRequestURI();
        if (path.contains("/editBook")) {
            int bookId = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            Optional<Book> book = bookService.getById(bookId);
            book.ifPresent(b -> request.setAttribute(AttributesHolder.BOOK, b));
        }

        return PagesHolder.BOOK;
    }
}