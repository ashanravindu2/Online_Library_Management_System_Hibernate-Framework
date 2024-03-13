package org.example.bo.custom.impl;

import org.example.bo.custom.BooksBO;
import org.example.bo.custom.BranchBO;
import org.example.configuration.Configure;
import org.example.dao.DAOFactory;
import org.example.dao.custom.BooksDAO;
import org.example.dao.custom.BranchDAO;
import org.example.dto.BooksDTO;
import org.example.dto.BranchDTO;
import org.example.entity.Books;
import org.example.entity.Branch;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class BooksBOImpl implements BooksBO {

 BooksDAO booksDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BOOKSDAO);
 BranchDAO branchDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOType.BRANCHDAO);

    @Override
    public boolean save(BooksDTO booksDTO) {

        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        Branch branches = branchDAO.find(booksDTO.getBranch_id());
        try {

            Session booksession = Configure.getInstance().getSession();
            booksDAO.setSession(booksession);
            return booksDAO.save(new Books(
                    booksDTO.getBooks_id(),
                    booksDTO.getBooks_title(),
                    booksDTO.getBooks_author(),
                    booksDTO.getBooks_genre(),
                    booksDTO.getBooks_avl(),
                    branches

            ));
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(BooksDTO booksDTO) {
        Session session = Configure.getInstance().getSession();
        branchDAO.setSession(session);
        Branch branches = branchDAO.find(booksDTO.getBranch_id());

        try {
            Session bookSession = Configure.getInstance().getSession();
            booksDAO.setSession(bookSession);
            return booksDAO.update(new Books(
                    booksDTO.getBooks_id(),
                    booksDTO.getBooks_title(),
                    booksDTO.getBooks_author(),
                    booksDTO.getBooks_genre(),
                    booksDTO.getBooks_avl(),
                    booksDAO.find(booksDTO.getBooks_id()).getBranch()
            ));
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }


    }

    @Override
    public boolean delete(String booksId) {
        Session session = Configure.getInstance().getSession();
        booksDAO.setSession(session);
        return booksDAO.delete(booksId);
    }

    @Override
    public BooksDTO find(String id) {
        return null;
    }

    @Override
    public List<BooksDTO> getAll() {
        Session session = Configure.getInstance().getSession();
        booksDAO.setSession(session);
        List<BooksDTO>branchDTOS  = new ArrayList<>();
        for (Books books : booksDAO.getAll()){
            branchDTOS.add(new BooksDTO(
                    books.getBooks_id(),
                    books.getBooks_title(),
                    books.getBooks_author(),
                    books.getBooks_genre(),
                    books.getBooks_avl(),
                    books.getBranch().getBranch_id()
            ));
        }
        return branchDTOS;
    }

    @Override
    public BooksDTO findCredential(String text) {
        Session session = Configure.getInstance().getSession();
        booksDAO.setSession(session);
        Books books = booksDAO.find(text);
        if (books!=null){
            return new BooksDTO(
                    books.getBooks_id(),
                    books.getBooks_title(),
                    books.getBooks_author(),
                    books.getBooks_genre(),
                    books.getBooks_avl(),
                    books.getBranch().getBranch_id()

            );
        }else {
            return null;
        }
    }

    @Override
    public String getNewBooksId() {
        Session session = Configure.getInstance().getSession();
        booksDAO.setSession(session);
        String booksId = booksDAO.getNextID();
        String nextBooksId = "K000";
        if(booksId != null) {
            booksId = booksId.substring(1, booksId.length());
            int intId = Integer.parseInt(booksId);
            intId = intId + 1;

            if (intId < 10) {
                nextBooksId = "K00" + intId;
            } else if (intId < 100) {
                nextBooksId = "KB0" + intId;
            } else {
                nextBooksId = "K" + intId;
            }
        }else {
            nextBooksId = "K001";
        }
        return nextBooksId;
    }

}
