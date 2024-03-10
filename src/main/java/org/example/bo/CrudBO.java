package org.example.bo;

public interface CrudBO<T,J> extends SuperBO{

    boolean save(T t);
    boolean update(T t);
    T find(J j);
}
