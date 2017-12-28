package controller;

import database.LoginDb;

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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        LoginDb loginDb = new LoginDb();
        if (loginDb.checkLogin(username, password)){
            session.setAttribute("username", username);
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }
        RequestDispatcher rs = request.getRequestDispatcher("jsp/login.jsp");
        rs.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            request.getRequestDispatcher("jsp/login.jsp").forward(request, response);
            return;
        } else {
            response.sendRedirect(request.getContextPath() + "/LoginController");
            return;
        }
    }
}
