package database;

import model.Request;
import utils.Constant;

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

    // get all request of an employee by status and created_by
    // viec toi yeu cau (new, inprogress, resolved, feedback, closed, cancelled)
    public ArrayList<Request> getAllRequest(int employeId, int status){
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where created_by = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getString("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getString("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("subteam_id"));
                r.setUpdatedAt(rs.getString("updated_at"));
                r.setResolvedAt(rs.getString("resolved_at"));
                r.setDeletedAt(rs.getString("deleted_at"));
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
            String s = "select count(*) from request where created_by = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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
            String s = "select * from request where assigned_to = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getString("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getString("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getString("update_at"));
                r.setResolvedAt(rs.getString("resolved_at"));
                r.setDeletedAt(rs.getString("deleted_at"));
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
            String s = "select count(*) from request where assigned_to = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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
            String s = "select rating from request where itteam_id = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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
            String s = "select count(*) from request where itteam_id = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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

    // get all request by employee_ and status
    // cong viec cua bo phan it
    public ArrayList<Request> getAllTeamRequest(int employeeId, int status){
        // todo teamid -> employeeid
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request join subteam on request.subteam_id = subteam.subteam_id " +
                    " join itteam on subteam.itteam_id = itteam.itteam_id " +
                    " join employee on request.created_by = employee.employee_id" +
                    " where itteam.itteam_id = ?";
            if (status != 0){
                s += " and request.status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeeId);
            if (status != 0){
                statement.setInt(2,status);
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getString("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getString("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("itteam_id"));
                r.setUpdatedAt(rs.getString("update_at"));
                r.setResolvedAt(rs.getString("resolved_at"));
                r.setDeletedAt(rs.getString("deleted_at"));
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
    public Integer getNumberOfTeamRequest(int employeeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request join subteam on request.subteam_id = subteam.subteam_id " +
                    " join itteam on subteam.itteam_id = itteam.itteam_id" +
                    " where itteam.itteam_id = ?";
            if (status != 0){
                s += " and request.status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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
    public ArrayList<Request> getAllSubteamRequest(int employeeId, int status) {
        // todo: subteamid -> employeeid
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where subteam_id = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeeId);
            if (status != 0){
                statement.setInt(2,status);
            }
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getString("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getString("deadline"));
                r.setPriority(rs.getInt("priority"));
                r.setRating(rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("subteam_id"));
                r.setUpdatedAt(rs.getString("update_at"));
                r.setResolvedAt(rs.getString("resolved_at"));
                r.setDeletedAt(rs.getString("deleted_at"));
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

    // get all request relate to an employee
    public ArrayList<Request> getAllRelateRequest(int employeeId, int status){
        // todo
        return null;
    }

    // get number of request that relate to the employee, join employee with relate table
    // if number = 0, return null
    public Integer getNumberOfRequestRelate(int employeeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request join relater on request.request_id = relater.employee_id " +
                    " where relater.employee_id = ?";
            if (status != 0){
                s += " and request.status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeeId);
            if (status != 0){
                statement.setInt(2,status);
            }
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

    // insert new request
    public void addNewRequest(Request request, String[] relater){
        addRequest(request);

        addRelaters(relater, getLastInsertIdRequest(request));
    }

    private void addRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "insert into request (subject, content, created_by, status," +
                    " priority, deadlline, subteam_id, create_at) VALUE " +
                    "(?, ?, ?, 1, ?, ?, ?, CURRENT_TIMESTAMP);";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setString(1,request.getSubject());
            statement.setString(2,request.getContent());
            statement.setInt(3,request.getCreatedBy());
            statement.setInt(4,request.getPriority());
            statement.setString(5,request.getDeadline());
            statement.setInt(6,request.getTeamId());
            statement.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void addRelaters(String[] relater, int id) {
        ArrayList<Integer> relatersId = new ArrayList<>();
        for (String rl:relater){
            relatersId.add(Integer.parseInt(rl));
        }
        RelaterDb relaterDb = new RelaterDb();
        relaterDb.addRelaters(relatersId, id);
    }

    // get last insert request_id
    public Integer getLastInsertIdRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select max(last_insert_id(request_id)) from request" +
                    " where subject = ? and content = ? and created_by = ? " +
                    " and subteam_id = ?";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setString(1, request.getSubject());
            statement.setString(2, request.getContent());
            statement.setInt(3, request.getCreatedBy());
            statement.setInt(4, request.getTeamId());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update request from db
    public void updateRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET status = ?, priority = ?, " +
                    "deadlline = ?, assigned_to = ?, " +
                    "team_id = ?, updated_at = CURRENT_TIMESTAMP " +
                    "WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1,request.getStatus());
            statement.setInt(2,request.getPriority());
            statement.setString(3,request.getDeadline());
            statement.setInt(4,request.getAssignedTo());
            statement.setInt(5,request.getTeamId());
            statement.setInt(6,request.getId());
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
