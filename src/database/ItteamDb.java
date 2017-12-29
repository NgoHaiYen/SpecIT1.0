package database;

import model.Employee;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItteamDb {
    private Connection conn;

    public ItteamDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all itteam
    public ArrayList<ItteamDb> getAllItteams() {
        // TODO
        return null;
    }

    // get all itteams name
    public ArrayList<String> getAllItteamsName() {
        // TODO
        return null;
    }

    // find itteam_id
    public int getItteamIdByName(String name){
        //TODO
        return 0;
    }

    // find itteam_name
    public String getItteamNameById(int id){
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
