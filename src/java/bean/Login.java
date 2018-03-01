package bean;

import database.EmployeeDb;
import helper.SessionUtil;
import model.Employee;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class Login implements Serializable {
    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;
    private String error;

    public String getError() {
        return error;
    }

    public String login(){
        if (userName == null || userName.isEmpty()){
            error = "Please enter your username";
        } else if (password == null || password.isEmpty()){
            error = "Please enter your password";
        } else {
            Employee employee = (new EmployeeDb()).checkLogin(userName, password);
            if (employee == null){
                error = "Invalid username or password";
            } else {
                HttpSession session = SessionUtil.getSession();
                session.setAttribute("username", userName);
                session.setAttribute("userid", employee.getId());
                error = "";
            }
        }

        if ("".equalsIgnoreCase(error)) return "list?faces-redirect=true";
        return "";
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
