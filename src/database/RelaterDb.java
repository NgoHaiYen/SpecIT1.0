package database;

import model.Comment;
import model.Priority;
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
            ResultSet rs = statement.executeQuery("select * from relater WHERE relater_id =" +requestId + ";");
            while(rs.next()){
               Relater r = new Relater();
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

    // get number of request that relate to the employee, join employee with relate table
    // if number = 0, return null
    public Integer getNumberOfRequestRelate(int employeeId, int status){
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery("select * from relater JOIN request " +
                    "ON relater.request_id = request.request_id " +
                    "WHERE relater_id =" + employeeId + " AND request.status = " + status + ";");

            while(rs.next()){
                count ++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if ( count ==0) return null;
        else  return count;
    }

    // insert a list of relater to a request
    public void addRelaters(ArrayList<Integer> relatersId, int requestId){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            for (int i = 0; i < relatersId.size(); i++) {
                String sqlS = "INSERT INTO relater(request_id, relater_id, created_at) VALUE (?,?,CURRENT_TIMESTAMP);";
                PreparedStatement statement = conn.prepareStatement(sqlS);
                statement.setInt(1,requestId);
                statement.setInt(2, relatersId.get(i));
                statement.executeQuery(sqlS);
            }


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

            String sqlS = "INSERT INTO relater(request_id, relater_id, created_at) VALUE (?,?,CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(1,requestId);
            statement.setInt(2,relaterId);
            statement.executeQuery(sqlS);

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

            String sqlS = "DELETE FROM relater WHERE relater_id = ? AND  request_id = ? ;";
            PreparedStatement statement = conn.prepareStatement(sqlS);
            statement.setInt(2,requestId);
            statement.setInt(1,relaterId);
            statement.executeQuery(sqlS);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    // update relater of a request
    public void updateRelater(ArrayList<Integer> relatersId, int requestId){
        // TODO: 12 : Cần sử dụng trường id trong relator , aborted
        //
        // insert all new relaters
        // delete all relaters that are not contained in the given list
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
