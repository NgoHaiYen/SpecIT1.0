package controller;

import database.LoginDb;
import model.Employee;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        LoginDb loginDb = new LoginDb();
        Employee e = loginDb.checkLogin(username, password);
        if (e != null){
            session.setAttribute("role", e.getRole());
            session.setAttribute("id", e.getId());
            response.sendRedirect(request.getContextPath() + "/add");
            return;
        }
        RequestDispatcher rs = request.getRequestDispatcher("index.jsp");
        rs.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        } else {
            response.sendRedirect(request.getContextPath() + "/add");
            return;
        }
    }
}
