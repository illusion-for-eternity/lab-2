package ua.kpi.model.dao.jdbc;

public class PostgresDaoFactory extends DaoFactory {

    @Override
    public DaoConnection getConnection() {
        return new PostgresDaoConnection();
    }

    @Override
    public ReaderDao createReaderDao(DaoConnection daoConnection) {
        return new PostgresReaderDao(((PostgresDaoConnection) daoConnection).getConnection());
    }

    @Override
    public BookDao createBookDao(DaoConnection daoConnection) {
        return new PostgresBookDao(((PostgresDaoConnection) daoConnection).getConnection());
    }
}