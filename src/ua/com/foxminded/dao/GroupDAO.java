package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Group;

public interface GroupDAO extends GenericDAO<Group, Integer> {
    
    public List<Group> findGroupsByFaculty(Integer id);
    
}
