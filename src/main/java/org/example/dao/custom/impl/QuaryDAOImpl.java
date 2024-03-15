package org.example.dao.custom.impl;

import org.example.dao.custom.QuaryDAO;
import org.example.dto.BarrowBooksDTO;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.math.BigInteger;
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
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "UPDATE books SET books_avl = 'No' WHERE books_id = :bookId AND branch_id = :branchId";
            Query query = session.createNativeQuery(queryString);
            query.setParameter("bookId", bookId);
            query.setParameter("branchId", branchId);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
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

    @Override
    public boolean bookUpdateAvl(String text) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "UPDATE books SET books_avl = 'Yes' WHERE books_id = :bookId";
            Query query = session.createNativeQuery(queryString);
            query.setParameter("bookId", text);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public boolean bookReturnStatusUpdate(String bookId, String userName) {
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String queryString = "UPDATE book_transaction SET return_status='1' WHERE books_id = :bookId AND user_gmail = :userName";
            Query query = session.createNativeQuery(queryString);
            query.setParameter("bookId", bookId);
            query.setParameter("userName", userName);
            int result = query.executeUpdate();
            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    @Override
    public List<BarrowBooksDTO> getAllReturnBeforeList(String userName) {
        List<BarrowBooksDTO> dtos = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        List<Object[]> list = session.createNativeQuery("SELECT b.books_id,b.books_title,b.books_genre,bb.barrow_date,bb.return_date,b.branch_id FROM books b INNER JOIN book_transaction bb ON b.books_id = bb.books_id WHERE bb.user_gmail = '"+userName+"' AND bb.return_status=0").list();
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

    @Override
    public List<BarrowBooksDTO> getAllTransactionIsUserList(String userName) {
        List<BarrowBooksDTO> dtos = new ArrayList<>();
        Transaction transaction = session.beginTransaction();
        System.out.println("GET QUADAOIMPL");
        List<Object[]> list = session.createNativeQuery("SELECT b.books_id,b.books_title,bb.barrow_date,bb.return_date,b.branch_id,bb.return_status FROM books b INNER JOIN book_transaction bb ON b.books_id = bb.books_id WHERE bb.user_gmail = '"+userName+"'").list();
        transaction.commit();
        session.close();
        if (list.isEmpty()){
            System.out.println("empty");
        }else {
            for (Object[] objects : list){
                dtos.add(new BarrowBooksDTO(
                        (String) objects[0],
                        (String) objects[1],
                        (Timestamp) objects[2],
                        (Date) objects[3],
                        (String) objects[4],
                        (int) objects[5]
                ));
            }
        }
        return dtos;
    }

    @Override
    public int getBranchCount() {
        Transaction transaction = null;
        int count = 0;
        try {
            transaction = session.beginTransaction();
            BigInteger result = (BigInteger) session.createNativeQuery("SELECT COUNT(branch_id) FROM branch").getSingleResult();
            count = result.intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public int getBookCount() {
        Transaction transaction = null;
        int count = 0;
        try {
            transaction = session.beginTransaction();
            BigInteger result = (BigInteger) session.createNativeQuery("SELECT COUNT(books_id) FROM books").getSingleResult();
            count = result.intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }

    @Override
    public int getUserCount() {
        Transaction transaction = null;
        int count = 0;
        try {
            transaction = session.beginTransaction();
            BigInteger result = (BigInteger) session.createNativeQuery("SELECT COUNT(user_gmail) FROM user").getSingleResult();
            count = result.intValue();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // Handle exception properly in your application
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return count;
    }


}
