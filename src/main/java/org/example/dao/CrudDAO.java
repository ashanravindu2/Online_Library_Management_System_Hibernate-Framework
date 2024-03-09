package org.example.dao;

import org.hibernate.Session;

public interface CrudDAO <T,J> extends SuperDAO{
    boolean save(T t);
    void setSession(Session session);
}
