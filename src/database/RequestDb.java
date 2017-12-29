package database;

import model.Request;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

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
        // TODO
        return null;
    }

    // get all request of an employee by status and created_by
    // viec toi yeu cau (new, inprogress, resolved, feedback, closed, cancelled)
    public ArrayList<Request> getAllRequest(int employeId, int status){
        // TODO: get from db
        return null;
    }

    // get all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Request> getAllAssignRequest(int employeId, int status){
        // TODO: get from db
        return null;
    }

    // get all feedback of all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Request> getFeedBack(int employeId, int status){
        // TODO: get from db
        return null;
    }

    // get all request by itteam_id and status
    // cong viec cua bo phan it
    public ArrayList<Request> getAllTeamRequest(int teamId, int status){
        // TODO: get from db
        return null;
    }

    // get all request by subteam_id and status
    // cong viec cua team
    public ArrayList<Request> getAllSubteamRequest(int subteamId, int status){
        // TODO: get from db
        return null;
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
