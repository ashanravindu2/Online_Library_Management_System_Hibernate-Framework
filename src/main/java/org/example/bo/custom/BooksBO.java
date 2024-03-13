package org.example.bo.custom;

import org.example.bo.CrudBO;
import org.example.dto.BooksDTO;


public interface BooksBO extends CrudBO<BooksDTO,String> {
    BooksDTO findCredential(String text);

    String getNewBooksId();
}
