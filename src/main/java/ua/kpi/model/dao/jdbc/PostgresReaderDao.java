package ua.kpi.model.dao.jdbc;

import ua.kpi.model.entity.Reader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresReaderDao implements ReaderDao {
    private final Connection connection;

    public PostgresReaderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Reader> find(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("SELECT * FROM reader WHERE id=?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Reader reader = new Reader(); // порожній конструктор
                reader.setId(rs.getInt("id"));
                reader.setFirstName(rs.getString("first_name"));
                reader.setLastName(rs.getString("last_name"));
                return Optional.of(reader);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Reader> findAll() {
        List<Reader> readers = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery("SELECT * FROM reader");
            while (rs.next()) {
                Reader reader = new Reader(); // порожній конструктор
                reader.setId(rs.getInt("id"));
                reader.setFirstName(rs.getString("first_name"));
                reader.setLastName(rs.getString("last_name"));
                readers.add(reader);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return readers;
    }

    @Override
    public void create(Reader reader) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO reader(first_name, last_name) VALUES (?, ?)")) {
            stmt.setString(1, reader.getFirstName());
            stmt.setString(2, reader.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Reader reader) {
        try (PreparedStatement stmt = connection.prepareStatement(
                "UPDATE reader SET first_name=?, last_name=? WHERE id=?")) {
            stmt.setString(1, reader.getFirstName());
            stmt.setString(2, reader.getLastName());
            stmt.setInt(3, reader.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try (PreparedStatement stmt = connection.prepareStatement("DELETE FROM reader WHERE id=?")) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Reader> findByLastName(String lastName) {
        List<Reader> result = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(
                "SELECT * FROM reader WHERE last_name=?")) {
            stmt.setString(1, lastName);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Reader reader = new Reader(); // порожній конструктор
                reader.setId(rs.getInt("id"));
                reader.setFirstName(rs.getString("first_name"));
                reader.setLastName(rs.getString("last_name"));
                result.add(reader);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}