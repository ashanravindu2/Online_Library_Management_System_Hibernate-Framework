package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.BooksDTO;
import org.example.entity.BookTransaction;
import org.example.entity.Books;
import org.example.entity.Branch;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public interface BooksDAO extends CrudDAO<Books,String> {
    String getNextID();
    List<BooksDTO> isSearchTitle(String value);
}
