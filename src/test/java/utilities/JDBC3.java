package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC3 {
    public static void main(String[] args) throws SQLException {

        Connection con = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_Production",
                "postgres",
                "admin");

        Statement stt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        String query = "select * from employees";
        ResultSet resultSet = stt.executeQuery(query);
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        List<Map<String, Object>> tableData = new ArrayList<>();

        while (resultSet.next()) {
            Map<String, Object> rowData = new HashMap<>();
            for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                rowData.put(resultSetMetaData.getColumnName(i), resultSet.getString(resultSetMetaData.getColumnName(i)));
            }
            tableData.add(rowData);
        }
        System.out.println(tableData);

        findEmployeeEmpID(tableData,100);
        findEmployeeEmailWithEmpFname(tableData, "Irene");

    }

    private static void findEmployeeEmailWithEmpFname(List<Map<String, Object>> tableData, String fname) {
        for(Map row : tableData){
         if(row.get("first_name").toString().equals(fname)){
             System.out.println(row.get("email"));
             break;
         }

        }
        System.out.println("Finished");


    }

    private static void findEmployeeEmpID(List<Map<String, Object>> tableData, int id) {
        for(Map row:tableData){
            int emp_id = Integer.parseInt(row.get("employee_id").toString());
            if(emp_id == id){
                System.out.println(row.get("first_name"));
                break;
            }
        }
        System.out.println("Finished");
    }

}
