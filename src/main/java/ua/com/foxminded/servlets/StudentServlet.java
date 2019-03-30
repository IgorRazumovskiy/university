package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.StudentDAO;
import ua.com.foxminded.dao.implementation.StudentDAOImpl;
import ua.com.foxminded.domain.Student;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private StudentDAO studentDAO;

    public void init() throws ServletException {
        super.init();
        studentDAO = new StudentDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "LIST";
        }
        if (command.equals("ADD")) {
            addStudent(request, response);
        }
        if (command.equals("LIST")) {
            readListStudents(request, response);
        }
        if ((command.equals("READ")) || (command.equals("FIND"))) {
            readStudent(request, response);
        }
        if (command.equals("UPDATE")) {
            updateStudent(request, response);
        }
        if (command.equals("DELETE")) {
            deleteStudent(request, response);
        }
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.delete(id);
        readListStudents(request, response);
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student(request.getParameter("name"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        student.setId(id);
        studentDAO.update(student);
        readListStudents(request, response);
    }

    private void readStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Student student = studentDAO.findOne(id);
        request.setAttribute("student", student);
        if ((request.getParameter("command").equals("READ"))) {
            request.getRequestDispatcher("/update-student-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/student.jsp").forward(request, response);
        }
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Student student = new Student(request.getParameter("name"));
        studentDAO.create(student);
        readListStudents(request, response);
    }

    private void readListStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> studentList = studentDAO.findAll();
        request.setAttribute("studentList", studentList);
        request.getRequestDispatcher("/students.jsp").forward(request, response);
    }

}
