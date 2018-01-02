package controller;

import database.LoginDb;
import model.Employee;
import utils.Mail;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Random;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        if (request.getParameter("ajax") != null){
            session.invalidate();
        } else if (request.getParameter("forgotemail") != null){
            System.out.println("forgot");
            forgotPassword(request.getParameter("forgotemail"));
        } else {
            String username = request.getParameter("user");
            String password = request.getParameter("pass");
            LoginDb loginDb = new LoginDb();
            Employee e = loginDb.checkLogin(username, password);
            loginDb.closeConnection();
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
        byte[] array = new byte[10]; // length is bounded by 7
        new Random().nextBytes(array);
        String newPass = new String(array, Charset.forName("UTF-8"));


        if (email != null){
            Mail mail = new Mail();
            String body = "Mật khẩu mới của bạn để truy cập trang SpecIT là: " + newPass;
            mail.sendMail(email, body, "Quên mật khẩu");
        }
    }
}
