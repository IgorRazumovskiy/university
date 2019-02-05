package ua.com.foxminded.dao;

import java.util.List;

public interface GenericDAO<E, K> {

    E create(E entity);

    E update(E entity);

    E findOne(K id);

    List<E> findAll();

    E delete(K id);

}
