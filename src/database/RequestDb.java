package database;

import model.Request;
import utils.Constant;
import utils.Mail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    // get request by its id
    public Request getRequestById(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where request_id = ?";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()){
                Request r = new Request();
                r.setId(rs.getInt("request_id"));
                r.setAssignedTo(rs.getInt("assigned_to"));
                r.setClosedAt(rs.getString("closed_at"));
                r.setCreatedBy(rs.getInt("created_by"));
                r.setContent(rs.getString("content"));
                r.setDeadline(rs.getString("deadline"));
                r.setPriority(rs.getInt("priority"));
                Integer stfytf = rs.getInt("rating");
                r.setRating(rs.getInt("rating")==0?null:rs.getInt("rating"));
                r.setStatus(rs.getInt("status"));
                r.setSubject(rs.getString("subject"));
                r.setTeamId(rs.getInt("team_id"));
                r.setUpdatedAt(rs.getString("updated_at"));
                r.setResolvedAt(rs.getString("resolved_at"));
                r.setDeletedAt(rs.getString("deleted_at"));
                r.setCreatedAt(rs.getString("create_at"));
                return r;
            }

        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // get all request of an employee by status and created_by
    // viec toi yeu cau (new, in progress, resolved, feedback, closed, cancelled)
    public ArrayList<Request> getAllRequest(int employeeId, int status){
        ArrayList<Request> requests = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where created_by = ?";
            if (status != 0){
                s += " and status = ? ";
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
                r.setTeamId(rs.getInt("team_id"));
                r.setBranchId(rs.getInt("branch_id"));
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
    // so viec toi yeu cau (new, in progress, resolved, feedback, closed, cancelled)
    public Integer getNumberOfRequest(int employeeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where created_by = ?";
            if (status != 0){
                s += " and status = ? ";
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

    // get all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Request> getAllAssignRequest(int employeeId, int status){
        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where assigned_to = ?";
            if (status != 0){
                s += " and status = ? ";
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
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return requests;


    }

    // get number of requests assign to an employee by assigned_to and status
    // so viec toi duoc giao
    public Integer getNumberOfAssignRequest(int employeeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where assigned_to = ?";
            if (status != 0){
                s += " and status = ? ";
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

    // get all feedback of all requests assign to an employee by assigned_to and status
    // viec toi duoc giao
    public ArrayList<Integer> getFeedBack(int employeeId, int status){
        ArrayList<Integer> feedBackRatings = new ArrayList<Integer>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select rating from request where itteam_id = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,employeeId);
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
    public Integer getNumberOfFeedBack(int employeeId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where itteam_id = ?";
            if (status != 0){
                s += " and status = ? ";
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

    // get all request by team_id_ and status
    // cong viec cua team
    public ArrayList<Request> getAllTeamRequest(int teamId, int status){

        ArrayList<Request> requests = new ArrayList<Request>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request " +
                    " where team_id ?";
            if (status != 0){
                s += " and request.status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,teamId);
            if (status != 0){
                statement.setInt(2,status);
            }
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Request r2 = new Request();
                r2.setId(rs.getInt("request_id"));
                r2.setAssignedTo(rs.getInt("assigned_to"));
                r2.setClosedAt(rs.getString("closed_at"));
                r2.setCreatedBy(rs.getInt("created_by"));
                r2.setContent(rs.getString("content"));
                r2.setDeadline(rs.getString("deadline"));
                r2.setPriority(rs.getInt("priority"));
                r2.setRating(rs.getInt("rating"));
                r2.setStatus(rs.getInt("status"));
                r2.setSubject(rs.getString("subject"));
                r2.setTeamId(rs.getInt("team_id"));
                r2.setBranchId(rs.getInt("branch_id"));
                r2.setUpdatedAt(rs.getString("updated_at"));
                r2.setResolvedAt(rs.getString("resolved_at"));
                r2.setDeletedAt(rs.getString("deleted_at"));
                requests.add(r2);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return requests;
    }

    // get number of requests by team_id and status
    // so cong viec cua team
    public Integer getNumberOfTeamRequest(int teamId, int status) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request where team_id = ? and status = ? ;";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, teamId);
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

    // get all requests by branch_id and status
    // cong viec cua chi nhanh
    public ArrayList<Request> getAllBranchRequest(int branchId, int status) {

        ArrayList<Request> requests = new ArrayList<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select * from request where branch_id = ?";
            if (status != 0){
                s += " and status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,branchId);
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
                r.setTeamId(rs.getInt("team_id"));
                r.setBranchId(rs.getInt("branch_id"));
                r.setUpdatedAt(rs.getString("updated_at"));
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

    // get number of requests by branch_id and status
    // so cong viec cua bo phan it
    public Integer getNumberOfBranchRequest(int branchId, int status){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select count(*) from request" +
                    " where branch_id = ?";
            if (status != 0){
                s += " and request.status = ? ";
            }
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1,branchId);
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


    // get all request relate to an employee
    public ArrayList<Request> getAllRelateRequest(int employeeId, int status){
        ArrayList<Request> requests =  new ArrayList<>();
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
                r.setTeamId(rs.getInt("team_id"));
                r.setBranchId(rs.getInt("branch_id"));
                r.setUpdatedAt(rs.getString("updated_at"));
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // get branch by request id
    private String getLeaderBranchMail(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select email from request join branch on request.branch_id = branch.branch_id " +
                    " join employees on branch.leader_id = employees.employee_id" +
                    " where request_id = ?";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("email");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // get request name by id
    public String getName(int id){
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select subject from request where request_id = ?";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("subject");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // insert new request
    public void addNewRequest(Request request, String[] relater, String file){
        addRequest(request);

        int id = getLastInsertIdRequest(request);

        addRelaters(relater, id);

        addImage(file, getLastInsertIdRequest(request));

        // send mail to current branch
        Mail mail = new Mail();
        String body = "Yêu cầu mới đã được chuyển đến bộ phận của bạn";
        String previousBranchLeaderMail = getLeaderBranchMail(id);
        mail.sendMail(previousBranchLeaderMail, body, "Thêm yêu cầu");
    }

    private void addImage(String file, Integer requestId) {
        if (file != null){
            ImageDb idb = new ImageDb();
            idb.addImage(requestId, file);
        }
    }

    private void addRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "insert into request (subject, content, created_by, status," +
                    " priority, deadline, team_id, create_at,branch_id) VALUE " +
                    "(?, ?, ?, 1, ?, ?, ?, CURRENT_TIMESTAMP,?);";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setString(1,request.getSubject());
            statement.setString(2,request.getContent());
            statement.setInt(3,request.getCreatedBy());
            statement.setInt(4,request.getPriority());
            statement.setString(5,request.getDeadline());
            statement.setInt(6,request.getTeamId());
            statement.setInt(7,request.getBranchId());
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
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
    private Integer getLastInsertIdRequest(Request request){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String s = "select max(last_insert_id(request_id)) from request" +
                    " where subject = ? and content = ? and created_by = ? " +
                    " and team_id = ?";
            PreparedStatement statement = conn.prepareStatement(s);
            statement.setString(1, request.getSubject());
            statement.setString(2, request.getContent());
            statement.setInt(3, request.getCreatedBy());
            statement.setInt(4, request.getTeamId());

            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update request from db
    public void updateRequest(Request request){
        deleteIsread(request.getId());
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
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // update request priority
    public void updateRequestPriority(int requestId, int priorityId){
        deleteIsread(requestId);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET priority = ?, updated_at = CURRENT_TIMESTAMP " +
                    "WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1, priorityId);
            statement.setInt(2, requestId);
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    private void deleteIsread(int requestId){
        IsReadDb isReadDb = new IsReadDb();
        isReadDb.renew(requestId);
        isReadDb.closeConnection();
    }

    // update request branch
    public void updateRequestBranch(int requestId, int branchId){
        deleteIsread(requestId);
        
        // send mail to current branch
        
        String previousBranchLeaderMail = getLeaderBranchMail(requestId);
        Mail mail = new Mail();
        String body = "Yêu cầu " + getName(requestId) + " của bộ phận của bạn đã được chuyển tới bộ phận IT khác";
        if (previousBranchLeaderMail != null){
            mail.sendMail(previousBranchLeaderMail, body, "Thay đổi bộ phận IT");
        }
        
        if (getAssignedEmail(requestId) != null){
            // send mail assigned employee
            mail.sendMail(getAssignedEmail(requestId), body, "Thay đổi bộ phận IT");
        }
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET branch_id = ?, team_id = null, assigned_to = null," +
                    " updated_at = CURRENT_TIMESTAMP WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1, branchId);
            statement.setInt(2, requestId);
            statement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        BranchDb bdb = new BranchDb();
        if (bdb.getLeaderEmail(branchId) != null){
            body = "Yêu cầu mới đã được chuyển tới bộ phận của bạn";
            mail.sendMail(bdb.getLeaderEmail(branchId), body, "Thay đổi bộ phận IT");
        }
        bdb.closeConnection();
    }

    private String getAssignedEmail(int requestId) {
        try {
            Class.forName("com.mysql.jdbc.Driver");

            String s = "select email from request join employees on request.assigned_to = employees.employee_id" +
                    " where request_id = ?";

            PreparedStatement statement = conn.prepareStatement(s);
            statement.setInt(1, requestId);
            ResultSet rs = statement.executeQuery();

            if(rs.next()){
                return rs.getString("email");
            }
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    // update request deadline
    public void updateRequestDeadline(int requestId, String deadline){
        deleteIsread(requestId);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET deadline = ?, updated_at = CURRENT_TIMESTAMP WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setString(1, Constant.formatDateToSqlFromView(deadline));
            statement.setInt(2, requestId);
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // send mail to assigned employee

        String assignedMail = getAssignedEmail(requestId);
        if (assignedMail != null){
            Mail mail = new Mail();
            String body = "Yêu cầu " + getName(requestId) + " của bạn đã được thay đổi deadline tới ngày: " + deadline;
            mail.sendMail(assignedMail, body, "Thay đổi deadline");
        }
    }

    // update request assign
    public void updateRequestAssign(int requestId, int assignId){
        // send mail to the assigned employee
        String assignedMail = getAssignedEmail(requestId);
        if (assignedMail != null){
            Mail mail = new Mail();
            String body = "Yêu cầu " + getName(requestId) + " của bạn đã được chuyển tới người khác";
            mail.sendMail(assignedMail, body, "Thay đổi người thực hiện");
        }

        deleteIsread(requestId);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET assigned_to = ?, updated_at = CURRENT_TIMESTAMP " +
                    "WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1, assignId);
            statement.setInt(2, requestId);
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // send mail to the new assigned employee
        assignedMail = getAssignedEmail(requestId);
        if (assignedMail != null){
            Mail mail = new Mail();
            String body = "Yêu cầu mới đã được chuyển tới bạn";
            mail.sendMail(assignedMail, body, "Thay đổi người thực hiện");
        }
    }

    // update request status
    public void updateRequestStatus(int requestId, int status){

        deleteIsread(requestId);

        // send mail to the assigned employee
        String assignedMail = getAssignedEmail(requestId);
        if (assignedMail != null){
            Mail mail = new Mail();
            String body = "Yêu cầu " + getName(requestId) + " của bạn đã được thay đổi trạng thái";
            mail.sendMail(assignedMail, body, "Thay đổi trạng thái");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
            String addSql = "UPDATE request SET status = ?, updated_at = CURRENT_TIMESTAMP " +
                    "WHERE request_id = ?";
            PreparedStatement statement = conn.prepareStatement(addSql);
            statement.setInt(1, status);
            statement.setInt(2, requestId);
            statement.execute();
        }
        catch (SQLException | ClassNotFoundException e) {
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
