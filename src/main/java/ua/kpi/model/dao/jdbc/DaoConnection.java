package ua.kpi.model.dao.jdbc;

public interface DaoConnection extends AutoCloseable {
    void begin();
    void commit();
    void rollback();
    void close();
}
