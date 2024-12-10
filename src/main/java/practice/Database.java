package practice;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Getter
public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/practice8";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Database instance;
    private Connection connection;

    private Database() throws SQLException {
        this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static Database getInstance() throws SQLException{
        if (instance == null){
            instance = new Database();
        }
        return instance;
    }
}
