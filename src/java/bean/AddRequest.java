package bean;

import database.BranchDb;
import database.EmployeeDb;
import database.PriorityDb;
import database.RequestDb;
import model.*;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class AddRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<Priority> priorities = PriorityDb.getAllPriorities();
    private List<Branch> branches = BranchDb.getAllBranch();
    private List<Employee> employees = EmployeeDb.getAllEmployee();
    private Request request = new Request();

    public String addRequest(){
        RequestDb requestDb = new RequestDb();
        requestDb.addNewRequest(request);
        return "list";
    }

    public List<Priority> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<Priority> priorities) {
        this.priorities = priorities;
    }

    public List<Branch> getBranches() {
        return branches;
    }

    public void setBranches(List<Branch> branches) {
        this.branches = branches;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }
}
