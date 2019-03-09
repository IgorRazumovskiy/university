package ua.com.foxminded.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import ua.com.foxminded.dao.DAOException;
import ua.com.foxminded.dao.implementation.*;
import ua.com.foxminded.domain.*;

public class DAOTest {

    GroupDAOImpl groupdao = new GroupDAOImpl();

    @Test
    public void testFindAllGroups() throws DAOException {
        List<Group> actual = groupdao.findAll();
        for (Group x : actual) {
            System.out.println(x);
            System.out.println(x.getStudents());
        }
    }
    
}
