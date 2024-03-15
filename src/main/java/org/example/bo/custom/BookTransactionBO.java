package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.AdminDTO;
import org.example.dto.BarrowBooksDTO;
import org.example.dto.BookTransactionDTO;
import org.example.dto.BooksDTO;

import java.util.List;

public interface BookTransactionBO extends CrudBO<BookTransactionDTO,String> {
    BookTransactionDTO findCredential(String text);
    String generateTransacId();

    List<BarrowBooksDTO> getAllBarroeBooks(String text);

    boolean bookReturnStatusUpdate(String text, String userName);

    List<BarrowBooksDTO> getAllReturnBeforeBarrowBooks(String userName);

    List<BarrowBooksDTO> getAllTransactionIsUser(String userName);
}
