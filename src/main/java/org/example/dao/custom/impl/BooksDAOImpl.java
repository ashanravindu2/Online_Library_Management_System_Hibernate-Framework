package org.example.dao.custom.impl;

import javafx.scene.control.Alert;
import org.example.dao.custom.BooksDAO;
import org.example.dao.custom.BranchDAO;
import org.example.dto.BooksDTO;
import org.example.entity.Books;
import org.example.entity.Branch;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.awt.print.Book;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BooksDAOImpl implements BooksDAO {
private Session session;

    @Override
    public boolean save(Books books) {
        try {
            Transaction transaction = session.beginTransaction();
            Serializable save = session.save(books);
            transaction.commit();
            return save!=null;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return false;
        }finally {
            session.close();
        }
    }

    @Override
    public Books find(String bookId) {
        try {
            Transaction transaction = session.beginTransaction();
            Books books = session.get(Books.class, bookId);
            transaction.commit();
            return books;
        }catch (Exception e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            return null;
        }finally {
            session.close();
        }
    }

    @Override
    public boolean update(Books books) {
        try {
            Transaction transaction = session.beginTransaction();
            session.update(books);
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
        this.session=session;
    }

    @Override
    public boolean delete(String bookId) {
        try {
            Transaction transaction = session.beginTransaction();
            Books books = session.get(Books.class, bookId);
            session.delete(books);
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
    public List<Books> getAll() {
        Transaction transaction = session.beginTransaction();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Books> query = criteriaBuilder.createQuery(Books.class);
        query.from(Books.class);
        List<Books> resultList = session.createQuery(query).getResultList();
        transaction.commit();
        session.close();
        return resultList;
    }

    @Override
    public String getNextID() {
        String newId = "K000";
        Transaction transaction = session.beginTransaction();
        String sql = "SELECT books_id FROM books ORDER BY books_id DESC LIMIT 1";
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

    @Override
    public List<BooksDTO> isSearchTitle(String value) {
        List<BooksDTO> booksDTOS = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        List<Object[]>list=null;
        list = session.createNativeQuery("SELECT * FROM books WHERE books_avl = 'Yes' AND books_title='"+value+"'").list();
        transaction.commit();
        session.close();
        if (list.isEmpty()){
            System.out.println("empty");
        }else {
            for (Object[] objects : list){
                booksDTOS.add(new BooksDTO(
                        (String) objects[0],//id
                        (String) objects[1],//title
                        (String) objects[2],//author
                        (String) objects[3],//genre
                        (String) objects[4],//avl
                        (String) objects[5]//branch

                ));
            }
        }
        return booksDTOS;
    }


}
