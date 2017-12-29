package controller;

import database.*;
import model.Employee;
import model.Itteam;
import model.Priority;
import utils.Constant;

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

        addDataForNavi(request);

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

    private boolean checkValidate(HttpServletRequest request) {
        ArrayList<String> values = new ArrayList<String>();
        values.add("tencv");
        values.add("priorities");
        values.add("date");
        values.add("itteam");
        values.add("relater");
        values.add("nd");
        values.add("upload");
        return checkValidate(request, values);
    }

    private boolean checkValidate(HttpServletRequest request, ArrayList<String> values){
        for (String value:values) {
            if (!checkValidate(request, value)) return false;
        }
        return true;
    }

    private boolean checkValidate(HttpServletRequest request, String value){
        value = request.getParameter("itteam");
        return value.isEmpty();
    }

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

    private void addDataForNavi(HttpServletRequest request){
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        RequestDb rdb = new RequestDb();
        session.setAttribute("myra", rdb.getNumberOfRequest(id, Constant.ALL));
        session.setAttribute("myrn", rdb.getNumberOfRequest(id, Constant.NEW));
        session.setAttribute("myri", rdb.getNumberOfRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("myrr", rdb.getNumberOfRequest(id, Constant.RESOLVED));
        session.setAttribute("myro", rdb.getNumberOfRequest(id, Constant.OUT_OF_DATE));

        session.setAttribute("myaa", rdb.getNumberOfAssignRequest(id, Constant.ALL));
        session.setAttribute("myan", rdb.getNumberOfAssignRequest(id, Constant.NEW));
        session.setAttribute("myai", rdb.getNumberOfAssignRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("myar", rdb.getNumberOfAssignRequest(id, Constant.RESOLVED));
        session.setAttribute("myao", rdb.getNumberOfAssignRequest(id, Constant.OUT_OF_DATE));

        RelaterDb relaterDb = new RelaterDb();
        session.setAttribute("rla", relaterDb.getNumberOfRequestRelate(id, Constant.ALL));
        session.setAttribute("rln", relaterDb.getNumberOfRequestRelate(id, Constant.NEW));
        session.setAttribute("rli", relaterDb.getNumberOfRequestRelate(id, Constant.IN_PROGRESS));
        session.setAttribute("rlr", relaterDb.getNumberOfRequestRelate(id, Constant.RESOLVED));
        session.setAttribute("rlo", relaterDb.getNumberOfRequestRelate(id, Constant.OUT_OF_DATE));

        session.setAttribute("myaa", rdb.getNumberOfAssignRequest(id, Constant.ALL));
        session.setAttribute("myan", rdb.getNumberOfAssignRequest(id, Constant.NEW));
        session.setAttribute("myai", rdb.getNumberOfAssignRequest(id, Constant.IN_PROGRESS));
        session.setAttribute("myar", rdb.getNumberOfAssignRequest(id, Constant.RESOLVED));
        session.setAttribute("myao", rdb.getNumberOfAssignRequest(id, Constant.OUT_OF_DATE));
    }
}
