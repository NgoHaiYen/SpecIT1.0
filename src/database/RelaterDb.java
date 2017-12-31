package database;

import model.Relater;

import java.sql.*;
import java.util.ArrayList;

public class RelaterDb {
    private Connection conn;

    public RelaterDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all relater of a request by request_id
    public ArrayList<Relater> getAllRelater(int requestId) {
        ArrayList<Relater> relaters = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select * from relater WHERE request_id =" +requestId + ";");
            while(rs.next()){
               Relater r = new Relater();
               r.setRelationId(rs.getInt("relation_id"));
               r.setEmployeeId(rs.getInt("employee_id"));
               r.setRequestId(rs.getInt("request_id"));
               r.setCreatedAt(rs.getString("created_at"));
               relaters.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return relaters;
    }

    // insert a list of relater to a request
    public void addRelaters(ArrayList<Integer> relatersId, int requestId){
        for (int i = 0; i < relatersId.size(); i++) {
            addRelater(relatersId.get(i), requestId);
        }
    }

    public void addRelaters(ArrayList<Integer> relatersId){
        for (int i = 0; i < relatersId.size(); i++) {
            addRelater(relatersId.get(i));
        }
    }

    public void addRelater(int relaterId){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO relater(request_id, employee_id, created_at) VALUE (last_insert_id(),?,CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,relaterId);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // insert a relater to a request
    public void addRelater(int relaterId, int requestId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "INSERT INTO relater(request_id, employee_id, created_at) VALUE (?,?,CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,requestId);
            statement.setInt(2,relaterId);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // delete a relater to a request
    private void deleteRelater(int relaterId, int requestId){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String sqlS = "DELETE FROM relater WHERE employee_id = ? AND  request_id = ? ;";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(2,requestId);
            statement.setInt(1,relaterId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update relater of a request
    // delete all relation to a req_id
    // insert new ones to db
    public void updateRelater(ArrayList<Integer> relatersId, int requestId){
        try {
                Class.forName("com.mysql.jdbc.Driver");
                String sqlS = "DELETE FROM relater WHERE request_id = ? ;";
                PreparedStatement statement = conn.prepareStatement(sqlS);
                statement.setInt(1,requestId);
                statement.execute();

                addRelaters(relatersId,requestId);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
