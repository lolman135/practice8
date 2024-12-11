package practice;

import practice.tableUtils.Column;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TableBuilder {

    private Connection connection;
    private String tableName;
    private final List<Column> columnHandler = new ArrayList<>();

    public TableBuilder setConnection(Connection connection){
        this.connection = connection;
        return this;
    }

    public TableBuilder setTableName(String tableName){
        this.tableName = tableName;
        return this;
    }

    public TableBuilder addColumn(Column column){
        columnHandler.add(column);
        return this;
    }

    public Table build(){
        return new Table(connection, tableName, columnHandler);
    }
}
