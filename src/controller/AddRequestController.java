package controller;

import database.EmployeeDb;
import database.ItteamDb;
import database.PriorityDb;
import model.Employee;
import model.Itteam;
import model.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "AddRequestController")
public class AddRequestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        String button = request.getParameter("register");
        if ("confirm".equals(button)) {
//            boolean check = checkValidate(request);
//            if(check) {
//                response.sendRedirect(request.getContextPath() + "/ConfirmController");
//                return;
//            } else {
//                request.getRequestDispatcher("jsp/register.jsp").forward(request, response);
//            }

        } else if ("back".equals(button)) {
            response.sendRedirect(request.getContextPath() + "/ListUserController");
            return;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("id") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        // priorities
        PriorityDb pdb = new PriorityDb();
        ArrayList<Priority> priorities = pdb.getAllPriorities();
        session.setAttribute("priorities", priorities);

        // itteams
        ItteamDb idb = new ItteamDb();
        ArrayList<Itteam> itteams = idb.getAllItteams();
        session.setAttribute("itteams", itteams);

        // employees
        EmployeeDb edb = new EmployeeDb();
        ArrayList<Employee> employees = edb.getAllEmployee();
        session.setAttribute("employees", employees);

        request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
    }

//    private boolean checkValidate(HttpServletRequest request) throws IOException, ServletException {
//        HttpSession session = request.getSession(true);
//
//        String value = request.getParameter("tencv");
//        if (value.isEmpty()) {
//            return false;
//        }
//
//        value = request.getParameter("priority");
//        if(value.isEmpty()) {
//            return false;
//        }
//
//        value = request.getParameter("date");
//        if (value.isEmpty()){
//            return false;
//        }
//
//        userName = request.getParameter("new_password");
//        if(userName.isEmpty()) {
//            request.setAttribute("errorPassword", "Password can not be empty");
//            check=false;
//        } else if(!userName.equals(request.getParameter("re_pass"))) {
//            request.setAttribute("errorConfirmPass", "Incorrect confirm password");
//            check=false;
//        } else {
//            session.setAttribute("npassword", userName);
//        }
//
//        userName = request.getParameter("new_tel");
//        session.setAttribute("ntel", request.getParameter("new_tel"));
//        if(userName.isEmpty()) {
//            request.setAttribute("errorTel", "Tel can not be empty");
//            check=false;
//        }
//
//        Part filePart = request.getPart("new_image");
//        if(Paths.get(filePart.getSubmittedFileName()).toString().equals("")) {
//            request.setAttribute("errorImage", "Image can not be empty");
//            check=false;
//        } else {
//            uploadImageToServer(filePart, session, request);
//        }
//
//        userName = request.getParameter("new_level_japan");
//        String point = request.getParameter("new_score");
//        session.setAttribute("nscore", point);
//        if(userName != null && !"".equals(userName)) {
//            session.setAttribute("nleveljapan", userName);
//            if (point == null || point.equals("")) {
//                request.setAttribute("errorScore", "You must enter your score");
//            } else {
//                try{
//                    int score = Integer.parseInt(request.getParameter("new_score"));
//                    if (score <= 0) {
//                        check = false;
//                        request.setAttribute("errorScore", "Invalid score, score has to be positive!");
//                    }
//                } catch (NumberFormatException e) {
//                    check = false;
//                    request.setAttribute("errorScore", "Invalid score, score has to be a number!");
//                }
//            }
//        } else {
//            if(point != null && !"".equals(point)) {
//                request.setAttribute("errorScore", "You must enter your level Japan with your score");
//            }
//        }
//        listUserDao.closeConnection();
//        session.setAttribute("ngender", request.getParameter("new_gender"));
//        session.setAttribute("ngroupName", request.getParameter("new_group"));
//        session.setAttribute("ndate", request.getParameter("new_date"));
//        session.setAttribute("nmonth", request.getParameter("new_month"));
//        session.setAttribute("nyear", request.getParameter("new_year"));
//
//        session.setAttribute("ndatestart", request.getParameter("new_start_date"));
//        session.setAttribute("nmonthstart", request.getParameter("new_start_month"));
//        session.setAttribute("nyearstart", request.getParameter("new_start_year"));
//
//        session.setAttribute("ndateend", request.getParameter("new_end_date"));
//        session.setAttribute("nmonthend", request.getParameter("new_end_month"));
//        session.setAttribute("nyearend", request.getParameter("new_end_year"));
//        return check;
//    }

    private void uploadImageToServer(Part filePart, HttpSession session, HttpServletRequest request) throws IOException {
        String fileName = Paths.get(filePart.getSubmittedFileName()).toString();
        if (checkImage(fileName)) {
            byte[] buffer = new byte[4096];
            long time = System.currentTimeMillis();
            fileName = fileName.split("\\.")[0] + time + "." + fileName.split("\\.")[1];
//            session.setAttribute("", fileName);
            fileName = request.getServletContext().getRealPath("") + "image/" + fileName;
            InputStream content = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            content.close();
        } else {
            request.setAttribute("errorImage", "Invalid image");
        }
    }

    private boolean checkImage(String fileName) {
        String mimeType = getServletContext().getMimeType(fileName);
        if (mimeType.startsWith("image/")) {
            return true;
        }
        return false;
    }
}
