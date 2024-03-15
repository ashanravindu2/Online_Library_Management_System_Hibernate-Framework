package org.example.bo.custom.impl;

import org.example.bo.custom.BookTransactionBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.*;
import org.example.dto.BarrowBooksDTO;
import org.example.dto.BookTransactionDTO;
import org.example.dto.BooksDTO;
import org.example.dto.BranchDTO;
import org.example.entity.BookTransaction;
import org.example.entity.Books;
import org.example.entity.Branch;
import org.example.entity.User;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BookTransactionBOImpl implements BookTransactionBO {
    private final BookTransactionDAO bookTransactionDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKTRANSACTIONDAO);
    private final BooksDAO booksDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKSDAO);
    private final UserDAO userDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.USERDAO);
    private final QuaryDAO quaryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.QUARYDAO);

    @Override
    public boolean save(BookTransactionDTO bookTransactionDTO) {

        Session roomSession = Configure.getInstance().getSession();
        booksDAO.setSession(roomSession);
        Books bookId = booksDAO.find(bookTransactionDTO.getBooks_id());


        Session userSession = Configure.getInstance().getSession();
        userDAO.setSession(userSession);
        User user = userDAO.find(bookTransactionDTO.getUser_gmail());

        try {
            if (bookId != null && user != null) {
                Session bookTransactionSession = Configure.getInstance().getSession();
                bookTransactionDAO.setSession(bookTransactionSession);
                return bookTransactionDAO.save(new BookTransaction(
                        bookTransactionDTO.getTransaction_id(),
                        bookTransactionDTO.getReturn_date(),
                        bookId,
                        user
                ));
            } else {
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BookTransactionDTO bookTransactionDTO) {
       return false;
    }

    @Override
    public boolean delete(String transacId) {
        Session session = Configure.getInstance().getSession();
        bookTransactionDAO.setSession(session);
        return bookTransactionDAO.delete(transacId);
    }


    @Override
    public BookTransactionDTO find(String s) {
        return null;
    }

    @Override
    public List<BookTransactionDTO> getAll() {
        return null;
    }

    @Override
    public BookTransactionDTO findCredential(String transacId) {
        return null;
    }

    @Override
    public String generateTransacId() {
        Session session = Configure.getInstance().getSession();
        bookTransactionDAO.setSession(session);
        String transacId = bookTransactionDAO.getNextID();
        String nextTransacId = "T000";
        if(transacId != null) {
            transacId = transacId.substring(1, transacId.length());
            int intId = Integer.parseInt(transacId);
            intId = intId + 1;

            if (intId < 10) {
                nextTransacId = "T00" + intId;
            } else if (intId < 100) {
                nextTransacId = "T0" + intId;
            } else {
                nextTransacId = "T" + intId;
            }
        }else {
            nextTransacId = "T001";
        }
        return nextTransacId;
    }

    @Override
    public List<BarrowBooksDTO> getAllBarroeBooks(String userName) {
        Session session = Configure.getInstance().getSession();
        quaryDAO.setSession(session);
        List<BarrowBooksDTO>branchDTOS  = new ArrayList<>();
        for (Object[] objects : quaryDAO.getAllBarrowBooks(userName)){
            branchDTOS.add(new BarrowBooksDTO(
                    (String) objects[0],//books_id
                    (String) objects[1],//books_title
                    (String) objects[2],//books_genre
                    (String) objects[3],//branch_id
                    (String) objects[4],//barrow_date
                    (String) objects[5]//return_date
                ));
        }
        return branchDTOS;
    }


}
