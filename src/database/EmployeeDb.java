package database;

import model.Employee;

import java.sql.*;
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
                e.setName(rs.getString("name"));
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));

                e.setRole(rs.getInt("role_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setBranchId(rs.getInt("branch_id"));

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
    public ArrayList<Employee> getAllEmployee(int branchID) {
        ArrayList<Employee> employeeInTeam = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlS = "select * from employees WHERE Branch_id_id =" + branchID + ";";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlS);

            while(rs.next()){
                Employee e = new Employee();
                e.setName(rs.getString("name"));
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));

                e.setUserName(rs.getString("username"));
                e.setPassWord(rs.getString("password"));

                e.setRole(rs.getInt("role_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setBranchId(rs.getInt("branch_id"));

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

    public ArrayList<Employee> getAllEmployeeInTeam(int teamId) {
        ArrayList<Employee> employeeInSubTeam = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String sqlS = "select * from employees WHERE team_id =" + teamId + ";";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sqlS);

            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));


                e.setName(rs.getString("name"));
                e.setPhone(rs.getString("phone"));
                e.setEmail(rs.getString("email"));

                e.setUserName(rs.getString("username"));
                e.setPassWord(rs.getString("password"));

                e.setRole(rs.getInt("role_id"));
                e.setTeamId(rs.getInt("team_id"));
                e.setBranchId(rs.getInt("branch_id"));

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
        ArrayList<Employee> employeesNameIDs = new ArrayList<Employee>();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select employee_id,name from employees");

            while(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setName(rs.getString("name"));
                employeesNameIDs.add(e);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return employeesNameIDs;
    }

    // get employee name by id
    public String getNameById(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select name from employees where employee_id = ?";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("name");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
