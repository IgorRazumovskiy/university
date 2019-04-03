package ua.com.foxminded.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;

@WebServlet("/group-update")
public class GroupUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAO groupDAO;

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
        request.getRequestDispatcher("/group-update.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Group group = new Group();
        group.setName(request.getParameter("name"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        group.setId(id);
        groupDAO.update(group);
        response.sendRedirect("groups");
    }

}
