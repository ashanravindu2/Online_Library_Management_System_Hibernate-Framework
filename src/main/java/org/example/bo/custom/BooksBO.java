package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.BookTransactionDTO;
import org.example.dto.BooksDTO;

import java.util.ArrayList;
import java.util.List;


public interface BooksBO extends CrudBO<BooksDTO,String> {
    BooksDTO findCredential(String text);
    List<String>getBranchIds();
    String getNewBooksId();
    List<BooksDTO> isSearchBookTitle(String value);
    String IsavailableOrNotBook(String branchId, String bookTitle);

    boolean bookAvlUpdate(String bookId, String branchId);
}
