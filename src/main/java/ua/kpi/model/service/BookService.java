package ua.kpi.model.service;

import ua.kpi.model.dao.jdbc.BookDao;
import ua.kpi.model.dao.jdbc.DaoConnection;
import ua.kpi.model.dao.jdbc.DaoFactory;
import ua.kpi.model.entity.Book;
import ua.kpi.model.service.exception.ServiceException;
import ua.kpi.utils.ErrorsMessages;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    private static class Holder {
        static final BookService INSTANCE = new BookService();
    }

    public static BookService getInstance() {
        return Holder.INSTANCE;
    }

    public void create(Book book) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            BookDao dao = daoFactory.createBookDao(connection);

            List<Book> existingBooks =
                    dao.findByTitleAndAuthor(book.getTitle(), book.getAuthor());

            if (!existingBooks.isEmpty()) {
                throw new ServiceException(ErrorsMessages.SERVICE_ERROR_BOOK_EXIST);
            }

            dao.create(book);
            connection.commit();
        }
    }

    public void update(Book book) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            BookDao dao = daoFactory.createBookDao(connection);
            dao.update(book);
            connection.commit();
        }
    }

    public void delete(int bookId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            connection.begin();
            BookDao dao = daoFactory.createBookDao(connection);
            dao.delete(bookId);
            connection.commit();
        }
    }

    public Optional<Book> getById(Integer id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            BookDao dao = daoFactory.createBookDao(connection);
            return dao.find(id);
        }
    }



    public List<Book> getAll() {
        System.out.println("BookService.getAll() START");
        try (DaoConnection connection = daoFactory.getConnection()) {
            System.out.println("Connection created");
            BookDao dao = daoFactory.createBookDao(connection);
            System.out.println("BookDao created");
            List<Book> books = dao.findAll();
            System.out.println("dao.findAll() DONE");
            return books;
        }
    }
}