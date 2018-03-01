package database;

import helper.HibernateUtil;
import model.Role;
import model.Team;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class TeamDb {
    // get all team
    public ArrayList<Team> getAllTeams() {
        ArrayList<Team> teams = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List results = session.createCriteria(Role.class).list();
            for (Object result : results) {
                Team team = (Team) result;
                teams.add(team);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return teams;
    }
}
