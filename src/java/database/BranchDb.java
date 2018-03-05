package database;

import helper.HibernateUtil;
import model.Branch;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BranchDb {
    private static List<Branch> branches = null;

    // get all branch
    public static List<Branch> getAllBranch() {
        if (branches == null) {
            branches = new ArrayList<>();
            Session session = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                List results = session.createCriteria(Branch.class).list();
                for (Object result : results) {
                    Branch branch = (Branch) result;
                    branches.add(branch);
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
        return branches;
    }

    public String getBranchNameById(int branchId) {
        try {
            return getBranchById(branchId).getName();
        } catch (NullPointerException e){
            return null;
        }
    }

    private Branch getBranchById(int branchId){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Branch branch = (Branch) session.get(Branch.class, branchId);
            tx.commit();
            return branch;
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // get email of the leader of this branch by branch
    String getLeaderEmail(int id){
        try {
            return getBranchById(id).getLeader().getEmail();
        } catch (NullPointerException e){
            return null;
        }
    }
}
