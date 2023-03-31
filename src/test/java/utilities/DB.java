package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DB implements DBMethods{

    private Connection con;
    private Statement stt;
    private ResultSet rs;
    private ResultSetMetaData rMeta;

    public ResultSet getRs() {
        return rs;
    }

    public Connection getCon() {
        return con;
    }

    public Statement getStt() {
        return stt;
    }

    public ResultSetMetaData getrMeta() {
        return rMeta;
    }

    public DB() throws SQLException {
         con = DriverManager.getConnection(
                ConfigReader.getProperty("DBURL"),
                ConfigReader.getProperty("DBUSER"),
                ConfigReader.getProperty("DBPassword"));
        stt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }

    public ResultSet executeSelectQuery(String query) throws SQLException {
        rs = stt.executeQuery(query);
       return rs;
    }
    public ResultSetMetaData getMetaForRs(ResultSet resultSet) throws SQLException {
        rMeta = resultSet.getMetaData();
        return rMeta;
    }
    public List<Map<String, Object>> getTableForQuery(String query) throws SQLException {
        rs = executeSelectQuery(query);
        rMeta = getMetaForRs(rs);
        List<Map<String, Object>> tableData = new ArrayList<>();

        while (this.rs.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= rMeta.getColumnCount(); i++) {
                rowData.put(rMeta.getColumnName(i), rs.getString(rMeta.getColumnName(i)));
            }
            tableData.add(rowData);
        }
        return tableData;

    }


    @Override
    public ResultSet runSelectQuery(String query) throws SQLException {
        rs = stt.executeQuery(query);
        return rs;
    }
    public ResultSet runSelectQuery(String coulmnName,String columnName2, String tableName) throws SQLException {
        String query = "Select " + coulmnName +","+ columnName2 + " from " + tableName;
        rs = stt.executeQuery(query);
        return rs;
    }
    public ResultSet runSelectQuery(String columnName,String tableName) throws SQLException {
       String query = "Select " + columnName + " from " + tableName;
        rs = stt.executeQuery(query);
        return rs;
    }
    public ResultSet runSelectQuery(ArrayList<String> columnName, String tableName) throws SQLException {
        String query = "Select ";
        for(String cName: columnName){
            query = query + cName + ", ";
        }
        query = query.substring(0, query.length()-2);
        query = query + " from "  + tableName;
        rs = stt.executeQuery(query);
        return rs;
    }

    @Override
    public void runUpdateQuery(String query) throws SQLException {
        stt.executeUpdate(query);
        System.out.println("DB was updated");
    }

    @Override
    public void runInsertQuery(String query) throws SQLException {
        stt.executeUpdate(query);
        System.out.println("DB was updated!");

    }

    @Override
    public void runDeleteQuery(String query) throws SQLException {
        stt.executeUpdate(query);
        System.out.println("Delete was successfully!");
    }

    public void close() throws SQLException {
        con.close();
        stt.close();
    }
}
