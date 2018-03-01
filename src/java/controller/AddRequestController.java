package controller;

import database.BranchDb;
import database.EmployeeDb;
import database.PriorityDb;
import model.Branch;
import model.Employee;
import model.Priority;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "AddRequestController")
@MultipartConfig
public class AddRequestController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        int id = (int) session.getAttribute("id");

        // lấy thông tin button submit
        String button = request.getParameter("addrequest");
        if ("add".equals(button)) {
            boolean check = checkValidate(request);
            // nếu đã validate
            if(check) {
//                String file = uploadImageToServer(request.getPart("chooseFile"), request);
//
//                Request r = new Request();
//                r.setSubject(request.getParameter("tencv"));
//                r.setContent(request.getParameter("nd"));
//                r.setCreatedBy((Integer) session.getAttribute("id"));
//                r.setPriority(Integer.parseInt(request.getParameter("priorities")));
//                r.setDeadline(Constant.formatDateToSqlFromView(request.getParameter("date")));
//                r.setBranchId(Integer.parseInt(request.getParameter("branches")));
//                String[] relater = request.getParameterValues("relater");
//                RequestDb rdb = new RequestDb();
//                rdb.addNewRequest(r, relater, file);
//
//                session.setAttribute("myra", rdb.getNumberOfRequest(id, Constant.ALL));
//                session.setAttribute("myrn", rdb.getNumberOfRequest(id, Constant.NEW));
//
//                response.sendRedirect(request.getContextPath() + "/list");
            } else {  // thông tin lỗi
                request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
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
        request.setAttribute("priorities", priorities);

        // itteams
        BranchDb bdb = new BranchDb();
        ArrayList<Branch> branches = bdb.getAllBranch();
        request.setAttribute("branches", branches);

        // employees
        EmployeeDb edb = new EmployeeDb();
        ArrayList<Employee> employees = edb.getAllEmployee();
        request.setAttribute("employees", employees);

        request.getRequestDispatcher("jsp/add.jsp").forward(request, response);
    }

    // kiểm tra thông tin đã được điền vào form
    private boolean checkValidate(HttpServletRequest request) {
        ArrayList<String> values = new ArrayList<>();
        values.add("tencv");
        values.add("priorities");
        values.add("date");
        values.add("branches");
        values.add("nd");

        String[] relater = request.getParameterValues("relater");
        return (relater != null) && checkValidate(request, values);
    }

    private boolean checkValidate(HttpServletRequest request, ArrayList<String> values){
        for (String value:values) {
            if (!checkValidate(request, value)) return false;
        }
        return true;
    }

    private boolean checkValidate(HttpServletRequest request, String value){
        String check = request.getParameter(value);
        return (check != null) && (!check.isEmpty());
    }

    private String uploadImageToServer(Part filePart, HttpServletRequest request) throws IOException {
        // nếu không thêm ảnh chứng minh
        if (filePart == null) return null;

        // lấy thông tin ảnh chứng minh
        String fileName = Paths.get(filePart.getSubmittedFileName()).toString();
        // kiểm tra định dạng ảnh
        if (checkImage(fileName)) {
            byte[] buffer = new byte[4096];
            long time = System.currentTimeMillis();
            fileName = fileName.split("\\.")[0] + time + "." + fileName.split("\\.")[1];
            String name = fileName;
            fileName = request.getServletContext().getRealPath("") + "image/" + fileName;
            InputStream content = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            int bytesRead;
            while ((bytesRead = content.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            content.close();

            return name;
        }
        return null;
    }

    // check if it is an image file
    private boolean checkImage(String fileName) {
        String mimeType = getServletContext().getMimeType(fileName);
        return mimeType != null && mimeType.startsWith("image/");
    }

}
