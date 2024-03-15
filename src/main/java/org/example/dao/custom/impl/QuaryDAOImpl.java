package org.example.dao.custom.impl;

import org.example.dao.custom.QuaryDAO;
import org.example.dto.BarrowBooksDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class QuaryDAOImpl implements QuaryDAO {
    private Session session;

    @Override
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public String isAvailableOrNot(String branchId, String bookTitle) {
        /*Transaction transaction = session.beginTransaction();
        String available = session.createNativeQuery("SELECT b.books_avl FROM books b WHERE b.branch_id='"+branchId+"' AND b.books_title='"+bookTitle+"'+").toString() ;
        System.out.println(available);
        transaction.commit();
        session.close();
        return available;*/

        Transaction transaction = session.beginTransaction();
        String queryString = "SELECT books_avl FROM books WHERE branch_id = :branchId AND books_title = :bookTitle";
        Query query = session.createNativeQuery(queryString);
        query.setParameter("branchId", branchId);
        query.setParameter("bookTitle", bookTitle);
        String available = (String) query.uniqueResult();
        transaction.commit();
        session.close();
        return available;


    }

    @Override
    public boolean bookAvlUpdate(String bookId, String branchId) {
        Transaction transaction = session.beginTransaction();
        String queryString = "UPDATE books SET books_avl = 'No' WHERE books_id = :bookId AND branch_id = :branchId";
        Query query = session.createNativeQuery(queryString);
        query.setParameter("bookId", bookId);
        query.setParameter("branchId", branchId);
        int result = query.executeUpdate();
        transaction.commit();
        session.close();
        return result > 0;
    }

    @Override
    public List<BarrowBooksDTO> getAllBarrowBooks(String userGmail) {
      List<BarrowBooksDTO> dtos = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createNativeQuery("SELECT b.books_id,b.books_title,b.books_genre,bb.barrow_date,bb.return_date,b.branch_id FROM books b INNER JOIN book_transaction bb ON b.books_id = bb.books_id WHERE bb.user_gmail = '"+userGmail+"'").list();
        transaction.commit();
        session.close();
        if (list.isEmpty()){
            System.out.println("empty");
        }else {
            for (Object[] objects : list){
                dtos.add(new BarrowBooksDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (String) objects[2],
                        (Timestamp) objects[3],
                        (Date) objects[4],
                        (String) objects[5]
                ));
            }
        }
        return dtos;
    }

}
