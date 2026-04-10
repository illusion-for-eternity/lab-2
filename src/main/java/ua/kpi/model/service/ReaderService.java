package ua.kpi.model.service;

import ua.kpi.model.dao.jdbc.DaoConnection;
import ua.kpi.model.dao.jdbc.DaoFactory;
import ua.kpi.model.dao.jdbc.ReaderDao;
import ua.kpi.model.entity.Reader;
import ua.kpi.model.service.exception.ServiceException;
import ua.kpi.utils.ErrorsMessages;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ReaderService {
    private final DaoFactory daoFactory = DaoFactory.getInstance();

    public static final String SERVICE_ERROR_READER_EXIST =
            "Reader with this ID ('%s') already exists";

    private static class Holder {
        static final ReaderService INSTANCE = new ReaderService();
    }

    public static ReaderService getInstance() {
        return Holder.INSTANCE;
    }

    public void create(Reader reader) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao dao = daoFactory.createReaderDao(connection);
            connection.begin();
            Optional<Reader> existingReader = dao.find(reader.getId());
            checkIfReaderExists(existingReader);
            dao.create(reader);
            connection.commit();
        }
    }

    public void update(Reader reader) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao dao = daoFactory.createReaderDao(connection);
            connection.begin();
            Optional<Reader> existingReader = dao.find(reader.getId());
            existingReader.ifPresent(r -> {
                if (!Objects.equals(reader.getId(), r.getId())) {
                    checkIfReaderExists(existingReader);
                }
            });
            dao.update(reader);
            connection.commit();
        }
    }

    public void delete(int readerId) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao dao = daoFactory.createReaderDao(connection);
            dao.delete(readerId);
        }
    }

    public Optional<Reader> getById(Integer id) {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao dao = daoFactory.createReaderDao(connection);
            return dao.find(id);
        }
    }

    public List<Reader> getAll() {
        try (DaoConnection connection = daoFactory.getConnection()) {
            ReaderDao dao = daoFactory.createReaderDao(connection);
            return dao.findAll();
        }
    }

    private void checkIfReaderExists(Optional<Reader> existingReader) {
        existingReader.ifPresent(r -> {
            throw new ServiceException(
                    ErrorsMessages.SERVICE_ERROR_READER_EXIST)
                    .addLogMessage(String.format(SERVICE_ERROR_READER_EXIST, r.getId()));
        });
    }
}