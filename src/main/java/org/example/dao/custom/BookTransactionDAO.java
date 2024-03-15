package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Admin;
import org.example.entity.BookTransaction;

public interface BookTransactionDAO extends CrudDAO<BookTransaction,String> {
    String getNextID();


}
