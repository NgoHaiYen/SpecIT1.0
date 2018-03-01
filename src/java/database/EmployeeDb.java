package database;

import helper.HibernateUtil;
import model.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDb {
    public static void main(String[] args) {
        EmployeeDb employeeDb = new EmployeeDb();
        Employee e = employeeDb.getEmployeeById(1);
        System.out.println(e.getAssigned().get(0).getImage().getUrl());
    }

    public Employee checkLogin(String userName, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("from Employee where userName = :name and passWord = md5(:pass)");
            query.setParameter("name", userName);
            query.setParameter("pass", password);
            List result = query.list();

            if(result.size() > 0){
                return (Employee) result.get(0);
            }
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // get all employee
    public ArrayList<Employee> getAllEmployee(){
        ArrayList<Employee> employees = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List results = session.createCriteria(Employee.class).list();

            for (Object result : results){
                Employee employee = (Employee) result;
                employees.add(employee);
            }
            tx.commit();
        }
        catch (HibernateException e) {
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return employees;
    }

    public Employee getEmployeeById(int id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Employee e = (Employee) session.get(Employee.class, id);
            tx.commit();
            return e;
        } catch (HibernateException e){
            if (tx != null) tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    // get employee name by id
    public String getNameById(int id){
        try {
            return getEmployeeById(id).getName();
        } catch (NullPointerException e){
            return null;
        }
    }

    // get employee email by id
    String getEmail(int id){
        try {
            return getEmployeeById(id).getEmail();
        } catch(NullPointerException e){
            return null;
        }
    }
}
