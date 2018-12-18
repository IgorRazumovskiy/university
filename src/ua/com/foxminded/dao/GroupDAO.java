package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Group;

public interface GroupDAO {

    public Group create(Group group) throws DAOException;
    
    public Group update(Group group) throws DAOException;

    public Group findOne(Integer id) throws DAOException;

    public List<Group> findAll() throws DAOException;

    public Group delete(Integer id) throws DAOException;

}
