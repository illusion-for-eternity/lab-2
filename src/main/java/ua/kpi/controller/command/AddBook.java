package ua.kpi.controller.command;

import ua.kpi.controller.validator.BookValidator;
import ua.kpi.controller.validator.Errors;
import ua.kpi.controller.validator.Validator;
import ua.kpi.model.entity.Book;
import ua.kpi.model.entity.Reader;
import ua.kpi.model.service.BookService;
import ua.kpi.model.service.ReaderService;
import ua.kpi.utils.AttributesHolder;
import ua.kpi.utils.PagesHolder;
import ua.kpi.utils.PathsHolder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddBook implements Command {

    private final BookService bookService = BookService.getInstance();
    private final ReaderService readerService = ReaderService.getInstance();
    private final Validator<Book> validator = new BookValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Book book = buildBook(request);

        Errors errors = new Errors();

        if (validator.validate(book, errors)) {
            bookService.create(book);
            response.sendRedirect(request.getContextPath() + PathsHolder.BOOKS);
            return FrontController.REDIRECT;
        }

        List<Reader> readers = readerService.getAll();
        request.setAttribute(AttributesHolder.READERS, readers);
        request.setAttribute(AttributesHolder.ERRORS, errors);
        request.setAttribute(AttributesHolder.BOOK, book);

        return PagesHolder.BOOK;
    }

    private Book buildBook(HttpServletRequest request) {
        return new Book.Builder()
                .setTitle(request.getParameter(AttributesHolder.TITLE))
                .setAuthor(request.getParameter(AttributesHolder.AUTHOR))
                .setGenre(request.getParameter(AttributesHolder.GENRE))
                .setTakenByreader(getTakenByReader(request))
                .build();
    }

    private Reader getTakenByReader(HttpServletRequest request) {
        try {
            int readerId = Integer.parseInt(
                    request.getParameter(AttributesHolder.TAKEN_BY_READER_ID));
            return new Reader.Builder().setId(readerId).build();
        } catch (NumberFormatException e) {
            return null;
        }
    }
}