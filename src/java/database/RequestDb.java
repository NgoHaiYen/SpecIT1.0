package database;

import helper.HibernateUtil;
import helper.Mail;
import model.Priority;
import model.Request;
import model.Role;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RequestDb {
    public static void main(String[] args) {
        RequestDb requestDb = new RequestDb();
        Request request = requestDb.getRequestById(29);
        System.out.println(request.getSubject());
    }

    // get request by its id
    public Request getRequestById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Request request = (Request) session.get(Request.class, id);
            tx.commit();
            return request;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // get branch by request id
    private String getLeaderBranchMail(int id){
        try {
            return getRequestById(id).getBranch().getLeader().getEmail();
        } catch (NullPointerException ignored){
            return null;
        }
    }

    // get request name by id
    public String getName(int id){
        try {
            return getRequestById(id).getSubject();
        } catch (NullPointerException ignored) {
            return null;
        }
    }

    // insert new request, relaters is id of the relaterss
    public Integer addNewRequest(Request request){
        //todo remember to mail

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(request);
            tx.commit();
            return request.getId();  // get the id of the recent inserted id
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // update request from db
    public void updateRequest(Request request){
        // todo mail
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(request);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // update request priority
    public void updateRequestPriority(int requestId, Priority priority){
        // todo, mail

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Request request = (Request) session.get(Request.class, requestId);
            request.setPriority(priority);
            session.update(request);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
    
    private void deleteIsread(int requestId){
        // todo mail
    }

    // update request branch
    public void updateRequestBranch(int requestId, int branchId){
        deleteIsread(requestId);
        
        // todo mail
    }

    private String getAssignedEmail(int requestId) {
        // todo
        return null;
    }

    // update request deadline
    public void updateRequestDeadline(int requestId, String deadline){
        deleteIsread(requestId);

        // todo mail

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

        // todo mail

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

        // todo mail
    }
}
