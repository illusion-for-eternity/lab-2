package ua.kpi.model.dao.jdbc;

import ua.kpi.model.dao.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public abstract class DaoFactory {
    public abstract DaoConnection getConnection();

    public abstract ReaderDao createReaderDao(DaoConnection daoConnection);
    public abstract BookDao createBookDao(DaoConnection daoConnection);

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";
    private static  DaoFactory instance;

    public static DaoFactory getInstance(){
        if( instance == null) {
            try {
                System.out.println("Loading db.properties...");
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);

                if (inputStream == null) {
                    throw new RuntimeException("db.properties not found");
                }

                Properties dbProps = new Properties();
                dbProps.load(inputStream);

                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                System.out.println("factory.class = " + factoryClass);

                instance = (DaoFactory) Class.forName(factoryClass)
                        .getDeclaredConstructor()
                        .newInstance();

            } catch (IOException | IllegalAccessException |
                     InstantiationException | ClassNotFoundException e ) {
                e.printStackTrace();
                throw new DaoException(e);
            } catch (InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return instance;
    }
}