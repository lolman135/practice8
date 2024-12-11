package practice;

import practice.tableUtils.Column;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * {@code Main} class demonstrates the usage of the {@code Database}, {@code TableBuilder}, and {@code Table} classes.
 *
 * @author Kyrylo Kusovskyi
 */
public class Main {

    public static void main(String[] args) {
        try (Connection connection = Database.getInstance().getConnection()) {

            TableBuilder builder = new TableBuilder();

            Table table = builder
                    .setConnection(connection)
                    .setTableName("users")
                    .addColumn(new Column("id", "INT"))
                    .addColumn(new Column("item_name", "VARCHAR(50)"))
                    .addColumn(new Column("quota", "INT"))
                    .build();

            table.delete();


        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
