package ua.kpi.test;

import java.sql.Connection;
import java.sql.DriverManager;

public class TestDB {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/librarydb",
                "postgres",
                "0102")) {
            System.out.println("hugyftxhfjghjli");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}