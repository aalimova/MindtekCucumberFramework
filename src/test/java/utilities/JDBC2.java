package utilities;

import io.cucumber.java.it.Ma;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBC2 {

    public static void main(String[] args) throws SQLException {

        // create connection:
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_Production",
                "postgres",
                "admin");

        // statement
        Statement stt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String query = "Select * from employees";
        ResultSet rs = stt.executeQuery(query);
//        rs.next();
//        System.out.println(rs.getString("first_name"));

  /*      ResultSetMetaData rsMetaData = rs.getMetaData();
        System.out.println(rsMetaData.getColumnCount());
        System.out.println(rsMetaData.getColumnName(1));
        System.out.println(rsMetaData.getTableName(1));
        System.out.println(rsMetaData.isNullable(8));

        for (int i = 1; i <= rsMetaData.getColumnCount(); i++){
            System.out.println("Column # " + i + " " + rsMetaData.getColumnName(i));
        }

   */
        Map<Integer,String> map = new HashMap<>();
        map.put(5, "Chicago");
        map.put(6, "Park Ridge");
        map.put(5, "Des Plaines");
        map.put(7, "Lincoln Park");
        System.out.println();

        Map<Integer,String> map1 = new HashMap<>();
        map1.put(5,"Mount Prospect");
        map1.put(6,"Schaumburg");
        map1.put(7, "Mundelin");

        Map<Integer,String> map2 = new HashMap<>();
        map2.put(5,"Libertville");
        map2.put(6,"Evanston");
        map2.put(7,"Skokie");

        List<String> list = new ArrayList<>();
        list.add("city");
        list.add("suburb");
        System.out.println(list);

        List<Map<Integer,String>> list1 = new ArrayList<>();
        list1.add(map);
        list1.add(map1);
        list1.add(map2);
        System.out.println(list1);

    }


}
