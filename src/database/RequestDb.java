package database;

import model.Request;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Statement;

public class RequestDb {
    private Connection conn;

    public RequestDb() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DbConnection.getConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // get all request of company
    public ArrayList<Request> getAllRequest() {
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Statement statement = conn.createStatement();

            ResultSet rs = statement.executeQuery( "select * from request;");
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getDate("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getDate("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getDate("update_at"));
                r.setResolvedAt(rs.getDate("resolved_at"));
                r.setDeletedAt(rs.getDate("deleted_at"));
                requests.add(r);
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // TODO Exception control
        return requests;

    }

    // get all request of an employee by status and created_by
    // viec toi yeu cau (new, inprogress, resolved, feedback, closed, cancelled)
    public ArrayList<Request> getAllRequest(int employeId, int status){
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where created_by = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getDate("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getDate("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getDate("update_at"));
                r.setResolvedAt(rs.getDate("resolved_at"));
                r.setDeletedAt(rs.getDate("deleted_at"));
                requests.add(r);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return requests;
    }

    // get all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Request> getAllAssignRequest(int employeId, int status){
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where assigned_to = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getDate("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getDate("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getDate("update_at"));
                r.setResolvedAt(rs.getDate("resolved_at"));
                r.setDeletedAt(rs.getDate("deleted_at"));
                requests.add(r);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // TODO: get from db
        return requests;


    }

    // get all feedback of all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Request> getFeedBack(int employeId, int status){
        // TODO: get from db
        // :??? really ?
        return null;
    }

    // get all request by itteam_id and status
    // cong viec cua bo phan it
    public ArrayList<Request> getAllTeamRequest(int teamId, int status){
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where itteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,teamId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getDate("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getDate("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getDate("update_at"));
                r.setResolvedAt(rs.getDate("resolved_at"));
                r.setDeletedAt(rs.getDate("deleted_at"));
                requests.add(r);
            }

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return requests;


    }

    // get all request by subteam_id and status
    // cong viec cua team
    public ArrayList<Request> getAllSubteamRequest(int subteamId, int status) {
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where subteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, subteamId);
            statement.setInt(2, status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getDate("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getDate("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("teamID"));
                r.setUpdatedAt(rs.getDate("update_at"));
                r.setResolvedAt(rs.getDate("resolved_at"));
                r.setDeletedAt(rs.getDate("deleted_at"));
                requests.add(r);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return requests;
    }

    // insert new request
    public void addNewRequest(Request request){
        // TODO
    }

    // update request from db
    public void updateRequest(Request request){
        // TODO
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
