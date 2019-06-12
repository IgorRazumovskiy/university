package ua.com.foxminded.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.StudentDAOHibernate;
import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;


@WebServlet("/student/delete")
public class StudentDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;
    private String path;

    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOHibernate();
        path = getServletContext().getContextPath();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.delete(id);
        response.sendRedirect(path + "/students");
    }

}
