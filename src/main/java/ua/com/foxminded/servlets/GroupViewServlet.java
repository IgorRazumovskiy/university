package ua.com.foxminded.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;

@WebServlet("/group")
public class GroupViewServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAOImpl groupDAO;

    public void init() throws ServletException {
        super.init();
        groupDAO = new GroupDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = null;
        Group group = null;
        if (request.getParameter("id") != null) {
            id = Integer.parseInt(request.getParameter("id"));
            group = groupDAO.findOne(id);
        }
        request.setAttribute("group", group);
        request.getRequestDispatcher("/group.jsp").forward(request, response);
    }

}
