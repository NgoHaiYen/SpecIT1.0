package database;

import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EmployeeDb {
    private Connection conn;

    public EmployeeDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all employees of company
    public ArrayList<Employee> getAllEmployee() {
        ArrayList<Employee> employees = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from employees");

            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setEmail(rs.getString("email"));
                e.setName(rs.getString("name"));
                e.setUserName(rs.getString("username"));
                e.setPassWord(rs.getString("password"));
                e.setPhone(rs.getString("phone"));
                e.setRole(rs.getInt("role_id"));
                e.setSubteamId(rs.getInt("subteam_id"));

                employees.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // only get name and id of employees
    public ArrayList<Employee> getAllEmployeeNameAndId(){
        // TODO: get from db
        return null;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
