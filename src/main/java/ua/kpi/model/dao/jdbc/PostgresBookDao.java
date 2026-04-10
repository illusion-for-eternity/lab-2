package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.exception.DaoException;
import ua.kpi.model.entity.Book;
import ua.kpi.model.entity.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresBookDao implements BookDao {
    private final Connection connection;

    public PostgresBookDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Book> find(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, title, author, genre, taken_by_reader_id FROM book WHERE id=?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));

                int readerId = rs.getInt("taken_by_reader_id");
                if (!rs.wasNull()) {
                    book.setTakenByreader(
                            new Reader.Builder().setId(readerId).build()
                    );
                }

                return Optional.of(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM book WHERE title ILIKE ?")) { // ILIKE для PostgreSQL, щоб не чутливо до регістру
            stmt.setString(1, "%" + title + "%"); // пошук часткової відповідності
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();         // порожній конструктор
                book.setId(rs.getInt("id"));    // заповнюємо через сеттери
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return books;
    }
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT id, title, author, genre, taken_by_reader_id FROM book");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setGenre(rs.getString("genre"));

                int readerId = rs.getInt("taken_by_reader_id");
                if (!rs.wasNull()) {
                    book.setTakenByreader(new Reader.Builder().setId(readerId).build());
                }

                books.add(book);
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return books;
    }

    @Override
    public List<Book> findByTitleAndAuthor(String title, String author) {
        List<Book> books = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM book WHERE title = ? AND author = ?")) {

            stmt.setString(1, title);
            stmt.setString(2, author);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public void create(Book book) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO book(title, author, genre, taken_by_reader_id) VALUES (?, ?, ?, ?)")) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());

            if (book.getTakenByreader() != null) {
                stmt.setInt(4, book.getTakenByreader().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void update(Book book) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE book SET title=?, author=?, genre=?, taken_by_reader_id=? WHERE id=?")) {

            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setString(3, book.getGenre());

            if (book.getTakenByreader() != null) {
                stmt.setInt(4, book.getTakenByreader().getId());
            } else {
                stmt.setNull(4, Types.INTEGER);
            }

            stmt.setInt(5, book.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM book WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}