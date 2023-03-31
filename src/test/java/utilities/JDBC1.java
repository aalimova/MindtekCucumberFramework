package utilities;

import io.cucumber.java.bs.A;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JDBC1 {

    public static void main(String[] args) throws SQLException {

        // create connection:
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost/HR_Production",
                "postgres",
                "admin");

        // statement
        Statement stt = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
      //  printCountries(stt);
        updateAllemails(stt);
        //printDepartmentsByCountries(stt);
    }

    private static void updateAllemails(Statement stt) throws SQLException {
        String emailQuery = "Select email from employees";
        ResultSet rs = stt.executeQuery(emailQuery);
//        rs.next();
//        System.out.println(rs.getString("email"));
        ArrayList<String> emailList = new ArrayList<>();
        while (rs.next()){
            emailList.add(rs.getString("email"));
        }
        ArrayList<String> newEmailList = new ArrayList<>();
       for(String email : emailList){
           email =email.substring(0,email.indexOf('@')) + "@mindtek.edu";
           newEmailList.add(email);
       }
        System.out.println(newEmailList);

       String updateQuery = "update employees set email = newemail where employees.email = oldEmail";
       int i = 0;
       for (String em : emailList){
           updateQuery = "update employees set email = " + newEmailList.get(i) + " where employees.email = " + emailList.get(i);
           System.out.println(updateQuery);
           i++;
       }
    }

    public static void printCountries(Statement stt ) throws SQLException {
        int count = 1;
                // read data
                ResultSet rs = stt.executeQuery("Select * from countries");
        while (rs.next()){
            System.out.println(count + " -> " + rs.getString("country_name"));
            count++;

        }
    }
    private static void printDepartmentsByCountries(Statement stt) throws SQLException {
        // add imp
        ResultSet rs = stt.executeQuery("select locations.country_id, count(departments.department_name) from locations, departments\n" +
                "where locations.location_id = departments.location_id\n" +
                "group by locations.country_id");

        while(rs.next()){
            System.out.println(rs.getString("country_id") + " -> " + rs.getString("count"));
        }
    }

}
