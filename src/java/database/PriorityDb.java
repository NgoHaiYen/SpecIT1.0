package database;

import helper.HibernateUtil;
import model.Priority;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PriorityDb {
    private static ArrayList<Priority> priorities = null;

    public static ArrayList<Priority> getAllPriorities(){
        if (priorities == null) {
            priorities = new ArrayList<>();

            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List results = session.createCriteria(Priority.class).list();
                for (Object result : results) {
                    Priority priority = (Priority) result;
                    priorities.add(priority);
                }
                tx.commit();
            } catch (HibernateException e) {
                if (tx != null) {
                    tx.rollback();
                }
                e.printStackTrace();
            } finally {
                session.close();
            }
        }
        return priorities;
    }

    public String getPriorityNameById(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Priority p = (Priority) session.get(Priority.class, id);
            tx.commit();
            return p.getName();
        } catch (HibernateException e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
