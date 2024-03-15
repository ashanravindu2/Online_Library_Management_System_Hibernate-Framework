package org.example.dao.custom.impl;

import org.example.dao.custom.QuaryDAO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
    public List<Object[]> getAllBarrowBooks(String userName) {
/*        List<BarrowBooksDTO> dtos = new ArrayList<>();
        Transaction transaction = session.beginTransaction();

        String queryString = "SELECT b.books_id, b.books_title, b.books_genre, b.branch_id, bt.barrow_date, bt.return_date FROM books b JOIN book_transaction bt ON b.books_id = bt.books_id WHERE bt.user_gmail = :userName";
        Query query = session.createNativeQuery(queryString);
        query.setParameter("userName", userName);

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

if (list.isEmpty()) {
            return null;
        } else {
            for (Object[] objects : list) {
                dtos.add(new BarrowBooksDTO(
                        (String) objects[0],//books_id
                        (String) objects[1],//books_title
                        (String) objects[2],//books_genre
                        (Date) objects[3],//barrow_date
                        (Date) objects[4],//return_date
                        (String) objects[5]//branch_id
                ));
            }
        }
        return dtos;*/
        Transaction transaction = session.beginTransaction();
        String queryString = "SELECT b.books_id, b.books_title, b.books_genre, b.branch_id, bt.barrow_date, bt.return_date FROM books b JOIN book_transaction bt ON b.books_id = bt.books_id WHERE bt.user_gmail = :userName";
        Query query = session.createNativeQuery(queryString);
        query.setParameter("userName", userName);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        return list;
    }

/*    @Override
    public List<BarrowBooksDTO> getAllBarrowBooks(String userName) {

        List<BarrowBooksDTO> dtos = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        String queryString = "SELECT b.books_id, b.books_title, b.books_genre, b.branch_id, bt.barrow_date, bt.return_date FROM books b JOIN book_transaction bt ON b.books_id = bt.books_id WHERE bt.user_gmail = :userName";
        Query query = session.createNativeQuery(queryString);
        query.setParameter("userName", userName);
        List<Object[]> list = query.list();
        transaction.commit();
        session.close();
        if (list.isEmpty()) {
            return null;
        } else {
          for (Object[] objects : list) {
              dtos.add(new BarrowBooksDTO(
                      (String) objects[0],//books_id
                      (String) objects[1],//books_title
                      (String) objects[2],//books_genre
                      (Date) objects[3],//barrow_date
                      (Date) objects[4],//return_date
                      (String) objects[5]//branch_id
              ));
            }
        }
        return dtos;
    }*/
}
