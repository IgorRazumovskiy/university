package ua.com.foxminded.dao;

import java.util.List;

import ua.com.foxminded.domain.Chair;

public interface ChairDAO extends GenericDAO<Chair, Integer> {

    public List<Chair> findChairsByFaculty(Integer id);

}
