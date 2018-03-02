package bean;

import database.EmployeeDb;
import helper.SessionUtil;
import model.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private Employee currentUser = (new EmployeeDb()).getEmployeeById(SessionUtil.getUserId());

    public Employee getCurrentUser() {
        System.out.println(currentUser.getAssigned().size());
        return currentUser;
    }
}
