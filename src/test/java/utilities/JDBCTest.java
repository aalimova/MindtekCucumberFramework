package utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JDBCTest {

    public static void main(String[] args) throws SQLException {

        //create connection:
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_Production",
                "postgres",
                "admin");
        //statement
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

        //read data
  //      ResultSet resultSet = statement.executeQuery("Select * from jobs");
//        resultSet.next();
//        resultSet.next();
//
//        System.out.println(resultSet.getString("job_title"));

//        while (resultSet.next()) {
//            System.out.println(resultSet.getString("job_title"));
//            System.out.println(resultSet.getString("min_salary"));
//        }

//        ResultSet resultSet = statement.executeQuery("select concat(e.first_name, ' ', e.last_name) as p_full_name, concat(d.first_name, ' ', d.last_name) as d_full_name from employees e, dependents d\n" +
//                "where e.employee_id = d.employee_id");
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("p_full_name") + " is a parent of " + resultSet.getString("d_full_name"));
//        }

//        ResultSet resultSet = statement.executeQuery("Select email from employees");
//        System.out.println(resultSet.getString("email"));

        findDifferentEmail(connection,statement);
    }

    private static void findDifferentEmail(Connection con, Statement stt) throws SQLException {
        ResultSet resultSet = stt.executeQuery("Select email from employees");
        ArrayList<String> emailList = new ArrayList<>();
        while (resultSet.next()) emailList.add((resultSet.getString("email")));

        boolean isWrongEmail = false;
        for(String email:emailList) {
            if (email.endsWith("@sqltutorial.org")) {
                isWrongEmail = true;
            }
        }
            if(isWrongEmail){
                System.out.println("Found different email");
            }
            else {
                System.out.println("All emails are the same");
            }
//        while (resultSet.next()){
//            System.out.println(resultSet.getString("email"));
        }



}
