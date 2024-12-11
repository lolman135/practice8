package practice;

import lombok.AllArgsConstructor;
import practice.tableUtils.Column;

import java.sql.*;
import java.util.List;
import java.util.Objects;

/**
 * {@code Table} class represents a database table and provides methods for creating, reading, and deleting it.
 *
 * @author Kyrylo Kusovskyi
 */
@AllArgsConstructor
public class Table {

    private Connection connection;
    private String tableName;
    private List<Column> columnHandler;

    /**
     * Creates the table in the database.
     *
     * @throws SQLException if a database access error occurs
     */
    public void create() throws SQLException {
        String request = formCreateRequest();
        executeRequest(request, "Create table " + tableName);
    }

    /**
     * Deletes the table from the database.
     *
     * @throws SQLException if a database access error occurs
     */
    public void delete() throws SQLException {
        String request = "DROP TABLE " + tableName + ';';
        executeRequest(request, "Delete table " + tableName);
    }

    /**
     * Reads and prints all rows from the table.
     *
     * @throws SQLException if a database access error occurs
     */
    public void read() throws SQLException {
        String request = "SELECT * FROM " + tableName + ';';
        try (
                PreparedStatement statement = connection.prepareStatement(request);
                ResultSet resultSet = statement.executeQuery()
        ) {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                System.out.print(metaData.getColumnName(i) + "\t");
            }
            System.out.println();

            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(resultSet.getString(i) + "\t\t");
                }
                System.out.println();
            }
        }
    }

    /**
     * Forms the SQL CREATE TABLE request.
     *
     * @return the SQL request string
     */
    private String formCreateRequest() {
        StringBuilder requestPart = new StringBuilder("CREATE TABLE " + tableName + " (");
        formFieldsRow(requestPart);
        requestPart.append(";");
        return requestPart.toString();
    }

    /**
     * Constructs the fields part of the CREATE TABLE request.
     *
     * @param requestPart the {@code StringBuilder} to append field definitions
     */
    private void formFieldsRow(StringBuilder requestPart) {
        columnHandler.stream()
                .filter(Objects::nonNull)
                .forEach(column -> {
                    requestPart.append(column.getName()).append(" ").append(column.getDataType());
                    addComma(requestPart, column);
                });
        requestPart.append(")");
    }

    /**
     * Adds a comma to the SQL request if more fields follow.
     *
     * @param requestPart the {@code StringBuilder} to append
     * @param column      the current {@code Column} being processed
     */
    private void addComma(StringBuilder requestPart, Column column) {
        if (columnHandler.indexOf(column) < columnHandler.size() - 1) {
            requestPart.append(", ");
        }
    }

    /**
     * Executes a SQL request and prints a confirmation message.
     *
     * @param request the SQL request string
     * @param message the confirmation message
     * @throws SQLException if a database access error occurs
     */
    private void executeRequest(String request, String message) throws SQLException {
        try (PreparedStatement createStatement = connection.prepareStatement(request)) {
            createStatement.execute();
            System.out.println(message);
        }
    }
}