package ua.com.foxminded.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.com.foxminded.dao.GroupDAO;
import ua.com.foxminded.dao.implementation.GroupDAOImpl;
import ua.com.foxminded.domain.Group;

@WebServlet("/groups")
public class GroupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private GroupDAO groupDAO;

    public void init() throws ServletException {
        super.init();
        groupDAO = new GroupDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "LIST";
        }
        if (command.equals("ADD")) {
            addGroup(request, response);
        }
        if (command.equals("LIST")) {
            readListGroups(request, response);
        }
        if ((command.equals("READ")) || (command.equals("FIND"))) {
            readGroup(request, response);
        }
        if (command.equals("UPDATE")) {
            updateGroup(request, response);
        }
        if (command.equals("DELETE")) {
            deleteGroup(request, response);
        }
    }

    private void deleteGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Group group = groupDAO.delete(id);
        readListGroups(request, response);       
    }

    private void updateGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Group group = new Group();
        group.setName(request.getParameter("name"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        group.setId(id);
        groupDAO.update(group);
        readListGroups(request, response);
    }

    private void readGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        Group group = groupDAO.findOne(id);
        request.setAttribute("group", group);
        if ((request.getParameter("command").equals("READ"))) {
            request.getRequestDispatcher("/update-group-form.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/group.jsp").forward(request, response);
        }
    }

    private void addGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Group group = new Group();
        group.setName(request.getParameter("name"));
        groupDAO.create(group);
        readListGroups(request, response);
    }

    private void readListGroups(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Group> groupList = groupDAO.findAll();
        request.setAttribute("groupList", groupList);
        request.getRequestDispatcher("/groups.jsp").forward(request, response);
    }

}
