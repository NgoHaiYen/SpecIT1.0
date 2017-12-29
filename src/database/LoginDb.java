package database;

import model.Employee;
import model.IsRead;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginDb {
    private Connection conn;

    public LoginDb() {
        conn = DbConnection.getConnection();
    }

    public Employee checkLogin(String userName, String password) {
        try {
            String s = "select count(*) from employees where username = ? and password = md5(?)";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setString(1, userName);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                Employee e = new Employee();
                e.setId(rs.getInt("employee_id"));
                e.setRole(rs.getInt("role_id"));
                return e;
            }
        } catch (SQLException e) {
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
