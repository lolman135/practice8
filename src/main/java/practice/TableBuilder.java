package practice;

import practice.tableUtils.Column;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code TableBuilder} class is a builder for creating {@code Table}
 * objects with a specified connection, table name, and columns.
 *
 * @author Kyrylo Kusovskyi
 */
public class TableBuilder {

    private Connection connection;
    private String tableName;
    private final List<Column> columnHandler = new ArrayList<>();

    /**
     * Sets the database connection for the {@code Table}.
     *
     * @param connection the database connection
     * @return the {@code TableBuilder} instance
     */
    public TableBuilder setConnection(Connection connection) {
        this.connection = connection;
        return this;
    }

    /**
     * Sets the name of the {@code Table}.
     *
     * @param tableName the name of the table
     * @return the {@code TableBuilder} instance
     */
    public TableBuilder setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * Adds a column to the {@code Table}.
     *
     * @param column the {@code Column} to be added
     * @return the {@code TableBuilder} instance
     */
    public TableBuilder addColumn(Column column) {
        columnHandler.add(column);
        return this;
    }

    /**
     * Builds and returns a {@code Table} instance.
     *
     * @return the {@code Table} instance
     */
    public Table build() {
        return new Table(connection, tableName, columnHandler);
    }
}
