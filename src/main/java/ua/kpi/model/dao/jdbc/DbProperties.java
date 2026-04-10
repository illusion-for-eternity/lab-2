package ua.kpi.model.dao.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = DbProperties.class.getResourceAsStream("/db.properties")) {
            props.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return props.getProperty(key);
    }
}