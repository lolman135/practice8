package practice;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * {@code Database} class provides a singleton for managing a database connection.
 * This class is implementation of Singleton pattern. In that way you can have only one connection
 * to the database holds in different objects.
 *
 * @author Kyrylo Kusovskyi
 */
@Getter
public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/practice8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Database instance;
    private Connection connection;

    /**
     * Initializes the database connection.
     *
     * @throws SQLException if a database access error occurs
     */
    private Database() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    /**
     * Returns the singleton instance of {@code Database}.
     *
     * @return the {@code Database} instance
     * @throws SQLException if a database access error occurs
     */
    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }
}
