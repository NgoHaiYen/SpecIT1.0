package database;

import model.Employee;
import model.Priority;

import java.sql.*;
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
        ArrayList<Priority> priorities = new ArrayList<Priority>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM priorities;");
            while(rs.next()){
                Priority p = new Priority();
                p.setId(rs.getInt("priority_id"));
                p.setName(rs.getString("name"));
                priorities.add(p);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorities;
    }

    // get all priority name
    public ArrayList<String> getAllPrioritiesName() {
        ArrayList<String> prioritiesNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT name FROM priorities;");
            while(rs.next()){
                String s = rs.getString("name");

                prioritiesNames.add(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return prioritiesNames;
    }

    // find priority_id
    public int getPriorityIdByName(String name){
        int idByName = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            String sql = "SELECT priority_id FROM priorities WHERE name = " + name + ";";
            ResultSet rs = statement.executeQuery(sql);
            idByName = rs.getInt("priority_id");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idByName;
    }

    // find priority_name
    public String getPriorityNameById(int id){
        String priorityById = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            String sql = "SELECT name FROM priorities WHERE priority_id = " + id + ";";
            ResultSet rs = statement.executeQuery(sql);
            priorityById = rs.getString("name");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return priorityById;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
