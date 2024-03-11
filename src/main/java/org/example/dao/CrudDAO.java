package org.example.dao;

import org.hibernate.Session;

import java.util.List;

public interface CrudDAO <T,J> extends SuperDAO{
    boolean save(T t);

    T find(J j);

    boolean update(T t);

    void setSession(Session session);

    boolean delete(J j);

    List<T> getAll();

}
