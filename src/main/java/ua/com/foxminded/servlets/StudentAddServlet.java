package ua.com.foxminded.servlets;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;

@WebServlet("/add-student")
public class StudentAddServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init(ServletConfig config) throws ServletException {
        super.init();
        studentDAO = new StudentDAOImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student(request.getParameter("name"));
        studentDAO.create(student);
        response.sendRedirect("/university/students");
    }

}
