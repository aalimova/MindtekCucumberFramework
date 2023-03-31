package utilities;

import io.cucumber.java.bs.A;
import io.cucumber.java.it.Ma;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DBTest {
    public static void main(String[] args) throws SQLException {

        DB db = new DB();


        ArrayList<String> columnNames = new ArrayList<>();
        columnNames.add("first_name");
        columnNames.add("last_name");
        columnNames.add("phone_number");

//      String query = "insert into countries values ('NK', 'North Korea', 3)";
  //    db.runInsertQuery(query);


      ResultSet rs = db.runSelectQuery(columnNames, "employees");

      while (rs.next()){
          System.out.println(rs.getString("first_name") + " " + rs.getString("phone_number"));
      }
        db.close();

//      while (rs.next()){
//          System.out.println(rs.getString("first_name") + " " + rs.getString("last_name"));
//      }

  //    List<Map<String,Object>> list = db.getTableForQuery(query);
     //   System.out.println(list);
    }
}
