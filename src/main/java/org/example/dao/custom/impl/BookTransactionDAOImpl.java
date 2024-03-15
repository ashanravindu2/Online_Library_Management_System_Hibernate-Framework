package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.BookTransactionDAO;
import org.example.entity.BookTransaction;
import org.example.entity.Books;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.io.Serializable;
import java.util.List;

public class BookTransactionDAOImpl implements BookTransactionDAO {
    private Session session;

    @Override
    public boolean save(BookTransaction bookTransaction) {
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            Serializable save = session.save(bookTransaction);
            transaction.commit();
            return save!=null;
        }catch (Exception e){
            if (transaction != null) {
                transaction.rollback();
            }
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }

    }

    @Override
    public BookTransaction find(String transacId) {
        try {
            Transaction transaction = session.beginTransaction();
            BookTransaction bookTransaction = session.get(BookTransaction.class, transacId);
            transaction.commit();
            return bookTransaction;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(BookTransaction bookTransaction) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(bookTransaction);
            transaction.commit();
            return true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public void setSession(Session session) {
        this.session = session;

    }

    @Override
    public boolean delete(String trasacId) {
        try {
            Transaction transaction = session.beginTransaction();
            BookTransaction bookTransaction = session.get(BookTransaction.class, trasacId);
            session.delete(bookTransaction);
            transaction.commit();
            return true;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public List<BookTransaction> getAll() {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<BookTransaction> query = criteriaBuilder.createQuery(BookTransaction.class);
        query.from(Books.class);
        List<BookTransaction> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getNextID() {
        String newId = "T000";
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT transaction_id FROM book_transaction ORDER BY transaction_id DESC LIMIT 1";
        NativeQuery query = session.createSQLQuery(sql);
        List list = query.list();

        if (list.isEmpty()) {
            return newId;
        }else {
            newId= (String) list.get(0);
            transaction.commit();
            session.close();
            return newId;
        }
    }


}
