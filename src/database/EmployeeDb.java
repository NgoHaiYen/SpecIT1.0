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
                e.setItteamId(rs.getInt("itteam_id"));

                employees.add(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // get all employees of a team join with itteam table
    public ArrayList<Employee> getAllEmployee(int itteamId) {
        ArrayList<Employee> employeeInTeam = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlS = "select * from employees WHERE itteam_id =" + itteamId + ";";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlS);

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
                e.setItteamId(rs.getInt("itteam_id"));
                employeeInTeam.add(e);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeeInTeam;
    }

    public ArrayList<Employee> getAllEmployeeInSubteam(int subteamId) {
        ArrayList<Employee> employeeInSubTeam = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlS = "select * from employees WHERE subteam_id =" + subteamId + ";";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlS);

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
                e.setItteamId(rs.getInt("itteam_id"));
                employeeInSubTeam.add(e);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeeInSubTeam;

    }

    // only get name and id of employees
    public ArrayList<Employee> getAllEmployeeNameAndId(){
        ArrayList<Employee> employeesNameID = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select employee_id,name from employees");

            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setName(rs.getString("name"));
                employeesNameID.add(e);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeesNameID;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
