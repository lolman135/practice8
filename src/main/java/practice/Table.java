package practice;

import lombok.AllArgsConstructor;
import practice.tableUtils.Column;

import java.sql.*;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Table {

    private Connection connection;
    private String tableName;
    private List<Column> columnHandler;

    public void create() throws SQLException {
        String request = formCreateRequest();
        executeRequest(request, "Create table " + tableName);
    }

    public void delete() throws SQLException{
        String request = "DROP TABLE " + tableName + ';';
        executeRequest(request, "Delete table " + tableName);
    }

    public void read() throws SQLException {
        String request = "SELECT * FROM " + tableName + ';';
        try (
                PreparedStatement statement = connection.prepareStatement(request);
                ResultSet resultSet = statement.executeQuery();
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

    private String formCreateRequest(){
        StringBuilder requestPart = new StringBuilder("CREATE TABLE " + tableName + " (");
        formFieldsRow(requestPart);
        requestPart.append(";");
        return requestPart.toString();
    }

    private void formFieldsRow(StringBuilder requestPart){
        columnHandler.stream()
                .filter(Objects::nonNull)
                .forEach(column -> {
                    requestPart.append(column.getName()).append(" ").append(column.getDataType());
                    addComma(requestPart, column);
                });
        requestPart.append(")");
    }

    private void addComma(StringBuilder requestPart, Column column){
        if(columnHandler.indexOf(column) < columnHandler.size() - 1){
            requestPart.append(", ");
        }
    }

    private void executeRequest(String request, String message) throws SQLException{
        try (PreparedStatement createStatement = connection.prepareStatement(request)) {
            createStatement.execute();
            System.out.println(message);
        }
    }
}
