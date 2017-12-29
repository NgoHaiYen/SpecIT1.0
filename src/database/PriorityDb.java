package database;

import model.Employee;
import model.Priority;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PriorityDb {
    private Connection conn;

    public PriorityDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all priority
    public ArrayList<Priority> getAllPriorities() {
        // TODO
        return null;
    }

    // get all priority name
    public ArrayList<String> getAllPrioritiesName() {
        // TODO
        return null;
    }

    // find priority_id
    public int getPriorityIdByName(String name){
        //TODO
        return 0;
    }

    // find priority_name
    public String getPriorityNameById(int id){
        //TODO
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
