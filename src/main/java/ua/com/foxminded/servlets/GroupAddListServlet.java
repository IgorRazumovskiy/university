package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.implementation.GroupDAOHibernate;
import ua.com.foxminded.domain.Group;

@WebServlet("/groups")
public class GroupAddListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAO groupDAO;
    private String path;

    public void init() throws ServletException {
        super.init();
        groupDAO = new GroupDAOHibernate();
        path = getServletContext().getContextPath();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Group> groupList = groupDAO.findAll();
        request.setAttribute("groupList", groupList);
        request.getRequestDispatcher("/groups.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Group group = new Group();
        group.setName(request.getParameter("name"));
        groupDAO.create(group);
        response.sendRedirect(path + "/groups");
    }

}
