package controller;

import database.EmployeeDb;
import helper.Mail;
import model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Random;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");

        // get data from ajax: logout
        if (request.getParameter("ajax") != null){
            session.invalidate();
        } else if (request.getParameter("forgotemail") != null){   // get data from ajax: forgot password
            forgotPassword(request.getParameter("forgotemail"));
        } else {  // get data from form post
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            EmployeeDb employeeDb = new EmployeeDb();
            Employee e = employeeDb.checkLogin(username, password);
            if (e != null){
                session.setAttribute("role", e.getRole());
                session.setAttribute("id", e.getId());
                session.setAttribute("username",e.getName());
                session.setAttribute("branch",e.getBranchId());
                response.sendRedirect(request.getContextPath() + "/list");
            } else {
                request.setAttribute("errMe", "Tên đăng nhập hoặc mật khẩu không đúng");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/list");
        }
    }

    private void forgotPassword(String email){
        // Update new password
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String newPass = salt.toString();

        if (email != null){
            Mail mail = new Mail();
            String body = "Mật khẩu mới của bạn để truy cập trang SpecIT là: " + newPass;
            mail.sendMail(email, body, "Quên mật khẩu");
        }
    }
}
