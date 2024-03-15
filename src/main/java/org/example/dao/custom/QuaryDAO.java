package org.example.dao.custom;

import org.example.dao.SuperDAO;
import org.example.dto.BarrowBooksDTO;
import org.hibernate.Session;

import java.util.List;

public interface QuaryDAO extends SuperDAO {

    void setSession(Session session);

    String isAvailableOrNot(String branchId, String bookTitle);

    boolean bookAvlUpdate(String bookId, String branchId);

    List<BarrowBooksDTO> getAllBarrowBooks(String userName);


    boolean bookUpdateAvl(String text);

    boolean bookReturnStatusUpdate(String bookId, String userName);

    List<BarrowBooksDTO> getAllReturnBeforeList(String userName);

    List<BarrowBooksDTO> getAllTransactionIsUserList(String userName);
}
