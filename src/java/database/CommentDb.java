package database;

import helper.HibernateUtil;
import model.Comment;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CommentDb {
    // add new comment
    public Integer addComment(Comment comment){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(comment);
            tx.commit();
            return comment.getId();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // update comment
    public void updateComment(Comment comment){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Comment c = (Comment) session.get(Comment.class, comment.getId());
            c.setContent(comment.getContent());
            c.setType(comment.getType());
            c.setNote(comment.getNote());

            session.save(c);
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
