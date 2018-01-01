package database;

import model.Team;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class TeamDb {
    private Connection conn;

    public TeamDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all team
    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM team;");
            while(rs.next()){
                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                team.setName(rs.getString("team_name"));
                teams.add(team);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    // get all team of a branch
    public ArrayList<Team> getAllTeams(int branchId) {
        ArrayList<Team> teams = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM team where branch_id = " + branchId);
            while(rs.next()){
                Team team = new Team();
                team.setId(rs.getInt("team_id"));
                team.setName(rs.getString("team_name"));
                teams.add(team);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
