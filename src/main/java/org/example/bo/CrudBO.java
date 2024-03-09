package org.example.bo;

public interface CrudBO<T,J> extends SuperBO{

    boolean save(T t);
}
