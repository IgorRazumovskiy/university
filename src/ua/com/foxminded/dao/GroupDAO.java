package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Group;

public interface GroupDAO extends GenericDAO<Group, Integer> {

    public Group create(Group group);
    
    public Group update(Group group);

    public Group findOne(Integer id);

    public List<Group> findAll();

    public Group delete(Integer id);

}
