package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Books;
import org.example.entity.Branch;

public interface BooksDAO extends CrudDAO<Books,String> {
    String getNextID();
}
