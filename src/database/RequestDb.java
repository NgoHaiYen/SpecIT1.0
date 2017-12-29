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

    public static void main(String[] args) {
        RequestDb requestDb = new RequestDb();
        Integer i = requestDb.getNumberOfRequest(1, 1);
        System.out.println(i);
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

    // get number of request of an employee by status and created_by
    // so viec toi yeu cau (new, inprogress, resolved, feedback, closed, cancelled)
    public Integer getNumberOfRequest(int employeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where created_by = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                int i = rs.getInt(1);
                if (i > 0) return i;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
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

        return requests;


    }

    // get number of requests assign to an employee by assigned_to and status
    // so viec toi duoc giao
    public Integer getNumberOfAssignRequest(int employeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where assigned_to = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                int i = rs.getInt(1);
                if (i > 0) return i;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // get all feedback of all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Integer> getFeedBack(int employeId, int status){
        ArrayList<Integer> feedBackRatings = new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select rating from request where itteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            while (rs.next()){
                Integer int1 = rs.getInt("rating");
                feedBackRatings.add(int1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return feedBackRatings;
    }

    // get number of feedback of all requests assign to an employee by assigned_to and status
    // so luong phan hoi cua viec toi duoc giao
    public Integer getNumberOfFeedBack(int employeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where itteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                int i = rs.getInt(1);
                if (i > 0) return i;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

    // get number of requests by itteam_id and status
    // so cong viec cua bo phan it
    public Integer getNumberOfTeamRequest(int teamId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where itteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,teamId);
            statement.setInt(2,status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
               int i = rs.getInt(1);
                if (i > 0) return i;
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // get all requests by subteam_id and status
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

    // get number of requests by subteam_id and status
    // so cong viec cua team
    public Integer getNumberOfSubteamRequest(int subteamId, int status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where subteam_id = ? and status = ? ";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, subteamId);
            statement.setInt(2, status);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                int i = rs.getInt(1);
                if (i > 0) return i;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    // insert new request
    public void addNewRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "insert into request (request_id,subject," +
                    " content, created_by, status," +
                    " prioriry, deadlline, assigned_to," +
                    " rating, team_id, resolved_at," +
                    " closed_at, create_at, updated_at, deleted_at) VALUE " +
                    "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1,request.getId());
            statement.setString(2,request.getSubject());
            statement.setString(3,request.getContent());
            statement.setInt(4,request.getCreatedBy());
            statement.setInt(5,request.getStatus());
            statement.setInt(6,request.getPriority());
            statement.setDate(7,request.getDeadline());
            statement.setInt(8,request.getAssignedTo());
            statement.setInt(9,request.getRating());
            statement.setInt(10,request.getTeamId());
            statement.setDate(11,request.getResolvedAt());
            statement.setDate(12,request.getClosedAt());
            statement.setDate(13,request.getCreatedAt());
            statement.setDate(14,request.getUpdatedAt());
            statement.setDate(15,request.getDeletedAt());
            statement.executeQuery();

        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update request from db
    public void updateRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET subject = ?,content = ?," +
                    "created_by = ?, status = ?, prioriry = ?,deadlline =?,assigned_to=?,rating = ?," +
                    "team_id = ?,resolved_at =? ,closed_at =?, create_at = ?,updated_at=?,deleted_at = ? WHERE request.request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setString(1,request.getSubject());
            statement.setString(2,request.getContent());
            statement.setInt(3,request.getCreatedBy());
            statement.setInt(4,request.getStatus());
            statement.setInt(5,request.getPriority());
            statement.setDate(6,request.getDeadline());
            statement.setInt(7,request.getAssignedTo());
            statement.setInt(8,request.getRating());
            statement.setInt(9,request.getTeamId());
            statement.setDate(10,request.getResolvedAt());
            statement.setDate(11,request.getClosedAt());
            statement.setDate(12,request.getCreatedAt());
            statement.setDate(13,request.getUpdatedAt());
            statement.setDate(14,request.getDeletedAt());
            statement.setInt(15,request.getId());
            statement.executeQuery();

        }
        catch (SQLException e) {
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
