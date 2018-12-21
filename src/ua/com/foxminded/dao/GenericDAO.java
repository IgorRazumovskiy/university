package ua.com.foxminded.dao;

import java.util.List;

public interface GenericDAO<E, K> {

    public E create(E entity);

    public E update(E entity);

    public E findOne(K id);

    public List<E> findAll();

    public E delete(K id);

}
