package database;

import model.Branch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BranchDb {
    private Connection conn;

    public BranchDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all branch
    public ArrayList<Branch> getAllBranch() {
        ArrayList<Branch> teams = new ArrayList<Branch>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM branch;");
            while(rs.next()){
                Branch team = new Branch();
                team.setId(rs.getInt("branch_id"));
                team.setName(rs.getString("name"));
                team.setLeaderId(rs.getInt("leader_id"));
                teams.add(team);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    // get all branchs name
    public ArrayList<String> getAllBranchName() {
        ArrayList<String> teamNames = new ArrayList<String>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT branch_name FROM branch;");
            while(rs.next()){
                String s = rs.getString("branch_name");

                teamNames.add(s);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teamNames;
    }


    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
